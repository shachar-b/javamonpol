/**
 * 
 */
package assets;

/**
 * class UtilOrTranspoAssetGroup extends AssetGroup
 * a Utility Or Transport asset Group in the monopoly game - contains only members of class UtilOrTranspoAsset 
 * @visibility public
 * @see AssetGroup
 * @author Omer Shenhar and Shachar Butnaro
 *
 */

public class UtilOrTranspoAssetGroup extends AssetGroup {

	int fullRental;
	/** 
	 * method UtilOrTranspoAssetGroup(String nameOfGroup, int priceForEntireGroup)
	 * @param nameOfGroup
	 * @param priceForEntireGroup : A positive integer which depicts the rental price for
	 *								a square in the group when whole of group is held by the same player.
	 */
	public UtilOrTranspoAssetGroup(String nameOfGroup, int priceForEntireGroup) {
		super(nameOfGroup);
		fullRental=priceForEntireGroup;
	}
	
	public int getFullRental() {
		return fullRental;
	}

}
