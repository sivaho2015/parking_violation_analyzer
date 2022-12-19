package data;
/**
 * This class represents a parking violation that includes its fine, state, and ZIP code.
 * @author sivah
 *
 */
public class ParkingViolation {
	
	private int parkingFine;
	private String state;
	private String ZIPCode;
	
	public ParkingViolation(int parkingFine, String state, String ZIPCode) {
		this.parkingFine = parkingFine;
		this.state = state;
		this.ZIPCode = ZIPCode;
	}

	public int getParkingFine() {
		return parkingFine;
	}

	public String getState() {
		return state;
	}

	public String getZIPCode() {
		return ZIPCode;
	}
	
	
}
