package assets;
import monopoly.GameManager;
import players.Player;
import squares.Square;
import ui.OfferType;




public abstract class Asset extends Square implements Offerable{
	protected Player owner;
	protected AssetGroup group;
	protected int cost;
	protected boolean justBoughtCity; //Used only for cities (for buying houses) so maintained only there.

	public Asset(AssetGroup group) {
		super();
		owner = GameManager.assetKeeper;
		this.group = group;
		justBoughtCity=false;
		group.add(this);
	}
	
	public Boolean isOwnedBy(Player player)
	{
		return owner.equals(player);
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public void playerArrived(Player player)
	{
		if (owner==GameManager.assetKeeper)
		{
			if (player.buyDecision(this))
			{
				player.ChangeBalance(cost, GameManager.SUBTRACT);
				setOwner(player);
				GameManager.CurrentUI.notifyPlayerBoughtAsset(player, this);
				justBoughtCity=true;
			}
		}
		else if (owner!=player)
		{
			GameManager.CurrentUI.notifyPlayerPaysRent(player, this, owner);
			int amountRcvd = player.ChangeBalance(getRentPrice(), GameManager.SUBTRACT);
			this.owner.ChangeBalance(amountRcvd, GameManager.ADD);
		}
	}
	
	
	public abstract int getRentPrice();
	
	public void setOwner(Player newOwner)
	{
		if(owner!=GameManager.assetKeeper)
		{
			owner.removeFromAssetList(this);
		}
		if(newOwner!=GameManager.assetKeeper)
			newOwner.addToAssetList(this);
		this.owner = newOwner;
	}

	public Player getOwner()
	{
		return owner;
	}
	
	public AssetGroup getGroup()
	{
		return group;
	}
	
	public OfferType getType()
	{
		return OfferType.Assets;
	}
	@Override
	public String toString() 
	{
		return getName();
	}
}
