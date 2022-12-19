
package ui;

import java.io.File;
/**
 * This class checks the validity of the input
 * 
 * @author shrutimirashi
 *
 */

import java.io.File;

public final class InputValidator {

  	//check number of arguments
	public static boolean validateArgs(String[] args) {
		if (args.length != 5) {
			System.out.println("Please pass exactly 5 arguments. Exiting.");
			return false;
		}

		if (!(args[0].toLowerCase().equals("json") || args[0].toLowerCase().equals("csv"))) {
			System.out.println("First argument must be either 'json' or 'csv'. Exiting.");
			return false;
		}

		// Return if can't open the parking violations file.
		File parkingViolationsFile = new File(args[1]);
		try {
			if (!parkingViolationsFile.canRead()) {
				System.out.println("Can't open parking violations file - " + args[1] + ". Exiting.");
				return false;
			}
		} catch (SecurityException e) {
			System.out.println("Can't open parking violations file - " + args[1] + ". Exiting.");
			return false;
		}

		// Return if can't open the property values file.
		File propertyValuesFile = new File(args[2]);
		try {
			if (!propertyValuesFile.canRead()) {
				System.out.println("Can't open property values file - " + args[2] + ". Exiting.");
				return false;
			}
		} catch (SecurityException e) {
			System.out.println("Can't open property values file - " + args[2] + ". Exiting.");
			return false;
		}
    
		// Return if can't open population file.
		File populationFile = new File(args[3]);
		try {
			if (!populationFile.canRead()) {
				System.out.println("Can't open population file - " + args[3] + ". Exiting.");
				return false;
			}
		} catch (SecurityException e) {
			System.out.println("Can't open population file - " + args[3] + ". Exiting.");
			return false;
		}

		return true;
	}

}
