package datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import data.MemoizedData;
import data.Property;
import logging.Logger;
/**
 * This class handles the reading of the properties.csv file and exposes a method
 * to be used by the Processor to get all properties.
 * @author sivah
 *
 */
public class PropertyValuesFileParser {
	
	protected String filename;
	protected MemoizedData memoizedData;
	private Logger logger;
	
	public PropertyValuesFileParser(String filename, MemoizedData memoizedData) {
		this.filename = filename;
		this.memoizedData = memoizedData;
		this.logger = Logger.getInstance();
	}
	
	public void readProperties() {
		
		List<Property> properties = new LinkedList<>();
		
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
			// Log the filename.
			logger.log(filename);
		} catch (FileNotFoundException e) {
			System.out.println("Properties file not found. Exiting.");
			System.exit(1);
		}
		
		// skip the column labels
		in.nextLine();
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] lineArr = lineParser(line);

			// column 34 = market_value, column 64 = livable_area, column 72 = ZIP code
			if (lineArr[34].length() == 0 || lineArr[64].length() == 0 || lineArr[72].length() < 5) {
				continue;
			}
			int marketValue = (int) Double.parseDouble(lineArr[34]);
			int livableArea = (int) Double.parseDouble(lineArr[64]);
			
			// only take in first five digits of the ZIP Code field
			char[] ZIPCodeArr = lineArr[72].toCharArray();
			String ZIPCode = "";
			for (int i = 0; i < 5; i++) {
				ZIPCode += ZIPCodeArr[i];
			}
			
			Property p = new Property(marketValue, livableArea, ZIPCode);
			
			properties.add(p);
			
		}
		
		this.memoizedData.properties = properties;
	}
	
	/**
	 * This method tokenizes lines read in from Scanner by commas outside of quotes.
	 * @param line
	 * @return array of String in the correct format
	 */
	private String[] lineParser(String line) {
		
		String[] modifiedColumn = new String[77];
		char[] charArr = line.toCharArray();
		
		int index = 0;
		String s = "";
		int quoteCount = 0;
		for (int i = 0; i < charArr.length; i++) {
			
			if (i == 0 && charArr[i] == ',') {
				modifiedColumn[index] = s;
				index++;
				continue;
			}
			
			if (charArr[i] == ',' && quoteCount == 0 && charArr[i-1] != '\"') {
				modifiedColumn[index] = s;
				s = "";
				index++;
				continue;
			}
			
			if (charArr[i] == ',' && charArr[i-1] == '\"' && quoteCount == 0) {
				continue;
			}
			
			if (charArr[i] == ',' && quoteCount > 0) {
				s += charArr[i];
				continue;
			}
			
			if (charArr[i] != ',' && charArr[i] != '\"') {
				s += charArr[i];
				continue;
			}
			
			if (charArr[i] == '\"' && quoteCount == 0) {
				quoteCount++;
				continue;
			}
			
			if (charArr[i] == '\"' && quoteCount > 0) {
				if (charArr[i+1] == ',' && quoteCount % 2 != 0) {
					modifiedColumn[index] = s;
					index++;
					s = "";
					quoteCount = 0;
					continue;
				}
				else {
					s += charArr[i];
					quoteCount++;
					continue;
				}
			}
		}
		
		return modifiedColumn;
	}
	
}
