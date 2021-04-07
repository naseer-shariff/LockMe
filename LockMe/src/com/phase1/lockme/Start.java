package com.phase1.lockme;

import java.util.Scanner;

public class Start {
	public static void main(String[] args) {
		Boolean exit = false;
		while (!exit) {
			System.out.println("=================================");
			System.out.println("|	Welcome to Lock Me!	|");
			System.out.println("=================================");
			System.out.println("[ - Developed by Naseer Shariff]");
			System.out.println("---------------------------------");
			System.out.println("Choose one of the options below");
			System.out.println("1. Show all user account files");
			System.out.println("2. Register new user");
			System.out.println("3. Login to see your accounts");
			System.out.println("4. Exit");
			System.out.println("[Type the number and hit Enter to load the option.]");
			Scanner input = new Scanner(System.in);

			try {
				String optionSelection = input.next();
				switch (optionSelection) {
				case "1":
					UserAccount.getAllUserAccountFilenames();
					break;
				case "2":
					User.registerUser();
					break;
				case "3":
					User.Login();
					break;
				case "4":
					System.out.println("Application exited.");
					exit = true;
					break;
				default:
					System.out.println("Please type a valid option \"1\", \"2\", \"3\", or \"4\" without quotes.");
				}
			}

			catch (Exception e) {
				Logger.writeToLog(e.toString());
				System.out.println("An error has occurred. Please restart the application. "
						+ "If this issue persists please contact support@lockme.com.");
			}

		}
	}
}
