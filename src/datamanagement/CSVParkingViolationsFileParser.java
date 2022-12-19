package datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import data.MemoizedData;
import data.ParkingViolation;
import logging.Logger;
/**
 * This class handles the reading of the parking CSV file and exposes a method
 * to be used by the Processor to get all the violations.
 * @author sivah
 *
 */
public class CSVParkingViolationsFileParser implements ParkingViolationsFileParser {
	
	protected String filename;
	private MemoizedData memoizedData;
	private Logger logger;
	
	public CSVParkingViolationsFileParser(String filename, MemoizedData memoizedData) {
		this.filename = filename;
		this.memoizedData = memoizedData;
		this.logger = Logger.getInstance();
	}
	
	@Override
	public void readParkingViolations() {
		
		List<ParkingViolation> violations = new LinkedList<>();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
			// Log the filename.
			logger.log(filename);
		} catch (FileNotFoundException e1) {
			System.out.println("Parking violations file not found. Exiting.");
			System.exit(1);
		}
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] columnData = line.split(",", 7);
			
			int parkingFine;
			try {
				parkingFine = Integer.parseInt(columnData[1]);
			}
			catch (NumberFormatException e){
				continue;
			}
			
			String state = columnData[4];
			String ZIPCode = columnData[6].length() == 0 ? "null" : columnData[6];
			
			if (ZIPCode.equals("null") || !columnData[4].equals("PA")) {
				continue;
			}
			
			ParkingViolation pv = new ParkingViolation(parkingFine, state, ZIPCode);
			
			violations.add(pv);
		}
		
		in.close();
		
		this.memoizedData.parkingViolations = violations;
	}
	
}
