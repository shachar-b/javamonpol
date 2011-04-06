package assets;
import monopoly.GameManager;
import players.Player;
import squares.Square;
import ui.OfferType;

/**
 * public abstract class Asset extends Square implements Offerable
 * @visibility public
 * @see Offerable
 * this class represent a Square which can be bought and sold by a player
 * @author Omer Shenhar and Shachar Butnaro
 *
 */

public abstract class Asset extends Square implements Offerable{
	
	protected Player owner;
	protected AssetGroup group;
	protected int cost;
	protected boolean justBoughtCity; //Used only for cities (for buying houses) so maintained only there.

	/**
	 * method  Asset(AssetGroup group)
	 * @visibility public
	 * this method is a constructor to be used by classes extending Asset
	 * @param group - an non null AssetGroup
	 */
	public Asset(AssetGroup group) {
		super();
		owner = GameManager.assetKeeper;
		this.group = group;
		justBoughtCity=false;
		group.add(this);
	}
	
	/**
	 * method Boolean isOwnedBy(Player player)
	 * @visibility public
	 * @param player - a player
	 * @return true if the player is the owner false otherwise
	 */
	public Boolean isOwnedBy(Player player)
	{
		return owner.equals(player);
	}
	
	/**
	 * method int getCost()
	 * @visibility public
	 * @return the buy cost of the asset
	 */
	public int getCost()
	{
		return cost;
	}
	
	/* (non-Javadoc)
	 * @see squares.Square#playerArrived(players.Player)
	 * also offers the player an option to  buy the asset if there is no owner
	 * otherwise if needed it makes the player pay rent
	 */
	public void playerArrived(Player player)
	{
		if (owner==GameManager.assetKeeper)
		{
			GameManager.CurrentUI.getFrame().getPlayerPanel().setBuyAssetButtonStatus(true);
		}
		else if (owner!=player)
		{
			GameManager.CurrentUI.notifyPlayerPaysRent(player, this, owner);
			int amountRcvd = player.ChangeBalance(getRentPrice(), GameManager.SUBTRACT);
			this.owner.ChangeBalance(amountRcvd, GameManager.ADD);
		}
	}
	
	public void buyAsset(Player player)
	{//TODO : Stuff
		player.ChangeBalance(cost, GameManager.SUBTRACT);
		setOwner(player);
		GameManager.CurrentUI.notifyPlayerBoughtAsset(player, this);
		justBoughtCity=true;
	}
	
	/**
	 * method  abstract int getRentPrice()
	 * @visibility public
	 * @return the current rent price of the asset
	 */
	public abstract int getRentPrice();
	
	/* (non-Javadoc)
	 * @see assets.Offerable#setOwner(players.Player)
	 */
	public void setOwner(Player newOwner)
	{
		if(owner!=GameManager.assetKeeper)
		{
			owner.removeFromAssetList(this);
		}
		if(newOwner!=GameManager.assetKeeper)
			newOwner.addToAssetList(this);
		this.owner = newOwner;
		
		fireEvent("owner"); //notify of a change
	}

	/**
	 * method Player getOwner()
	 * @visibility public
	 * @return the owner of the Asset
	 */
	public Player getOwner()
	{
		return owner;
	}
	
	/**
	 * method AssetGroup getGroup()
	 * @visibility public
	 * @return the group this Asset belongs to
	 */
	public AssetGroup getGroup()
	{
		return group;
	}
	
	/* (non-Javadoc)
	 * @see assets.Offerable#getType()
	 */
	public OfferType getType()
	{
		return OfferType.Assets;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return getName();
	}
}
