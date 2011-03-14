/**
 * 
 */
package monopoly;

import java.util.HashSet;

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
	private HashSet<AssetGroup> assetGroups;
	private HashSet<Asset> singleAssets;
	private Player offerMaker;


	public buyOffer(Player offerMaker) {
		money=0;
		assetGroups=new HashSet<AssetGroup>();
		singleAssets=new HashSet<Asset>();
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
		if(offer.getClass().isInstance(AssetGroup.class))
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
	public HashSet<AssetGroup> getAssetGroups() {
		return assetGroups;
	}
	public HashSet<Asset> getSingleAssets() {
		return singleAssets;
	}

	public Player getOfferMaker() {
		return offerMaker;
	}
}
