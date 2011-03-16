/**
 * 
 */
package players;

import java.util.ArrayList;

import monopoly.GameManager;
import monopoly.buyOffer;
import ui.OfferType;
import assets.Asset;
import assets.City;
import assets.Offerable;

/**
 * class HumanPlayer extends Player
 * @see Player
 * @visibility public
 * A human player in the Monopoly game.
 * @author Omer Shenhar and Shachar Butnaro
 */
public class HumanPlayer extends Player {

	/**
	 * Constructor for human player.
	 * Gets a name from the UI and transfers it the the super constructor.
	 */
	public HumanPlayer() {
		super(GameManager.CurrentUI.askName());
	}

	/* (non-Javadoc)
	 * @see players.Player#buyDecision(java.lang.String, assets.Asset, int)
	 * Prompts the player to choose.
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

	/* (non-Javadoc)
	 * @see players.Player#buyHouseDecision(assets.City)
	 * Prompts the player to choose.
	 */
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

	/* (non-Javadoc)
	 * @see players.Player#chooseToForfeit()
	 * Prompts the player to choose.
	 */
	@Override
	public boolean chooseToForfeit() {
		return GameManager.CurrentUI.askYesNoQuestion("Would you like to forfeit?");		
	}

	/* (non-Javadoc)
	 * @see players.Player#hasGetOutOfJailFreeCard()
	 * If player has a get out of jail free card, he is asked if he wants to use it.
	 */
	@Override
	public boolean hasGetOutOfJailFreeCard()
	{
		if (super.hasGetOutOfJailFreeCard())
			return GameManager.CurrentUI.askYesNoQuestion("Would you like to you your get out of jail free card?");
		else return false;
	}

	/* (non-Javadoc)
	 * @see players.Player#makeSellOffers()
	 * Prompts the user for relevant choices when selling an asset/group.
	 */
	@Override
	public void makeSellOffers()
	{
		int offersMade = 0;
		ArrayList<Offerable>  assetsForSaleList=this.tradeableAssets();
		ArrayList<Offerable>  assetGroupsForSaleList=this.tradeableGroups();
		boolean skipRun =false;
		if (assetsForSaleList.size()+assetGroupsForSaleList.size()>0)
		{
			while ((!assetGroupsForSaleList.isEmpty()|| !assetsForSaleList.isEmpty() )&& offersMade<GameManager.MAX_NUM_OF_SELL_OFFERS)
			{
				buyOffer helperOffer = new buyOffer(this);
				if (GameManager.CurrentUI.askYesNoQuestion("Would you like to sell an asset?"))
				{
					if (!assetGroupsForSaleList.isEmpty())
					{
						if (GameManager.CurrentUI.askYesNoQuestion("Would you like to sell an asset group?"))
						{
							GameManager.CurrentUI.askOfferableSellQuestions(this, helperOffer, OfferType.Groups, false);
							if (!helperOffer.getAssetGroups().isEmpty())
							{
								sellAsset(helperOffer.getAssetGroups().get(0));
							}
							else	
								offersMade--;
							skipRun=true;
						}
						
					}
					if (!assetsForSaleList.isEmpty() && !skipRun)
					{
						if (GameManager.CurrentUI.askYesNoQuestion("Would you like to sell a single asset?"))
						{
							GameManager.CurrentUI.askOfferableSellQuestions(this, helperOffer, OfferType.Assets, false);
							if (!helperOffer.getSingleAssets().isEmpty())
								sellAsset(helperOffer.getSingleAssets().get(0));
							else
								offersMade--; //Offer canceled by user
						}	
					}
					offersMade++;
					skipRun=false;
				}
				else
					break;
				
				assetsForSaleList=this.tradeableAssets();
				assetGroupsForSaleList=this.tradeableGroups();
			}
			if (assetGroupsForSaleList.size()+ assetsForSaleList.size() ==0)
				GameManager.CurrentUI.notifyPlayerOutOfAssets(this);
			if (offersMade==GameManager.MAX_NUM_OF_SELL_OFFERS)
				GameManager.CurrentUI.notifyPlayerExceededSellOfferCount(this);
		}
	}
	
	/**
	 * method buyOffer makeBuyOffer()
	 * @visibility private
	 * Prompts the user to make a buy offer and returns the formulated offer. 
	 * @return A buy offer made by the player.
	 */
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

	/* (non-Javadoc)
	 * @see players.Player#makeBuyOffer(assets.Offerable)
	 * Outputs the asset's details and lets the user make an offer.
	 */
	@Override
	public buyOffer makeBuyOffer(Offerable asset) {
		GameManager.CurrentUI.notifyBidEvent(asset);
		return makeBuyOffer();
	}


	/* (non-Javadoc)
	 * @see players.Player#chooseWinningOffer(java.util.ArrayList)
	 * Prompts the user to choose the winning offer.
	 */
	@Override
	protected int chooseWinningOffer(ArrayList<buyOffer> buyOffers) 
	{
		return GameManager.CurrentUI.chooseAnOffer(buyOffers);
	}
}
