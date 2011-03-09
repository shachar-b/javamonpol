package assets;
import monopoly.GameManager;
import players.Player;
import squares.Square;




public abstract class Asset extends Square{
	protected Player owner;
	protected AssetGroup group;
	protected int cost;

	public Asset(AssetGroup group) {
		super();
		owner = GameManager.assetKeeper;
		this.group = group;
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
			if (player.buyDecision(this, cost))
			{
				player.ChangeBalance(cost, GameManager.SUBTRACT);
				owner=player;
				GameManager.CurrentUI.notifyPlayerBoughtAsset(player, this);
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
	
	public void setOwner(Player owner)
	{
		this.owner = owner;
	}

	public Player getOwner()
	{
		return owner;
	}
}
