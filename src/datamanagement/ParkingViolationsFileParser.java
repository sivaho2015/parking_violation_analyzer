package datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.parser.ParseException;

import data.ParkingViolation;
/**
 * This interface declares a method to get all parking violations.
 * @author sivah
 *
 */
public interface ParkingViolationsFileParser {
	
	public void readParkingViolations() throws IOException, ParseException;
		
}
