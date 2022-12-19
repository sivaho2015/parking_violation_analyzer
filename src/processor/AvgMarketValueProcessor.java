package processor;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.MemoizedData;
import data.Property;
import datamanagement.PropertyValuesFileParser;

/**
 * This class calculates the average market value in a ZIP code.
 * 
 * @author sivah
 *
 */
public class AvgMarketValueProcessor implements AverageProcessor {

	protected NumOfResidencesProcessor resProcessor;
	private MemoizedData memoizedData;

	public AvgMarketValueProcessor(NumOfResidencesProcessor numOfResidencesProcessor, MemoizedData memoizedData) {
		this.memoizedData = memoizedData;
		this.resProcessor = numOfResidencesProcessor;
	}

	public void mapZIPtoMarketValue() {
		// Do nothing if already populated.
		if (this.memoizedData.zipcodeToTotalMarketValue.size() != 0) {
			return;
		}
		for (Property p : this.memoizedData.properties) {
			if (this.memoizedData.zipcodeToTotalMarketValue.containsKey(p.getZIPCode())) {
				this.memoizedData.zipcodeToTotalMarketValue.put(p.getZIPCode(),
						(this.memoizedData.zipcodeToTotalMarketValue.get(p.getZIPCode()) + p.getMarketValue()));
			} else {
				this.memoizedData.zipcodeToTotalMarketValue.put(p.getZIPCode(), (long) p.getMarketValue());
			}
		}
	}

	private double calculateAverageMarketValue(String ZIPCode) {
		double average = 0;

		if (!this.memoizedData.zipcodeToTotalMarketValue.containsKey(ZIPCode)) {
			return 0;
		}
		long marketValue = this.memoizedData.zipcodeToTotalMarketValue.get(ZIPCode);
		int numOfRes = resProcessor.getNumOfResidencesByZIP(ZIPCode);

		average = (double) marketValue / numOfRes;

		return average;
	}

	@Override
	public double getAverageData(String ZIPCode) {
		if (this.memoizedData.zipcodeToAvgMarketValueMap.containsKey(ZIPCode)) {
			return this.memoizedData.zipcodeToAvgMarketValueMap.get(ZIPCode);
		} else {
			mapZIPtoMarketValue();
			double average = calculateAverageMarketValue(ZIPCode);
			this.memoizedData.zipcodeToAvgMarketValueMap.put(ZIPCode, average);
			return average;
		}
	}

}
