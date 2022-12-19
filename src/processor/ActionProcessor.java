package processor;

import data.MemoizedData;
import ui.ScreenPrinter;
import ui.UserInteractor;
/**
 * This class processes the action(0 to 6) selected by the user
 * 
 * @author shrutimirashi
 *
 */
public class ActionProcessor {

	private MemoizedData memoizedData;
	private PopulationProcessor populationProcessor;
	private ParkingProcessor parkingProcessor;
	private AverageProcessor averageProcessor;
	private NumOfResidencesProcessor numOfResidencesProcessor;
	private AvgMarketValueProcessor avgMarketValueProcessor;
	private UserInteractor userInteractor;
	private AvgLivableAreaProcessor avgLivableAreaProcessor;
	private ResValPerCapita resValPerCapita;
	private AdditionalFeatureProcessor additionalFeatureProcessor;

	public ActionProcessor() {
		this.memoizedData = MemoizedData.getInstance();
		this.populationProcessor = new PopulationProcessor(memoizedData);
		this.parkingProcessor = new ParkingProcessor(populationProcessor, memoizedData);
		this.numOfResidencesProcessor = new NumOfResidencesProcessor(memoizedData);
		this.avgMarketValueProcessor = new AvgMarketValueProcessor(numOfResidencesProcessor, memoizedData);
		this.avgLivableAreaProcessor = new AvgLivableAreaProcessor(numOfResidencesProcessor, memoizedData);
		this.userInteractor = new UserInteractor();
		this.resValPerCapita = new ResValPerCapita(memoizedData, populationProcessor, avgMarketValueProcessor);
		this.additionalFeatureProcessor = new AdditionalFeatureProcessor(memoizedData, populationProcessor,
				avgMarketValueProcessor, parkingProcessor);
	}

	/**
	 * Processes the given number as an action and does the required work.
	 *
	 * @param actionNumber
	 * @return true if the program needs to exit, false otherwise
	 */
	public boolean processAction(int actionNumber) {
		switch (actionNumber) {
		case 0:
			return true;
		case 1:
			ScreenPrinter.print(this.populationProcessor.getTotalPopulation());
			return false;
		case 2:
			ScreenPrinter.printTotalFinesPerCapita(parkingProcessor.getParkingFinesPerCapita());
			return false;
		case 3:
			this.averageProcessor = this.avgMarketValueProcessor;
			ScreenPrinter.print(this.averageProcessor.getAverageData(this.userInteractor.getZipCodeFromUser()));
			return false;
		case 4:
			this.averageProcessor = this.avgLivableAreaProcessor;
			ScreenPrinter.print(this.averageProcessor.getAverageData(this.userInteractor.getZipCodeFromUser()));
			return false;
		case 5:
			ScreenPrinter
					.print(this.resValPerCapita.getResidentialValuePerCapita(this.userInteractor.getZipCodeFromUser()));
			return false;
		case 6:
			ScreenPrinter.print(this.additionalFeatureProcessor.getResidentialValuePerCapitaForZipWithHighestFine());
			return false;
		default:
			return false;
		}
	}

}
