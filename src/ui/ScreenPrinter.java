package ui;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
/**
 * This class prints the data to the screen
 * 
 * @author shrutimirashi
 *
 */
public final class ScreenPrinter {

	/**
	 * Simply prints the given string data to the screen.
	 * 
	 * @param data a string provided by the user
	 */
	public static void print(String data) {
		System.out.println(data);
	}
	
	/**
	 * Simply prints the given int data to the screen.
	 * 
	 * @param data an int provided by the user
	 */
	public static void print(int data) {
		System.out.println(data);
	}
	

	public static void print(double data) {
		if (data == 0) {
			print(0);
			return;
		}		
		
		Double doubleVal = new Double(data);
        System.out.println(doubleVal.intValue());
        
	}

	public static void printTotalFinesPerCapita(Map<String, Double> parkingFinesPerCapita) {
		DecimalFormat df = new DecimalFormat("###0.0000");
        df.setRoundingMode(RoundingMode.DOWN);
    
		ArrayList<String> sortedKeys = 
                new ArrayList<String>(parkingFinesPerCapita.keySet());
		
		Collections.sort(sortedKeys);
		
		for (String zipCode: sortedKeys) {
			Double fine = parkingFinesPerCapita.get(zipCode);
			if (fine == 0) {
				continue;
			}
			System.out.print(zipCode);
			System.out.print(" ");
			System.out.println(df.format(fine));
		}
	}
}
