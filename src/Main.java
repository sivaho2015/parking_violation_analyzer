import java.io.IOException;

import org.json.simple.parser.ParseException;

import data.MemoizedData;
import datamanagement.*;
import logging.Logger;
import processor.ActionProcessor;
import ui.InputValidator;
import ui.UserInteractor;

public class Main {

	public static void main(String args[]) {

		String exitString = "Exiting.Goodbye!";

		// Initialize logger.
		Logger.setLogFileName(args[4]);

		// Get an instance of logger.
		Logger logger = Logger.getInstance();
		
		// Log initial arguments.
		logger.logArgs(args);

		// Validate input arguments. Return if invalid.
		if (!InputValidator.validateArgs(args)) {
			return;
		}

		// Initialize the memoized data
		MemoizedData memoizedData = MemoizedData.getInstance();

		// Read the parking violations file
		ParkingViolationsFileParser parkingViolationsFileParser;
		if (args[0].equalsIgnoreCase("json")) {
			parkingViolationsFileParser = new JSONParkingViolationsFileParser(args[1], memoizedData);
		} else {
			parkingViolationsFileParser = new CSVParkingViolationsFileParser(args[1], memoizedData);
		}
		try {
			parkingViolationsFileParser.readParkingViolations();
		} catch (IOException | ParseException e) {
			System.out.println("Error while reading the parking violations file. Exiting.");
			System.out.println(exitString);
			return;
		}

		// Read the property values file
		PropertyValuesFileParser propertyValuesFileParser = new PropertyValuesFileParser(args[2], memoizedData);
		propertyValuesFileParser.readProperties();

		// Read the population values file
		PopulationFileParser populationFileParser = new PopulationFileParser(args[3], memoizedData);
		populationFileParser.parsePopulationFile();

		// Get input from the user
		UserInteractor userInteractor = new UserInteractor();
		ActionProcessor actionProcessor = new ActionProcessor();

		while (true) {
			int actionNumber = userInteractor.getNextActionFromUser();

			boolean isUserDone = actionProcessor.processAction(actionNumber);
			if (isUserDone) {
				System.out.println(exitString);
				return;
			}
		}
	}

}
