/**
 * 
 */
package players;

import java.util.ArrayList;

import monopoly.GameManager;
import monopoly.buyOffer;
import ui.OfferType;
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
			message = "Would you like to buy " + (asset.getName()) + " in " + asset.getGroup().getName() + " for " + asset.getCost() + "?";
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
		buyOffer helperOffer = new buyOffer(this);
		if (getAssetList().size()>0)
		{
			while (getAssetList().size()>0 && offersMade<GameManager.MAX_NUM_OF_SELL_OFFERS)
			{
				if (GameManager.CurrentUI.askYesNoQuestion("Would you like to sell an asset?"))
				{
					GameManager.CurrentUI.printAssetList(this);
					if (!this.tradeableGroups().isEmpty())
					{
						if (GameManager.CurrentUI.askYesNoQuestion("Would you like to sell an asset group?"))
						{
							GameManager.CurrentUI.askOfferableSellQuestions(this, helperOffer, OfferType.Groups, false);
							if (!helperOffer.getAssetGroups().isEmpty())
								sellAssetGroup(helperOffer.getAssetGroups().get(0));
							else
								offersMade--;							
						}
					}
					else if (GameManager.CurrentUI.askYesNoQuestion("Would you like to sell a single asset?"))
					{
						GameManager.CurrentUI.askOfferableSellQuestions(this, helperOffer, OfferType.Assets, false);
						if (!helperOffer.getSingleAssets().isEmpty())
							sellAsset(helperOffer.getSingleAssets().get(0));
						else
							offersMade--; //Offer canceled by user
					}					
					offersMade++;
				}
				else
					break;
			}
			if (getAssetList().size()==0)
				GameManager.CurrentUI.notifyPlayerOutOfAssets(this);
			if (offersMade==GameManager.MAX_NUM_OF_SELL_OFFERS)
				GameManager.CurrentUI.notifyPlayerExceededSellOfferCount(this);
		}
	}
	
	private buyOffer makeBuyOffer() {
		buyOffer offer=new buyOffer(this);
		if (GameManager.CurrentUI.askYesNoQuestion("Would you like to buy?"))
		{
			if(GameManager.CurrentUI.askYesNoQuestion("Would you like to offer money?"))
				offer.addToOffer(GameManager.CurrentUI.askNumericQuestion("How much would you like to offer?"));

			if (!this.tradeableGroups().isEmpty())
				if (GameManager.CurrentUI.askYesNoQuestion("Would you like to offer asset groups?"))
				{
					GameManager.CurrentUI.askOfferableSellQuestions(this, offer, OfferType.Groups,true);
				}

			if (!this.tradeableAssets().isEmpty())
				if (GameManager.CurrentUI.askYesNoQuestion("Would you like to offer single assets?"))
				{
					GameManager.CurrentUI.askOfferableSellQuestions(this, offer, OfferType.Assets,true);
				}
		}
		else
			offer.addToOffer(0);
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

	@Override
	protected int chooseWinningOffer(ArrayList<buyOffer> buyOffers) {
		// TODO Auto-generated method stub
		return -1;
	}
}
