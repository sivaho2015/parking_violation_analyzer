
package ui;

import java.util.Scanner;

import logging.Logger;

/**
 * This class is in-charge of user interactions. Accepts user input, formats and
 * prints out messages to the user on the screen.
 * 
 * @author shrutimirashi
 *
 */
public class UserInteractor {

	Scanner userIn = new Scanner(System.in);
	Logger logger = Logger.getInstance();

	/**
	 * Gets the input Number from user.
	 */
	public int getNextActionFromUser() {
		int inputNumber = 0; // Any number from 0 to 6
		boolean runLoop = true;
		while (runLoop) {
			System.out.print("Please enter a valid 1 digit number from 0 to 6 for performing an action: ");
			String input = userIn.nextLine();
			
			logger.log(input);

			try {
				inputNumber = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Looks like you entered something that's not a number. Let's try again.");
				continue;
			}

			if (inputNumber < 0 || inputNumber > 6) {
				System.out.println("Looks like you did not enter a valid number. Let's try again.");
				continue;
			}

			// Valid input, break out of the loop.
			runLoop = false;
		}
		return inputNumber;
	}
	
	public String getZipCodeFromUser() {
		System.out.print("\tPlease enter a valid PA zip code: ");
		String zipCode = userIn.nextLine();
		logger.log(zipCode);
		return zipCode;
	}

}
