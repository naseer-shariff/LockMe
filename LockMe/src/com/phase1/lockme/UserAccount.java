package com.phase1.lockme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
			boolean exit = false;
			while (!exit) {
				System.out.println("Press Enter to go back to the menu.");
				System.in.read();
				exit = true;
			}
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
			boolean exit = false;
			while (!exit) {
				System.out.println("Press Enter to go back to the menu.");
				System.in.read();
				exit = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Logger.writeToLog(e.toString());
		}
	}

	public static void lockerOptions(String username) {
		boolean exit = false;
		while (!exit) {
			System.out.println("=================================");
			System.out.println("|	" + username + "'s Locker	|");
			System.out.println("=================================");
			System.out.println("Choose one of the options below");
			System.out.println("1. Show my credentials");
			System.out.println("2. Add credentials");
			System.out.println("3. Delete my stored credentials");
			System.out.println("4. Return to Main menu");
			System.out.println("[Type the number and hit Enter to load the option]");
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
			System.out.println("Your stored credentials were deleted successfully.");
			boolean exit = false;
			while (!exit) {
				System.out.println("Press Enter to go back to the menu.");
				System.in.read();
				exit = true;
			}
		} catch (FileNotFoundException e) {
			Logger.writeToLog(e.toString());
		} catch (IOException e) {
			Logger.writeToLog(e.toString());
		}

	}

	public static void getAllUserAccountFilenames() {

		Scanner input = new Scanner(System.in);
		File folder = new File("Data\\UserAccounts");
		String[] fileNames = folder.list();
		mergeSort(fileNames, 0, fileNames.length - 1);
		System.out.println("---- User Account Files [Ascending Order done by Merge Sort]-----");
		for (String fileName : fileNames)
			System.out.println(fileName);
		System.out.println("------------------------");
		Boolean exit = false;
		while (!exit) {
			System.out.println("Press Enter to go back to the menu.");
			try {
				System.in.read();
				exit = true;
			} catch (IOException e) {
				Logger.writeToLog(e.toString());
			}

		}

	}

	private static void mergeSort(String[] fileNames, int start, int end) {
		if (start == end) {
			return;
		}
		int mid = (start + end) / 2;
		// Sort the first and the second half
		mergeSort(fileNames, start, mid);
		mergeSort(fileNames, mid + 1, end);
		merge(fileNames, start, mid, end);
	}

	private static void merge(String[] a, int start, int mid, int end) {
		int n = end - start + 1; // Size of the range to be merged
		String[] temp = new String[n]; // Merge both halves into a temporary array temp
		int i1 = start; // Next element to consider in the first range
		int i2 = mid + 1; // Next element to consider in the second range
		int j = 0; // Next open position in temp

		while (i1 <= mid && i2 <= end) {
			if (a[i1].compareTo(a[i2]) < 0) {
				temp[j] = a[i1];
				i1++;
			} else {
				temp[j] = a[i2];
				i2++;
			}
			j++;
		}

		// Copy any remaining entries of the first half
		while (i1 <= mid) {
			temp[j] = a[i1];
			i1++;
			j++;
		}

		// Copy any remaining entries of the second half
		while (i2 <= end) {
			temp[j] = a[i2];
			i2++;
			j++;
		}

		// Copy back from the temporary array
		for (j = 0; j < n; j++) {
			a[start + j] = temp[j];
		}
	}
}
