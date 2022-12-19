package processor;

import data.MemoizedData;
/**
 * This class finds the total residential market value per capita of the zip code with highest total fine.
 * 
 * @author shrutimirashi
 *
 */
public class AdditionalFeatureProcessor {

	private MemoizedData memoizedData;
	private ResValPerCapita resValPerCapita;
	private ParkingProcessor parkingProcessor;

	public AdditionalFeatureProcessor(MemoizedData memoizedData, PopulationProcessor populationProcessor,
			AvgMarketValueProcessor avgMarketValueProcessor, ParkingProcessor parkingProcessor) {
		this.memoizedData = memoizedData;
		this.resValPerCapita = new ResValPerCapita(memoizedData, populationProcessor, avgMarketValueProcessor);
		this.parkingProcessor = parkingProcessor;
	}

	public double getResidentialValuePerCapitaForZipWithHighestFine() {
		// Find zipcode with highest total fine.
		String zipCode = findZipWithHighestTotalFine();

		// Get the residential value per capita for that zip code.
		return this.resValPerCapita.getResidentialValuePerCapita(zipCode);

	}

	private String findZipWithHighestTotalFine() {
		String highestZipCode = "";
		int highestFine = 0;

		// Initialize the zipcode to total fine map, in case it has been been yet.
		parkingProcessor.mapZIPToTotalFine();

		// Find the zipcode with highest fine.
		for (String currentZipCode : this.memoizedData.zipcodeToTotalFine.keySet()) {

			int currentFine = this.memoizedData.zipcodeToTotalFine.get(currentZipCode);

			if (currentFine > highestFine) {
				highestFine = currentFine;
				highestZipCode = currentZipCode;
			}
		}
		return highestZipCode;
	}

}
