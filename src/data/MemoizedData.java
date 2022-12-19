package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a singleton class that memoizes the data for faster processing.
 * 
 * @author shrutimirashi
 *
 */
public class MemoizedData {

	private static MemoizedData INSTANCE = null;
	
	public List<ZIPCodePopulation> populationList;
	public int totalPopulation = 0;
	public Map<String, Integer> zipcodeToPopulationMap = new HashMap<>();
	
	public List<ParkingViolation> parkingViolations;
	public Map<String, Integer> zipcodeToTotalFine = new HashMap<>();
	public Map<String, Double> zipcodeToPerCapitaFine = new HashMap<>();
	
	public List<Property> properties;
	public Map<String, Double> zipcodeToAvgMarketValueMap = new HashMap<>();
	public Map<String, Double> zipcodeToAvgLivableAreaMap = new HashMap<>();
	public Map<String, Long> zipcodeToTotalMarketValue = new HashMap<>();
	public Map<String, Integer> ZIPtoTotalLivableAreas = new HashMap<>();
	public Map<String, Integer> ZIPtoNumOfResidences = new HashMap<>();
	public Map<String, Double> zipcodePerCapitaResValueMap = new HashMap<>();

	private MemoizedData() {}
	
	public static MemoizedData getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MemoizedData();
		}
		return INSTANCE;
	}
}
