/**
 * 
 */
package assets;


/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class UtilOrTranspoAsset extends Asset {
	
	private int basicRental;
	private int fullRental;
	
	public UtilOrTranspoAsset(AssetGroup group, String name, int cost, int basic, int full) {
		super(group);
		this.name=name;
		basicRental=basic;
		fullRental=full;
		this.cost = cost;
	}

	/* (non-Javadoc)
	 * @see assets.Asset#getRentPrice()
	 */
	@Override
	public int getRentPrice() {
		if (group.isOfSoleOwnership())
			return fullRental;
		else
			return basicRental;
	}
}
