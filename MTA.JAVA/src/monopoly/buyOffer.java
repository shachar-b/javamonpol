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
 * class buyOffer implements Comparable<buyOffer>
 * @see Comparable<buyOffer>
 * public
 * represents a buy offer in the bidding phase
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class buyOffer implements Comparable<buyOffer> {

	/**
	 * static enum  Answer
	 * public
	 * @see buyoffer
	 * this is an enum for the use of buy offer. it says if an asset has been accepted or refused by the offer
	 * @author Omer Shenhar and Shachar Butnaro
	 *
	 */
	public static enum  Answers{
		ACCEPTED,DECLINED
	}

	private int money;
	private ArrayList<AssetGroup> assetGroups;
	private ArrayList<Asset> singleAssets;
	private Player offerMaker;


	/**
	 * method buyOffer(Player offerMaker)
	 * public
	 * this is a constructor for class buyOffer
	 * @param offerMaker - a non null valid player
	 */
	public buyOffer(Player offerMaker) {
		money=0;
		assetGroups=new ArrayList<AssetGroup>();
		singleAssets=new ArrayList<Asset>();
		this.offerMaker = offerMaker;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * compares two offers by the money offered
	 */
	@Override
	public  int compareTo(buyOffer other) {
		return money-other.money;
	}

	/**
	 * method Answers addToOffer(int amount)
	 * public
	 * adds a money amount to offer
	 * @param amount - a number grater then 1
	 * @return if(amount<1) DECLINED otherwise ACCEPTED
	 */
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


	/**
	 * method Answers addToOffer(Offerable offer)
	 * public
	 * adds a Offerable asset or group to offer
	 * @param offer - an non null Offerable
	 * @return if(offer could be not sold) DECLINED otherwise ACCEPTED
	 */
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

	/**
	 * method Answers addToOffer(AssetGroup group)
	 * private
	 * adds a AssetGroup to offer
	 * @param group - an non null AssetGroup
	 * @return if(group could be not sold) DECLINED otherwise ACCEPTED
	 */
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

	/**
	 * method Answers addToOffer(Asset asset)
	 * private
	 * adds a Asset to offer
	 * @param asset - an non null AssetGroup
	 * @return if(asset could be not sold) DECLINED otherwise ACCEPTED
	 */
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

	/**
	 * method void combineWith(buyOffer otherOffer)
	 * public
	 * adds another offer into this offer (other offer is not changed)
	 * @param otherOffer - a valid non null buyOffer
	 */
	public void combineWith(buyOffer otherOffer) {
		money+=otherOffer.money;
		assetGroups.addAll(otherOffer.assetGroups);
		singleAssets.addAll(otherOffer.singleAssets);

	}
	/**
	 * method int getMoney()
	 * public
	 * @return the amount of money in an offer (0 for non)
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * method ArrayList<AssetGroup> getAssetGroups()
	 * public
	 * @return the list of AssetGroup in this offer(empty list for non)
	 */
	public ArrayList<AssetGroup> getAssetGroups() {
		return assetGroups;
	}
	/**
	 * method public ArrayList<Asset> getSingleAssets()
	 * public
	 * @return the list of Asset in this offer(empty list for non)
	 */
	public ArrayList<Asset> getSingleAssets() {
		return singleAssets;
	}

	/**
	 * method Player getOfferMaker()
	 * public
	 * @return the player who made this offer
	 */
	public Player getOfferMaker() {
		return offerMaker;
	}
	/**
	 * method void preform(Player player)
	 * public
	 * moves the money Assets and AssetGroup from to player
	 * @param player - a valid non null player
	 */
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
	/**
	 * method boolean has(Offerable obj)
	 * public
	 * @param obj - a valid non null offerable
	 * @return true if this obj is in the offer. false otherwise
	 */
	public boolean has(Offerable obj)
	{
		return assetGroups.contains(obj) || singleAssets.contains(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return money+GameManager.MoneySign+" , the groups"+assetGroups +
		" and the assets" +singleAssets;
	}
}
