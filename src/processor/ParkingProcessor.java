package processor;

import java.util.Map;

import data.MemoizedData;
import data.ParkingViolation;
import datamanagement.ParkingViolationsFileParser;

/**
 * This class processes data regarding parking fines.
 * 
 * @author sivah
 *
 */
public class ParkingProcessor {

	protected ParkingViolationsFileParser parkingReader;
	protected PopulationProcessor populationProcessor;
	private MemoizedData memoizedData;

	public ParkingProcessor(PopulationProcessor populationProcessor, MemoizedData memoizedData) {
		this.populationProcessor = populationProcessor;
		this.memoizedData = memoizedData;
	}

	/**
	 * This method maps ZIP code to its total fines.
	 * 
	 */
	public void mapZIPToTotalFine() {
		// Already memoized, no work required.
		if (this.memoizedData.zipcodeToTotalFine.size() != 0) {
			return;
		}
		for (ParkingViolation v : this.memoizedData.parkingViolations) {

			if (this.memoizedData.zipcodeToTotalFine.containsKey(v.getZIPCode())) {
				this.memoizedData.zipcodeToTotalFine.put(v.getZIPCode(),
						this.memoizedData.zipcodeToTotalFine.get(v.getZIPCode()) + v.getParkingFine());
			} else {
				this.memoizedData.zipcodeToTotalFine.put(v.getZIPCode(), v.getParkingFine());

			}
		  }
		}



	/**
	 * This method calculates the fines per capita.
	 * 
	 * @param ZIPCode
	 * @return
	 */
	private double calculateTotalFinesPerCapita(String ZIPCode) {

		double result = 0;

		int population = this.populationProcessor.getPopulationByZIP(ZIPCode);

		// No population for this zip code, returning zero.
		if (population == 0) {
			return 0;
		}

		int fines = this.memoizedData.zipcodeToTotalFine.get(ZIPCode);
		// getTotalFineByZIP(ZIPCode);

		result = (double) fines / population;

		return result;
	}

	

	public Map<String, Double> getParkingFinesPerCapita() {
		if (this.memoizedData.zipcodeToPerCapitaFine.size() != 0) {
			return this.memoizedData.zipcodeToPerCapitaFine;
		}

		mapZIPToTotalFine();

		for (String zipCode : this.memoizedData.zipcodeToTotalFine.keySet()) {
			double finePerCapita = calculateTotalFinesPerCapita(zipCode);
			this.memoizedData.zipcodeToPerCapitaFine.put(zipCode, finePerCapita);
		}

		return this.memoizedData.zipcodeToPerCapitaFine;
	}

}
