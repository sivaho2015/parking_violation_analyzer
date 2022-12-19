package datamanagement;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

import data.MemoizedData;
import data.ParkingViolation;
import logging.Logger;
/**
 * This class handles the reading of the parking JSON file and exposes a method
 * to be used by the Processor to get all the violations.
 * @author sivah
 *
 */
public class JSONParkingViolationsFileParser implements ParkingViolationsFileParser {
	
	protected String filename;
	private MemoizedData memoizedData;
	private Logger logger;
	
	public JSONParkingViolationsFileParser(String filename, MemoizedData memoizedData) {
		this.filename = filename;
		this.memoizedData = memoizedData;
		this.logger = Logger.getInstance();
	}

	@Override
	public void readParkingViolations() throws IOException, ParseException {
		
		List<ParkingViolation> violations = new LinkedList<>();
		
		JSONParser parser = new JSONParser();
		
		JSONArray violationArr = (JSONArray) parser.parse(new FileReader(filename));
		
		logger.log(filename);

		Iterator iter = violationArr.iterator();
		
		while (iter.hasNext()) {
			
			JSONObject violation = (JSONObject) iter.next();
			
			int fine;
			try {
				fine = Integer.parseInt(violation.get("fine").toString());
			}
			catch (NumberFormatException e) {
				continue;
			}
 
			String state = violation.get("state").toString();
			String ZIPCode = violation.get("zip_code").toString();
			
			if (ZIPCode.length() == 0 || !state.equals("PA")) {
				continue;
			}
			
			ParkingViolation pv = new ParkingViolation(fine, state, ZIPCode);
			
			violations.add(pv);
			
		}
		
		this.memoizedData.parkingViolations = violations;
	}
	
}
