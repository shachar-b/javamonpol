/**
 * 
 */
package monopoly;

import java.util.ArrayList;

import players.Player;
import assets.Asset;
import assets.AssetGroup;
import assets.Offerable;

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
	

	public Answers addToOffer(Offerable offer)
	{
		if(AssetGroup.class.isInstance(offer))
		{
			return addGroupToOffer((AssetGroup)offer);
		}
		else
		{
			return addAssetToOffer((Asset)offer);
		}
	}

	private Answers addGroupToOffer (AssetGroup group)
	{
		if (!group.isOfSoleOwnership())
		{
			return Answers.DECLINED;
		}
		else
		{
			assetGroups.add(group);
			return Answers.ACCEPTED;
		}
	}

	private Answers addAssetToOffer(Asset asset)
	{
		if (assetGroups.contains(asset.getGroup())) //can't offer an asset that is in a group already offered.
		{
			return Answers.DECLINED;
		}
		else
		{
			
			singleAssets.add(asset);
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
	public void preform(Player player) {
		
		//money
		int cash=offerMaker.ChangeBalance(money, GameManager.SUBTRACT);
		player.ChangeBalance(cash, GameManager.ADD);
		
		//assets Groups
		for(AssetGroup group:assetGroups)
		{
			group.setOwner(player);
			
		}
		
		//single Assets
		for(Asset asset:singleAssets)
		{
			asset.setOwner(player);
		}
		
		
		
	}
	public boolean has(Offerable obj)
	{
		return assetGroups.contains(obj) || singleAssets.contains(obj);
	}
	
	@Override
	public String toString() {
		return money+GameManager.MoneySign+" , the groups"+assetGroups +
		" and the assets" +singleAssets;
	}
}
