/**
 * 
 */
package monopoly;

import java.util.ArrayList;

import players.Player;

import assets.Asset;
import assets.AssetGroup;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class buyOffer implements Comparable<buyOffer> {
	public static enum  Answers{
		ACCEPTED,DECLINED
	}
	
	private int money;
	private ArrayList<AssetGroup> assetGroups;
	private ArrayList<Asset> singleAssets;
	private Player offerMaker;

	
	public buyOffer(Player offerMaker) {
		money=0;
		assetGroups=new ArrayList<AssetGroup>();
		singleAssets=new ArrayList<Asset>();
		this.offerMaker = offerMaker;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public  int compareTo(buyOffer other) {
		return money-other.money;
	}
	
	public Answers addToOffer(int amount)
	{
		if(amount<1)
		{
			return Answers.DECLINED;
		}
		else
		{
			money=amount;
			return Answers.ACCEPTED;
		}
	}
	public Answers addToOffer(Asset asset)
	{
		if(true)//ToDo: put rules here
		{
			return Answers.DECLINED;
		}
		else
		{
			
			return Answers.ACCEPTED;
		}
	}

	public void combineWith(buyOffer otherOffer) {
		money+=otherOffer.money;
		assetGroups.addAll(otherOffer.assetGroups);
		singleAssets.addAll(otherOffer.singleAssets);
		
	}
	public int getMoney() {
		return money;
	}
	public ArrayList<AssetGroup> getAssetGroups() {
		return assetGroups;
	}
	public ArrayList<Asset> getSingleAssets() {
		return singleAssets;
	}
	
	public Player getOfferMaker() {
		return offerMaker;
	}
	

}
