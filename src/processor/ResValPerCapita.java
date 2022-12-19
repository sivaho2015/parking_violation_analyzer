package processor;

import data.MemoizedData;
/**
 * This class calculates total residential market value per capita for the zipcode entered by the user
 * 
 * @author shrutimirashi
 *
 */
public class ResValPerCapita {

	private MemoizedData memoizedData;
	private PopulationProcessor populationProcessor;
	private AvgMarketValueProcessor avgMarketValueProcessor;

	public ResValPerCapita(MemoizedData memoizedData, PopulationProcessor populationProcessor,
			AvgMarketValueProcessor avgMarketValueProcessor) {
		this.memoizedData = memoizedData;
		this.populationProcessor = populationProcessor;
		this.avgMarketValueProcessor = avgMarketValueProcessor;
	}

	public double getResidentialValuePerCapita(String zipCode) {
		if (this.memoizedData.zipcodePerCapitaResValueMap.containsKey(zipCode)) {
			return this.memoizedData.zipcodePerCapitaResValueMap.get(zipCode);
		}

		double value = calculatePerCapitaResValue(zipCode);
		this.memoizedData.zipcodePerCapitaResValueMap.put(zipCode, value);
		return value;
	}

	private double calculatePerCapitaResValue(String zipCode) {
		// Get total property value for this zip code.
		avgMarketValueProcessor.mapZIPtoMarketValue();
		if (!this.memoizedData.zipcodeToTotalMarketValue.containsKey(zipCode)) {
			return 0;
		}

		long totalMarketValueForZipCode = this.memoizedData.zipcodeToTotalMarketValue.get(zipCode);

		// Get population for this zip code.
		int population = populationProcessor.getPopulationByZIP(zipCode);

		return (double) totalMarketValueForZipCode / population;
	}
}
