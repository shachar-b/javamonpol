package players;
import java.util.ArrayList;

import cards.ActionCard;

import monopoly.GameManager;
import monopoly.buyOffer;
import assets.Asset;
import assets.AssetGroup;
import assets.City;

public abstract class Player {
	
	
	//Variable list
	protected String Name;
	protected int Balance;
	private int CurrentPosition;
	protected boolean GoOnNextTurn;
	private ArrayList<Asset> assetList = new ArrayList<Asset>();
	private ActionCard getOutOfJailFreeCardPlaceHolder = null;
	
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
	
	public abstract Boolean buyDecision(Asset asset);
	public abstract Boolean buyHouseDecision(City asset);

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
	
	public void setGetOutOfJailFreeCardPlaceHolder(
			ActionCard getOutOfJailFreeCardPlaceHolder) {
		this.getOutOfJailFreeCardPlaceHolder = getOutOfJailFreeCardPlaceHolder;
	}

	public ActionCard getGetOutOfJailFreeCardPlaceHolder() {
		return getOutOfJailFreeCardPlaceHolder;
	}
	
	public boolean hasGetOutOfJailFreeCard()
	{
		return (getOutOfJailFreeCardPlaceHolder!=null);
	}

	public abstract void makeSellOffers();
	
	public abstract buyOffer makeBuyOffer(Asset assat);
	public abstract buyOffer makeBuyOffer(AssetGroup assat);
	
	public void sellAsset(Asset a)
	{


	}
}