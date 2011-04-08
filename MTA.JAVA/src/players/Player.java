package players;
import java.util.ArrayList;

import listeners.innerChangeEventListener.InnerChangeListenableClass;
import monopoly.GameManager;
import monopoly.buyOffer;
import ui.utils.ImagePanel;
import assets.Asset;
import assets.City;
import assets.Country;
import assets.Offerable;
import cards.ActionCard;

/**
 * abstract class Player
 * @visibility public
 * A player in the monopoly game.
 * @author Omer Shenhar and Shachar Butnaro
 */
public abstract class Player extends InnerChangeListenableClass {

	protected String Name;
	protected int Balance;
	private int CurrentPosition;
	protected boolean GoOnNextTurn;
	protected ArrayList<Asset> assetList = new ArrayList<Asset>();
	private ActionCard getOutOfJailFreeCardPlaceHolder = null;
	private int lastKnownPosition = 0; 
	ImagePanel playerIcon = null;
	private int ParkedOnRound=0;

	/**
	 * Constructor for Player
	 * @visibility public
	 * Creates a new player and sets his position and balance, and makes him active.
	 * @param name A String containing the name of the new player.
	 */
	public Player(String name,ImagePanel playerIcon2) {
		Name = name;
		setCurrentPosition(GameManager.START_SQ_LOCATION);
		Balance=GameManager.INITAL_FUNDS;
		GoOnNextTurn=true;
		this.playerIcon = playerIcon2;
	}	
	
	/**
	 * method Boolean equals(Player other)
	 * @visibility public
	 * Checks if two player instances are identical.
	 * @param other A player to compare with.
	 * @return true IFF both players have the exact same name.
	 */
	public Boolean equals(Player other)
	{
		return Name.equals(other.getName());		
	}

	/**
	 * method abstract Boolean buyDecision(Asset asset)
	 * @visibility public
	 * Lets a player choose whether to buy an asset or not.
	 * @param asset The asset that the player can buy
	 * @return true IFF player decides to buy an asset
	 */
	public abstract Boolean buyDecision(Asset asset);

	/**
	 * method abstract Boolean buyHouseDecision(City asset)
	 * @visibility public
	 * Lets a player choose whether to buy a house in a city or not.
	 * @param asset The city the player is on.
	 * @return true IFF player decides to buy a house.
	 */
	public abstract Boolean buyHouseDecision(City asset);

	/**
	 * method void setName(String name)
	 * @visibility public
	 * Setter for Name.
	 * @param name A String containing the new name.
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * method String getName()
	 * @visibility public
	 * Getter for Name.
	 * @return A String containing the Player's name.
	 */
	public String getName() {
		return Name;
	}
	
	/**
	 * method String getIconPanel()
	 * @visibility public
	 * Getter for playerIcon.
	 * @return An Icon representing the player.
	 */
	public ImagePanel getIconPanel(){
		return playerIcon;
	}

	/**
	 * method void setGoOnNextTurn(boolean goOnNextTurn)
	 * @visibility public
	 * Setter for GoOnNextTurn.
	 * @param goOnNextTurn A boolean containing the new value for the field.
	 */
	public void setGoOnNextTurn(boolean goOnNextTurn) {
		GoOnNextTurn = goOnNextTurn;
	}

	/**
	 * method boolean getGoOnNextTurn()
	 * @visibility public
	 * Getter for GoOnNextTurn.
	 * @return A boolean containing the value of GoOnNextTurn.
	 */
	public boolean getGoOnNextTurn() {
		return GoOnNextTurn;
	}

	/**
	 * method int ChangeBalance(int amount, int sign)
	 * @visibility public
	 * Changes the player's balance.
	 * @param amount An integer containing the amount of money to be added/subtracted from player.
	 * @param sign An integer specifying the direction of the change (Add/Subtract)
	 * @return An integer containing the amount that actually moved.
	 */
	public int ChangeBalance(int amount, int sign) {
		if(sign==GameManager.SUBTRACT)
		{
			if(amount>Balance)
			{
				makeSellOffers();//Player can try to sell assets to avoid going bankrupt.

				if(amount>Balance) //Can have balance of zero - but not negative balance
				{
					GameManager.currentGame.removePlayerFromGame(this);//if false remove from game
					fireEvent("removed");
					return Balance;//returns the amount of money taken
				}
				else
				{
					Balance-=amount;
					fireEvent("taken");
					return amount;
				}
			}
			else
			{
				Balance-=amount;
				fireEvent("taken");
				return amount;
			}
		}
		else
		{
			Balance+=amount;
			fireEvent("added");
			return 0;
			
		}
	}

	/**
	 * method abstract boolean chooseToForfeit()
	 * @visibility public
	 * Asks the player if he wants to quit the game.
	 * @return true IFF player wants to quit.
	 */
	public abstract boolean chooseToForfeit();

	/**
	 * method void setCurrentPosition(int currentPosition)
	 * @visibility public
	 * Setter for CurrentPosition
	 * @param currentPosition An integer specifying the new position of the player.
	 */
	public void setCurrentPosition(int currentPosition) {
		lastKnownPosition = this.CurrentPosition;
		CurrentPosition = currentPosition;
	}

	/**
	 * method int getLastKnownPosition()
	 * @visibility public
	 * Getter for getLastKnownPosition.
	 * @return an integer containing the last position of the player.
	 */
	public int getLastKnownPosition(){
		return lastKnownPosition;
	}
	
	/**
	 * method int getCurrentPosition()
	 * @visibility public
	 * Getter for CurrentPosition.
	 * @return an integer containing the current position of the player.
	 */
	public int getCurrentPosition() {
		return CurrentPosition;
	}

