/**
 * 
 */
package players;

import java.util.ArrayList;
import java.util.Random;

import javax.management.RuntimeErrorException;

import monopoly.GameManager;
import monopoly.buyOffer;
import ui.utils.ImagePanel;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import assets.Offerable;

/**
 * class ComputerPlayer extends Player
 * @see Player
 * public
 * A computer player in the Monopoly game.
 * @author Omer Shenhar and Shachar Butnaro
 */
public class ComputerPlayer extends Player {

	private static final int BUY_THRESHHOLD=300;// the minimal amount of funds to leave in balance when buying something
	private static int generatedComputerNumber=1;

	/**
	 * Constructor for computer player.
	 * Generated a name and sends to super constructor.
	 */
	public ComputerPlayer() {
		super("Computer"+generatedComputerNumber
				,new ImagePanel(GameManager.IMAGES_FOLDER+"/playerIcons/"+generatedComputerNumber+".png"));
		generatedComputerNumber++;
	}

	/* (non-Javadoc)
	 * @see players.Player#buyDecision(java.lang.String, assets.Asset, int)
	 * If computer will be left with BUY_THRESHHOLD or more after buying - chooses to buy.
	 */
	@Override
	public Boolean buyDecision(Asset asset) {
		if( Balance-asset.getCost()>= BUY_THRESHHOLD )
		{
			return true;
		}
		else
		{
			GameManager.CurrentUI.notifyPlayerCantBuy(this, asset.getName(), asset.getCost());
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see players.Player#buyHouseDecision(assets.City)
	 * Same buying algorithm as an asset.
	 */
	@Override
	public Boolean buyHouseDecision(City asset)
	{
		if (Balance-asset.getCostOfHouse()>=BUY_THRESHHOLD)
		{
			return true;
		}
		else
		{
			GameManager.CurrentUI.notifyPlayerCantBuy(this, "house in " + asset.getName(), asset.getCostOfHouse());
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see players.Player#chooseToForfeit()
	 */
	@Override
	public boolean chooseToForfeit() {
		return false; //Computer is not a quitter!
	}

	/* (non-Javadoc)
	 * @see players.Player#makeSellOffers()
	 */
	@Override
	public void makeSellOffers() {
		return; //Computer is not a sellout!

	}

	/* (non-Javadoc)
	 * @see players.Player#makeBuyOffer(assets.Offerable)
	 * Calls the appropriate function for a computer player.
	 */
	@Override
	public buyOffer makeBuyOffer(Offerable asset) {
		switch (asset.getType()) {
		case Groups:
			return makeBuyOffer((AssetGroup)asset);

		case Assets:
			return makeBuyOffer((Asset)asset);

		default: //Shouldn't get here
			throw new RuntimeErrorException(null, "Unknown agrument for function \"notifyBidEvent\"");
		}
	}

	/**
	 * method buyOffer makeBuyOffer(Asset asset)
	 * private
	 * Generates a monetary value and adds it to a buy offer. Will not trade assets.
	 * @param asset The asset being auctioned.
	 * @return A formulated buy offer.
	 */
	private buyOffer makeBuyOffer(Asset asset) {//computer does not trade assets
		buyOffer offer=new buyOffer(this);
		int priceFactor = (new Random().nextInt((int)Math.sqrt(GameManager.NUMBER_OF_SQUARES)))+1;
		int offerMoney=asset.getRentPrice()/priceFactor;
		if(Balance -offerMoney>=BUY_THRESHHOLD)//otherwise offer nothing
		{
			offer.addToOffer(offerMoney);
		}
		return offer;
	}

	/**
	 * method buyOffer makeBuyOffer(AssetGroup group)
	 * private
	 * Generates a monetary value by summing up value of assets in group, and adds it to a buy offer.
	 * @param group The group being auctioned.
	 * @return A formulated buy offer.
	 */
	private buyOffer makeBuyOffer(AssetGroup group) {

		buyOffer offer=new buyOffer(this);
		for(Asset asset:group)
		{
			offer.combineWith(makeBuyOffer(asset));
		}
		if(Balance -offer.getMoney()>=BUY_THRESHHOLD)//otherwise offer nothing
		{
			return offer;
		}
		else
		{
			return new buyOffer(this);
		}
	}

	/* (non-Javadoc)
	 * @see players.Player#chooseWinningOffer(java.util.ArrayList)
	 * 
	 * Computer chooses the winning offer by choosing the offer with the largest
	 * monetary offer made.
	 */
	@Override
	protected int chooseWinningOffer(ArrayList<buyOffer> buyOffers) {
		int maxMoneyOffer=0;
		int maxIndex=-1;
		int index=0;

		for(buyOffer offer:buyOffers)
		{
			if(offer.getMoney()>maxMoneyOffer)
			{
				maxMoneyOffer=offer.getMoney();
				maxIndex=index;
			}
			index++;
		}
		return maxIndex;
	}	
}
