package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Singleton logger class.
 * 
 * @author shrutimirashi
 *
 */
public final class Logger {

	private static String fileName;

	private static PrintWriter writer;

	private static Logger INSTANCE;

	// Constructor is private to prevent instantiation from outside.
	private Logger() {
		try {
			writer = new PrintWriter(new FileWriter(new File(fileName), true));
		} catch (IOException e) {
			System.out.println("IOException while creating/opening the logfile");
		}
	}

	public static Logger getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Logger();
		}
		return INSTANCE;
	}

	public void log(String data) {	
		long timeStamp = System.currentTimeMillis();
		writer.printf("%d %s\n", timeStamp, data);
		
		writer.flush();
	}

	public static void setLogFileName(String logFileName) {
		if (fileName == null || fileName.isEmpty()) {
			fileName = logFileName;
		}
	}

	public void logArgs(String[] args) {
		String argsString = "";
		for (String arg : args) {
			argsString += arg + " ";
		}

		// Removing the last extra whitespace if there are any args.
		if (!argsString.isEmpty()) {
			argsString = argsString.substring(0, argsString.length() - 1);
		}

		log(argsString);
	}
}
