/**
 * 
 */
package players;

import java.util.ArrayList;
import java.util.Random;

import javax.management.RuntimeErrorException;

import monopoly.GameManager;
import monopoly.buyOffer;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import assets.Offerable;

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

	@Override
	public boolean chooseToForfeit() {
		return false; //Computer is not a quitter!
	}

	@Override
	public void makeSellOffers() {
		return; //Computer is not a sellout!

	}

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

	private buyOffer makeBuyOffer(Asset asset) {//computer do not trade assets
		buyOffer offer=new buyOffer(this);
		int priceFactor = (new Random().nextInt((int)Math.sqrt(GameManager.NUMBER_OF_SQUARES)))+1;
		int offerMoney=asset.getRentPrice()/priceFactor;
		if(Balance -offerMoney>=BUY_THRESHHOLD)//otherwise offer nothing
		{
			offer.addToOffer(offerMoney);
		}
		return offer;
	}

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
