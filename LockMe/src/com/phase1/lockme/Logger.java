package com.phase1.lockme;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

	public static void writeToLog(String logEntry) {
		File logFile = new File("Data\\Log\\App.log");
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(logFile, true));
			String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(("dd/MM/yyyy hh:mm a")))
					+ " (IST)";
			writer.println("------------------------ " + currentDateTime + " --------------------");
			writer.println(logEntry);
			writer.println("-----------------------------------------------------------------------");
			writer.println();
			writer.close();
		} catch (IOException e) {

		}
	}
}
