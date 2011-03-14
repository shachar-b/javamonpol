/**
 * 
 */
package players;

import monopoly.GameManager;
import monopoly.buyOffer;
import assets.Asset;
import assets.AssetGroup;
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
	public Boolean buyDecision(Asset asset) {
		String message;
		if (asset.getCost()<Balance)
		{
			message = "Would you like to buy " + (asset.getName()) + " in " + asset.getGroup().getNameOfGroup() + " for " + asset.getCost() + "?";
			return GameManager.CurrentUI.askYesNoQuestion(message);
		}
		else
		{
			GameManager.CurrentUI.notifyPlayerCantBuy(this, asset.getName(), asset.getCost());
			return false;
		}
	}

	@Override
	public Boolean buyHouseDecision(City asset)
	{
		if (asset.getCostOfHouse()<Balance) //Player can't buy if not enough cash!
		{
			String message = "Would you like to buy house number " + (asset.getNumHouses()+1) + " for " + asset.getCostOfHouse() + "?";
			return GameManager.CurrentUI.askYesNoQuestion(message);
		}
		else
		{
			GameManager.CurrentUI.notifyPlayerCantBuy(this, "house in " + asset.getName(), asset.getCostOfHouse());
			return false;
		}
	}

	@Override
	public boolean chooseToForfeit() {
		return GameManager.CurrentUI.askYesNoQuestion("Would you like to forfeit?");		
	}

	@Override
	public boolean hasGetOutOfJailFreeCard()
	{
		if (super.hasGetOutOfJailFreeCard())
			 return GameManager.CurrentUI.askYesNoQuestion("Would you like to you your get out of jail free card?");
		else return false;
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
			//TODO
		}
	}
	
	private buyOffer makeBuyOffer() {
		buyOffer offer=new buyOffer(this);
		
		
		
		return offer;
		
	}

	@Override
	public buyOffer makeBuyOffer(Asset asset) {
		GameManager.CurrentUI.notifyBidEvent(asset);
		return makeBuyOffer();
	}

	@Override
	public buyOffer makeBuyOffer(AssetGroup group) {
		GameManager.CurrentUI.notifyBidEvent(group);
		return makeBuyOffer();
	}
}
