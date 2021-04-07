package com.phase1.lockme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class UserAccount {
	public static void addUserAccount(String username) {
		File users = new File("Data\\UserAccounts\\" + username + "-Accounts.txt");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter website");
		String website = input.next();
		System.out.println("Enter username");
		String accountusername = input.next();
		System.out.println("Enter password");
		String password = input.next();
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(users, true));
			writer.print(website + "\t" + accountusername + "\t" + password);
			writer.println();
			writer.close();
			System.out.println("Account added successfully.");
		} catch (IOException e) {
			System.out.println("We couldn't create your account due to an error. Please try again." + "\n"
					+ "If the issue persists please contact us at support@lockme.com." + "\n"
					+ "We apologize for the inconvenience.");
			Logger.writeToLog(e.toString());
		}
	}

	public static void getUserAccounts(String username) {
		File userAccounts = new File("Data\\UserAccounts\\" + username + "-Accounts.txt");
		if (!userAccounts.exists()) {
			System.out.println("We couldn't find any file for your account."
					+ " If you have previosuly stored accounts please contact support@lockme.com about this error."
					+ " We apologize for the inconvenience.");
			return;
		}
		try {
			Scanner scanner = new Scanner(userAccounts);
			if (scanner.hasNext())
				System.out.println("Your Accounts" + "\n" + "----------");
			else
				System.out.println("You don't have any saved accounts.");
			while (scanner.hasNext()) {
				System.out.println("Website:" + scanner.next() + "\nUsername:" + scanner.next() + "\nPassword:"
						+ scanner.next() + "\n" + "----------");
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Logger.writeToLog(e.toString());
		}
	}

	public static void lockerOptions(String username) {

		System.out.println("=================================");
		System.out.println("|	" + username + "'s Locker	|");
		System.out.println("=================================");
		System.out.println("Choose one of the options below");
		System.out.println("1. Show my credentials");
		System.out.println("2. Add credentials");
		System.out.println("3. Delete my stored credentials");
		System.out.println("4. Return to Main menu");
		System.out.println("[Type the number and hit Enter to load the option]");
		boolean exit = false;
		while (!exit) {
			Scanner input = new Scanner(System.in);
			int userInput = input.nextInt();
			switch (userInput) {
			case 1:
				getUserAccounts(username);
				break;
			case 2:
				addUserAccount(username);
				break;
			case 3:
				deleteUserAndAccountFile(username);
				break;
			case 4:
				exit = true;
				break;
			default:
				System.out.print("Please choose one of the valid options 1,2,3, or 4 and hit Enter.");
			}
		}
	}

	public static void deleteUserAndAccountFile(String username) {
		File userAccountFile = new File("Data\\UserAccounts\\" + username + "-Accounts.txt");
		try {
			PrintWriter writer = new PrintWriter(userAccountFile);
			writer.close();
		} catch (FileNotFoundException e) {
			Logger.writeToLog(e.toString());
		}
		System.out.println("Your stored credentials were successfully deleted.");
	}
}
