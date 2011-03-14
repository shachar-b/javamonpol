package players;
import java.util.ArrayList;

import monopoly.GameManager;
import monopoly.buyOffer;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import assets.Country;
import assets.Offerable;
import cards.ActionCard;

public abstract class Player {


	//Variable list
	protected String Name;
	protected int Balance;
	private int CurrentPosition;
	protected boolean GoOnNextTurn;
	protected ArrayList<Asset> assetList = new ArrayList<Asset>();
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

				if(amount>Balance) //Can have balance of zero - but not negative balance
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

	public ArrayList<Offerable> tradeableAssets(){
		ArrayList<Offerable> result = new ArrayList<Offerable>();
		for (Asset current : assetList)
		{
			if(current.getClass().isInstance(City.class))
			{
				if (((Country)current.getGroup()).hasHousesConstructed())
					continue;
			}
			else
				result.add(current);
		}
		return result;
	}

	public ArrayList<Offerable> tradeableGroups(){
		ArrayList<Offerable> result = new ArrayList<Offerable>();
		for (Asset current : assetList)
		{
			if (!result.contains(current))
				if (current.getGroup().isOfSoleOwnership())
					result.add(current.getGroup());
		}
		return result;
	}

	public void sellAsset(Asset asset)
	{
		ArrayList<buyOffer> buyOffers= new ArrayList<buyOffer>();
		for (Player player : GameManager.currentGame.getGamePlayers())
		{
			if (player!=this)
				buyOffers.add(player.makeBuyOffer(asset));
		}
	}
	
	protected abstract int chooseWinningOffer(ArrayList<buyOffer> buyOffers) ;
	
	public void sellAssetGroup(AssetGroup assetGroup)
	{
		// TODO Auto-generated method stub
		
	}

}