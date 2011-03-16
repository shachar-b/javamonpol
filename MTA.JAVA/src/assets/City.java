/**
 * 
 */
package assets;

import monopoly.GameManager;
import players.Player;

/**
 * class City extends Asset
 * @see Asset
 * @visibility public
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class City extends Asset {

	private int rentPrice[] = new int [4]; // index 0=as is, 1=1 house, 2=2 houses, 3=3 houses
	private int numHouses;
	private int costOfHouse;

	
	/**
	 * method City(AssetGroup group, String name, int costOfCity ,int costOfHouse, int[] rentPrices)
	 * @visibility public
	 * this is the constructor for a city in the monopoly game
	 * @param group - a valid non null Country which this City belongs to 
	 * @param name - the name of the city
	 * @param costOfCity - a non negative integer. the buy cost of the City  
	 * @param costOfHouse - a non negative integer. cost for each house built
	 * @param rentPrices - a array of size 4 were each of its cells is initilized to a non negative integer.
	 * @pre costOfCity>0 &&costOfHouse>0 && rentPrices.size()==4 &&if(i<1) !rentPrices.contains(i)
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
				if (justBoughtCity==false)
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
			}	
			//If the arriving player is the owner, need to set justBoughtCity to false.
			justBoughtCity=false; //This way you don't buy a house in the exact 
								  //same turn you buy the last city in the country.
		}
	}


	/**
	 * method int getNumHouses()
	 * @visibility public
	 * @return the number of houses in the city
	 */
	public int getNumHouses() {
		return numHouses;
	}

	/**
	 * method int getCostOfHouse()
	 * @visibility public
	 * @return the cost for a single house in the city
	 */
	public int getCostOfHouse() {
		return costOfHouse;
	}

	/* (non-Javadoc)
	 * @see assets.Asset#setOwner(players.Player)
	 */
	@Override
	public void setOwner(Player owner)
	{
		super.setOwner(owner);
		if (owner==GameManager.assetKeeper) //Player has been removed from game.
			numHouses=0;
	}

}