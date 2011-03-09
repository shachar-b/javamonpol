/**
 * 
 */
package players;

import monopoly.GameManager;
import assets.Asset;
import assets.City;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class HumanPlayer extends Player {

	/**
	 * 
	 */
	public HumanPlayer() {
		super(GameManager.CurrentUI.askName());
	}

	/* (non-Javadoc)
	 * @see players.Player#buyDecision(java.lang.String, assets.Asset, int)
	 */
	@Override
	public Boolean buyDecision(Asset asset, int cost) {
		return true; //GameSettings.CurrentUI.
	}
	
	@Override
	public Boolean buyHouseDecision(City asset, int cost)
	{
		if (cost<Balance) //Player can't buy if not enough cash!
		{
			String message = "Would you like to buy house number " + (asset.getNumHouses()+1) + " for " + asset.getCostOfHouse() + "?";
			return GameManager.CurrentUI.askYesNoQuestion(message);
		}
		else return false;
	}

	@Override
	public boolean chooseToForfeit() {
		return GameManager.CurrentUI.askYesNoQuestion("Would you like to forfeit?");		
	}

	@Override
	public void makeSellOffers()
	{
		int offersMade = 0;
		if (getAssetList().size()>0)
		{
			while (getAssetList().size()>0 && offersMade<GameManager.MAX_NUM_OF_SELL_OFFERS)
			{
				if (GameManager.CurrentUI.askYesNoQuestion("Would you like to sell an asset?"))
				{
					GameManager.CurrentUI.printAssetList(this);
					int assetIndex = GameManager.CurrentUI.askNumericQuestion("Which assest would you like to sell?");
					sellAsset(getAssetList().get(assetIndex-1));
					offersMade++;
				}
				else
					break;
			}
			if (getAssetList().size()==0)
				GameManager.CurrentUI.displayMessage("No assets left to sell!");
			if (offersMade==GameManager.MAX_NUM_OF_SELL_OFFERS)
				GameManager.CurrentUI.displayMessage("Max number of offers exceeded!");
		}
	}
}
