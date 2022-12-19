package processor;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.MemoizedData;
import data.Property;
import datamanagement.PropertyValuesFileParser;

/**
 * This class calculates the average livable area in a ZIP code.
 * 
 * @author sivah
 *
 */
public class AvgLivableAreaProcessor implements AverageProcessor {

	protected NumOfResidencesProcessor resProcessor;
	private MemoizedData memoizedData;

	public AvgLivableAreaProcessor(NumOfResidencesProcessor numOfResidencesProcessor, MemoizedData memoizedData) {
		this.memoizedData = memoizedData;
		this.resProcessor = numOfResidencesProcessor;
	}

	private void mapZIPtoLivableAreas() {
		// Do nothing if already populated.
		if (this.memoizedData.ZIPtoTotalLivableAreas.size() != 0) {
			return;
		}
		for (Property p : this.memoizedData.properties) {
			if (this.memoizedData.ZIPtoTotalLivableAreas.containsKey(p.getZIPCode())) {
				this.memoizedData.ZIPtoTotalLivableAreas.put(p.getZIPCode(),
						this.memoizedData.ZIPtoTotalLivableAreas.get(p.getZIPCode()) + p.getTotalLivableArea());
			} else {
				this.memoizedData.ZIPtoTotalLivableAreas.put(p.getZIPCode(), p.getTotalLivableArea());
			}
		}
	}

	private double calculateAverageLivableArea(String ZIPCode) {
		double average = 0;

		if (!this.memoizedData.ZIPtoTotalLivableAreas.containsKey(ZIPCode)) {
			return 0;
		}
		double livableArea = this.memoizedData.ZIPtoTotalLivableAreas.get(ZIPCode);
		int numOfRes = resProcessor.getNumOfResidencesByZIP(ZIPCode);

		average = (double) livableArea / numOfRes;

		return average;
	}

	@Override
	public double getAverageData(String ZIPCode) {
		if (this.memoizedData.zipcodeToAvgLivableAreaMap.containsKey(ZIPCode)) {
			return this.memoizedData.zipcodeToAvgLivableAreaMap.get(ZIPCode);
		} else {
			mapZIPtoLivableAreas();
			double average = calculateAverageLivableArea(ZIPCode);
			this.memoizedData.zipcodeToAvgLivableAreaMap.put(ZIPCode, average);
			return average;
		}
	}

}
