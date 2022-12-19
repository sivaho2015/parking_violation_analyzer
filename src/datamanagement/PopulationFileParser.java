package datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import data.MemoizedData;
import data.ZIPCodePopulation;
import logging.Logger;

/**
 * This class handles the reading of the population.txt file and exposes a
 * method to be used by the Processor to get the population in a ZIP code.
 * 
 * @author sivah
 *
 */
public class PopulationFileParser {

	protected String filename;
	protected MemoizedData memoizedData;
	private Logger logger;

	public PopulationFileParser(String filename, MemoizedData memoizedData) {
		this.filename = filename;
		this.memoizedData = memoizedData;
		this.logger = Logger.getInstance();
	}

	public void parsePopulationFile() {
		List<ZIPCodePopulation> statePopulation = new LinkedList<>();

		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
			// Log the filename.
			logger.log(filename);
		} catch (FileNotFoundException e1) {
			System.out.println("Population file not found. Exiting.");
			System.exit(1);
		}

		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] splitLine = line.split(" ");

			String ZIPCode = splitLine[0];
			String population = splitLine[1];
			int populationInt;
			try {
				populationInt = Integer.parseInt(population);
			} catch (NumberFormatException e) {
				continue;
			}

			ZIPCodePopulation zipPop = new ZIPCodePopulation(ZIPCode, populationInt);
			statePopulation.add(zipPop);
		}
		
		this.memoizedData.populationList = statePopulation;
	}
}
