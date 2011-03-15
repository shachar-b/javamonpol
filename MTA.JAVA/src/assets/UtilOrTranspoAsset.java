/**
 * 
 */
package assets;

import javax.management.RuntimeErrorException;


/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class UtilOrTranspoAsset extends Asset {
	
	private int basicRental;
	
	public UtilOrTranspoAsset(AssetGroup group, String name, int cost, int basic, int full) {
		super(group);
		this.name=name;
		basicRental=basic;
		this.cost = cost;
	}

	/* (non-Javadoc)
	 * @see assets.Asset#getRentPrice()
	 */
	@Override
	public int getRentPrice() {
		if (group.isOfSoleOwnership())
			try {
				return ((UtilOrTranspoAssetGroup)group).getFullRental();
			} catch (ClassCastException e) {//could only get here if the class has been handled incorrectly- could not recover
				throw new RuntimeErrorException(null,"The utility "+ this.name + " has been added to a non Utility object");
			}			
		else
			return basicRental;
	}
}
