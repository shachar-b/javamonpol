/**
 * 
 */
package monopoly;

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
	private int money=0;
	private AssetGroup[]assetGroups;
	private Asset[] singleAssets;
	
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

}
