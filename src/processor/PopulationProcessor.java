package processor;


import data.*;

/**
 * This class processes data regarding populations in each ZIP code.
 * 
 * @author sivah
 *
 */
public class PopulationProcessor {

	private MemoizedData memoizedData;

	public PopulationProcessor(MemoizedData memoizedData) {
		this.memoizedData = memoizedData;
	}

	/**
	 * This method returns the total population for all ZIP codes.
	 * 
	 * @return
	 */
	public int getTotalPopulation() {
		if (this.memoizedData.totalPopulation == 0) {
			calculateTotalPopulation();
		}
		return this.memoizedData.totalPopulation;
	}

	/**
	 * This method calculates the total population for all ZIP codes.
	 */
	private void calculateTotalPopulation() {

		for (ZIPCodePopulation p : this.memoizedData.populationList) {
			this.memoizedData.totalPopulation += p.getPopulation();
		}

	}

	/**
	 * This method maps ZIP code to its population.
	 * 
	 * @return
	 */
	private void createPopulationMap() {
		for (ZIPCodePopulation p : this.memoizedData.populationList) {
			this.memoizedData.zipcodeToPopulationMap.put(p.getZIPCode(), p.getPopulation());
		}
	}

	/**
	 * This method returns population by ZIP code.
	 * 
	 * @param ZIPCode
	 * @return
	 */
	public int getPopulationByZIP(String ZIPCode) {
		if (this.memoizedData.zipcodeToPopulationMap.size() == 0) {
			createPopulationMap();
		}

		if (this.memoizedData.zipcodeToPopulationMap.get(ZIPCode) == null) {
			return 0;
		}
		return this.memoizedData.zipcodeToPopulationMap.get(ZIPCode);
	}

}
