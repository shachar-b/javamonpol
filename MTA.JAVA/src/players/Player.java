package players;
import java.util.ArrayList;

import monopoly.GameManager;
import assets.Asset;
import assets.City;

public abstract class Player {
	
	
	//Variable list
	protected String Name;
	protected int Balance;
	private int CurrentPosition;
	protected boolean GoOnNextTurn;
	private ArrayList<Asset> assetList = new ArrayList<Asset>();
	
	public Player(String name) {
		Name = name;
		setCurrentPosition(GameManager.SquareIndex.START.getIndex());
		Balance=GameManager.INITAL_FUNDS;
		GoOnNextTurn=true;
	}	
	
	public Boolean equals(Player other)
	{
		return Name.equals(other.getName());		
	}
	
	public abstract Boolean buyDecision(Asset asset, int cost);
	public abstract Boolean buyHouseDecision(City asset, int cost);

	public void setName(String name) {
		Name = name;
	}
	public String getName() {
		return Name;
	}
	public void setGoOnNextTurn(boolean goOnNextTurn) {
		GoOnNextTurn = goOnNextTurn;
	}
	public boolean getGoOnNextTurn() {
		return GoOnNextTurn;
	}
	
	public int ChangeBalance(int amount, int sign) {
		if(sign==GameManager.SUBTRACT)
		{
			if(amount>Balance)
			{
				makeSellOffers();
				
				if(amount>Balance)
				{
					GameManager.currentGame.removePlayerFromGame(this);//if false remove from game
				}
				return Math.max(Balance, amount);//returns the amount of money taken
			}
			else
			{
				Balance-=amount;
				return amount;
			}
		}
		else
		{
			Balance+=amount;
			return 0;
		}
	}

	public abstract boolean chooseToForfeit();

	public void setCurrentPosition(int currentPosition) {
		CurrentPosition = currentPosition;
	}

	public int getCurrentPosition() {
		return CurrentPosition;
	}
	
	public int getBalance()
	{
		return Balance;
	}

	public void addToAssetList(Asset a) {
		assetList.add(a);
		a.setOwner(this);
	}
	
	public void removeFromAssetList(Asset a) {
		assetList.remove(a);
	}

	public ArrayList<Asset> getAssetList() {
		return assetList;
	}
	
	public abstract void makeSellOffers();
	
	
	public void sellAsset(Asset a)
	{


	}


}