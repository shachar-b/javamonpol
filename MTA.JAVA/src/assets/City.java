/**
 * 
 */
package assets;

import java.io.File;

import monopoly.GameManager;

import players.Player;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class City extends Asset {

	private int rentPrice[] = new int [4]; // index 0=as is, 1=1 house, 2=2 houses, 3=3 houses
	private int numHouses;
	private int costOfHouse;

	/**
	 * @param group
	 */
	public City(AssetGroup group, String name, int costOfCity ,int costOfHouse, int[] rentPrices) {
		super(group);
		this.name = name;
		this.numHouses = 0;
		this.cost = costOfCity;
		this.costOfHouse=costOfHouse;
		this.rentPrice=rentPrices.clone();
	}

	/* (non-Javadoc)
	 * @see assets.Asset#getRentPrice()
	 */
	@Override
	public int getRentPrice()
	{
		return rentPrice[numHouses];
	}

	/* (non-Javadoc)
	 * @see squares.Square#playerArrived(players.Player)
	 */
	@Override
	public void playerArrived(Player player)
	{
		super.playerArrived(player); //Check for the other options
		if  (owner==player)//Owner is player
		{
			if (group.isOfSoleOwnership())
			{
				if (numHouses<GameManager.MAX_NUMBER_OF_HOUSES)
				{
					if (player.buyHouseDecision(this))
					{
						player.ChangeBalance(costOfHouse, GameManager.SUBTRACT);
						numHouses++;
						GameManager.CurrentUI.notifyPlayerBoughtHouse(player, this);
					}
				}
			}
			//Otherwise do nothing.
		}
	}
	
	public void init(File inFile) {
		//TODO : init city
	}

	public int getNumHouses() {
		return numHouses;
	}

	public int getCostOfHouse() {
		return costOfHouse;
	}
 	
	@Override
	public void setOwner(Player owner)
	{
		super.setOwner(owner);
		if (owner==GameManager.assetKeeper) //Player has been removed from game.
			numHouses=0;
	}
	
}