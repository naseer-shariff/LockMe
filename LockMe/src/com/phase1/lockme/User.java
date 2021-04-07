package com.phase1.lockme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class User {
	public static void createUser(String username, String password) {
		File users = new File("Data\\Users\\Users.txt");
		File userAccounts = new File("Data\\UserAccounts\\" + username + "-Accounts.txt");
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(users, true));
			writer.print(username + "\t" + password);
			writer.println();
			writer.close();
			System.out.println(userAccounts.createNewFile());
			System.out.println("User " + username + " succesfully created!");
		} catch (IOException e) {
			System.out.println("We couldn't create your account due to an error. Please try again." + "\n"
					+ "If the issue persists please contact us at support@lockme.com." + "\n"
					+ "We apologize for the inconvenience.");
			Logger.writeToLog(e.toString());
		}
	}

	public static boolean checkUserExists(String username) {
		File users = new File("Data\\Users\\Users.txt");
		boolean userExists = false;
		try {
			Scanner scanner = new Scanner(users);

			while (scanner.hasNext()) {
				if (scanner.next().split("\t")[0].toLowerCase().equals(username.toLowerCase())) {
					userExists = true;
					scanner.close();
					return userExists;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			Logger.writeToLog(e.toString());
		}

		return userExists;
	}

	public static void registerUser() {
		System.out.println("-----:Register User:-----	");
		System.out.println("-> Enter new username");
		System.out.println("  -- Please note the username has to be between 3 and 8 characters long.\n"
				+ " And can have only alphabets and numbers. Special characters like -_@#$! are not allowed!");
		Scanner input = new Scanner(System.in);
		String username = input.next();
		boolean isusernameValid = false;
		do {
			if (username.length() < 3 || username.length() > 8) {
				System.out.println("There username has to be between 3 and 8 characters long. Please try again.");
				System.out.println("-> Enter username");
				username = input.next();
			} else if (!username.matches("[A-Za-z0-9]+")) {
				System.out.println("No special characters allowed. Please try again.");
				System.out.println("-> Enter username");
				username = input.next();
			} else {
				if (checkUserExists(username)) {
					System.out.println(username + " already exists. Please try a different name.");
					username = input.next();
				} else {
					isusernameValid = true;
				}
			}
		} while (!isusernameValid);

		System.out.println("-> Enter password");
		System.out.println("  -- Please note the password has to be between 5 and 12 characters long.");
		String password = input.next();
		boolean isPasswordValid = false;
		do {
			if (password.length() < 5 || password.length() > 12) {
				System.out.println("The password cannot be less than 5 characters and more than 12 characters!");
				System.out.println("-> Enter new password");
				password = input.next();
			} else
				isPasswordValid = true;
		} while (!isPasswordValid);
		createUser(username, password);
		UserAccount.lockerOptions(username);

	}

	public static void Login() {
		System.out.println("=================================");
		System.out.println("|	Log into your account	|");
		System.out.println("=================================");
		System.out.println("Enter username");
		Scanner input = new Scanner(System.in);
		String username = input.next();

		boolean authenticated = false;
		boolean usernameExists = false;
		File users = new File("Data\\Users\\Users.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(users);
			while (scanner.hasNext() && !usernameExists) {
				String storedCredentials = scanner.nextLine();
				String storedUser = storedCredentials.split("\t")[0];
				String storedPassword = storedCredentials.split("\t")[1];
				if (storedUser.toLowerCase().equals(username.toLowerCase())) {
					System.out.println("Enter password");
					String password = input.next();
					if (storedPassword.equals(password)) {
						usernameExists = true;
						System.out.println("Login successful.");
						UserAccount.lockerOptions(username);
					}
				}

			}
			if (!usernameExists) {
				System.out.println("Incorrect username or password. Please try again.");
			}
		} catch (FileNotFoundException e) {
			Logger.writeToLog(e.toString());
			System.out.println("An error ocurred please reload the application."
					+ " If this issue persists please contact support@lockme.com.");
		}
	}

}
