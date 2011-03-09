/**
 * 
 */
package players;

import assets.Asset;
import assets.City;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class ComputerPlayer extends Player {

	private static final int BUY_THRESHHOLD=300;// the minimal amount of funds to leave in balance when buying something
	private static int generatedComputerNumber=1;

	/**
	 * 
	 */
	public ComputerPlayer() {
		super("Computer " + generatedComputerNumber);
		generatedComputerNumber++;
	}

	/* (non-Javadoc)
	 * @see players.Player#buyDecision(java.lang.String, assets.Asset, int)
	 */
	@Override
	public Boolean buyDecision(Asset asset, int cost) {
		if( Balance-cost>= BUY_THRESHHOLD )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public Boolean buyHouseDecision(City asset, int cost)
	{
		return buyDecision(asset, cost);
	}

@Override
public boolean chooseToForfeit() {
	return false; //Computer is not a quitter!
}

@Override
public void makeSellOffers() {
	return; //Computer is not a sellout!
	
}

}
