package processor;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.MemoizedData;
import data.Property;
import datamanagement.PropertyValuesFileParser;

/**
 * This class exposes a method that calculates the number of residences in a ZIP
 * code area.
 * 
 * @author sivah
 *
 */
public class NumOfResidencesProcessor {

	private MemoizedData memoizedData;

	public NumOfResidencesProcessor(MemoizedData memoizedData) {
		this.memoizedData = memoizedData;
	}

	private void mapZIPtoNumResidences() {
		for (Property p : this.memoizedData.properties) {
			if (this.memoizedData.ZIPtoNumOfResidences.containsKey(p.getZIPCode())) {
				this.memoizedData.ZIPtoNumOfResidences.put(p.getZIPCode(),
						this.memoizedData.ZIPtoNumOfResidences.get(p.getZIPCode()) + 1);
			} else {
				this.memoizedData.ZIPtoNumOfResidences.put(p.getZIPCode(), 1);
			}
		}
	}

	public int getNumOfResidencesByZIP(String ZIPCode) {
		if (this.memoizedData.ZIPtoNumOfResidences.size() == 0) {
			mapZIPtoNumResidences();
		}
		return this.memoizedData.ZIPtoNumOfResidences.get(ZIPCode);
	}

}
