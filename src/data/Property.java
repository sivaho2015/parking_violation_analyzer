package data;
/**
 * This class represents a property that includes its market value, total livable area, and ZIP code.
 * @author sivah
 *
 */
public class Property {
	
	private int marketValue;
	private int totalLivableArea;
	private String ZIPCode;
	
	public Property(int marketValue, int totalLivableArea, String ZIPCode) {
		this.marketValue = marketValue;
		this.totalLivableArea = totalLivableArea;
		this.ZIPCode = ZIPCode;
	}

	public int getMarketValue() {
		return marketValue;
	}

	public int getTotalLivableArea() {
		return totalLivableArea;
	}

	public String getZIPCode() {
		return ZIPCode;
	}
	
}
