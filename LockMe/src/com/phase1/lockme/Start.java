package com.phase1.lockme;

import java.util.Scanner;

public class Start {
	public static void main(String[] args) {

		System.out.println("=================================");
		System.out.println("|	Welcome to Lock Me!	|");
		System.out.println("=================================");
		System.out.println("[ - Developed by Naseer Shariff]");
		System.out.println("---------------------------------");
		System.out.println("Choose one of the options below");
		System.out.println("1. Show all user account files");
		System.out.println("2. Register new user");
		System.out.println("3. Login to see your accounts");
		System.out.println("[Type the number and hit Enter to load the option.]");
		Scanner input = new Scanner(System.in);

		Boolean isInputCorrect = false;
		try {
			while (!isInputCorrect) {
				int optionSelection = input.nextInt();

				switch (optionSelection) {
				case 1:
					isInputCorrect = true;

					break;
				case 2:
					isInputCorrect = true;
					User.registerUser();
					break;
				case 3:
					isInputCorrect = true;
					User.Login();
					break;
				default:
					System.out.println("Please type a valid option \"1\" or \"2\" without quotes.");
				}
			}

		} catch (Exception e) {
			Logger.writeToLog(e.toString());
			System.out.println("An error has occurred. Please restart the application. "
					+ "If this issue persists please contact support@lockme.com.");
		}

	}
}
