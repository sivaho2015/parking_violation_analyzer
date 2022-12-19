package data;
/**
 * This class represents the population in a ZIP code.
 * @author sivah
 *
 */
public class ZIPCodePopulation {
	
	private String ZIPCode;
	private int population;
	
	public ZIPCodePopulation(String ZIPCode, int population) {
		this.ZIPCode = ZIPCode;
		this.population = population;
	}

	public String getZIPCode() {
		return ZIPCode;
	}

	public int getPopulation() {
		return population;
	}
	
	
}