	/**
	 * method int getBalance()
	 * @visibility public
	 * Getter for Balance.
	 * @return An integer containing the current balance of the player.
	 */
	public int getBalance()
	{
		return Balance;
	}

	/**
	 * method void addToAssetList(Asset a)
	 * @visibility public
	 * Adds an asset to the player's asset list.
	 * @param a An asset to be added.
	 */
	public void addToAssetList(Asset a) {
		assetList.add(a);
	}

	/**
	 * method void removeFromAssetList(Asset a)
	 * @visibility public
	 * Removes an asset from the player's asset list.
	 * @param a An asset to be removed.
	 */
	public void removeFromAssetList(Asset a) {
		assetList.remove(a);
	}

	/**
	 * method ArrayList<Asset> getAssetList()
	 * @visibility public
	 * Getter for assetList.
	 * @return A list of player's assets.
	 */
	public ArrayList<Asset> getAssetList() {
		return assetList;
	}

	/**
	 * method void setGetOutOfJailFreeCardPlaceHolder(ActionCard getOutOfJailFreeCardPlaceHolder)
	 * @visibility public
	 * Setter for getOutOfJailFreeCardPlaceHolder.
	 * @param getOutOfJailFreeCardPlaceHolder A get out of jail free card reference.
	 */
	public void setGetOutOfJailFreeCardPlaceHolder(ActionCard getOutOfJailFreeCardPlaceHolder)
	{
		this.getOutOfJailFreeCardPlaceHolder = getOutOfJailFreeCardPlaceHolder;
	}

	/**
	 * method ActionCard getGetOutOfJailFreeCardPlaceHolder()
	 * @visibility public
	 * Getter for getOutOfJailFreeCardPlaceHolder.
	 * @return A get out of jail free card.
	 */
	public ActionCard getGetOutOfJailFreeCardPlaceHolder() {
		return getOutOfJailFreeCardPlaceHolder;
	}

	/**
	 * method boolean hasGetOutOfJailFreeCard()
	 * @visibility public
	 * A boolean function, checks whether the player has a get out of jail free card.
	 * @return true IFF player uses a get out of jail free card.
	 */
	public boolean hasGetOutOfJailFreeCard()
	{
		return (getOutOfJailFreeCardPlaceHolder!=null);
	}

	/**
	 * method abstract void makeSellOffers()
	 * @visibility public
	 * Allows the player to make a sell offer.  
	 */
	public abstract void makeSellOffers();

	/**
	 * method abstract buyOffer makeBuyOffer(Offerable asset)
	 * @visibility public
	 * Allows a player to make a buy offer for an asset/group.
	 * @param asset The asset/group that can be bought.
	 * @return The buy offer a player has made.
	 */
	public abstract buyOffer makeBuyOffer(Offerable asset);

	/**
	 * method ArrayList<Offerable> tradeableAssets()
	 * @visibility public
	 * Returns a list of all assets that the player can sell/trade.
	 * @return a list containing all assets that the player can sell/trade.
	 */
	public ArrayList<Offerable> tradeableAssets(){
		ArrayList<Offerable> result = new ArrayList<Offerable>();
		for (Asset current : assetList)
		{
			if(City.class.isInstance(current))
			{
				if (((Country)current.getGroup()).hasHousesConstructed())
				{
					continue;
				}
			}
			result.add(current);
		}
		return result;
	}

	/**
	 * method ArrayList<Offerable> tradeableGroups()
	 * @visibility public
	 * Returns a list of all groups that the player can sell/trade.
	 * @return a list containing all groups that the player can sell/trade.
	 */
	public ArrayList<Offerable> tradeableGroups(){
		ArrayList<Offerable> result = new ArrayList<Offerable>();
		for (Asset current : assetList)
		{
			if (!result.contains(current.getGroup()))
				if (current.getGroup().isOfSoleOwnership())
					result.add(current.getGroup());
		}
		return result;
	}

	/**
	 * method void sellAsset(Offerable asset)
	 * @visibility public
	 * Allows all other players to bid for an asset/group, lets the seller choose an offer
	 * (or cancel trade) and transfers assets/money/groups between players.
	 *
	 * @param asset The asset being sold.
	 */
	public void sellAsset(Offerable asset)
	{
		buyOffer winningOffer;
		ArrayList<buyOffer> buyOffers= new ArrayList<buyOffer>();
		for (Player player : GameManager.currentGame.getGamePlayers())
		{
			if (player!=this)
				buyOffers.add(player.makeBuyOffer(asset));
		}
		int choise=chooseWinningOffer(buyOffers);
		if(choise!=-1)//selected a proper offer
		{
			winningOffer=buyOffers.get(choise);
			asset.setOwner(winningOffer.getOfferMaker());
			winningOffer.preform(this);
			GameManager.CurrentUI.notifyTradeEvent(this,asset,winningOffer);
		}
		else
		{
			GameManager.CurrentUI.notifyTradeCanceled(this);
		}
	}

	/**
	 * method abstract int chooseWinningOffer(ArrayList<buyOffer> buyOffers)
	 * @visibility protected
	 * Returns the index of the selected buy offer when selling an asset/group.
	 * @param buyOffers A list of buy offers.
	 * @return index of winning offer.
	 */
	protected abstract int chooseWinningOffer(ArrayList<buyOffer> buyOffers);

	public void setParkedOnRound(int parkedOnRound) {
		ParkedOnRound = parkedOnRound;
	}

	public int getParkedOnRound() {
		return ParkedOnRound;
	}
}