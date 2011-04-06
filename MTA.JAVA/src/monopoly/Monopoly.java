package monopoly;

import java.util.ArrayList;

import monopoly.GameManager.jailActions;

import players.Player;
import squares.JailSlashFreePassSquare;
import squares.Square;
import ui.UI;
import ui.guiComponents.dice.Dice;
import assets.Asset;
import assets.City;
import cards.ShaffledDeck;

/**
 * public class Monopoly
 * this is the monopoly game
 * @author Omer Shenhar & Shachar Butnaro
 *
 */
public class Monopoly
{
	private UI userInterface;
	private ArrayList<Player> gamePlayers;
	private OldDice[] die; 
	private ShaffledDeck surprise = new ShaffledDeck();
	private ShaffledDeck callUp = new ShaffledDeck();
	private ArrayList<Square> gameBoard;
	private int playerIndex=0;
	private int roundNumber = 1;
	private Player currentActivePlayer;


	/**
	 * method void init (package protected)
	 * this method initializes the game data members
	 */
	void init()
	{
		MonopolyInitilizer gameInitializer = new Init();
		// init gameBoard
		gameBoard = gameInitializer.initBoard();
		// init UI
		GameManager.CurrentUI = new UI();
		userInterface = (UI)GameManager.CurrentUI;
		//init die
		die = gameInitializer.initDie();
		//init CARDS
		surprise = gameInitializer.initSurprise();
		callUp = gameInitializer.initCallUp();		
	}

	public void setGamePlayers(ArrayList<Player> gamePlayers)
	{
		this.gamePlayers=gamePlayers;
	}

	/**
	 * method public void play()
	 * this methods handles the game
	 * 
	 */
	public void play()
	{
		userInterface.notifyNewRound(gamePlayers.get(0), roundNumber, gameBoard.get(0));
	}

	/**
	 * method private void doRound(int roundNumber)
	 * this method handles a single round of monopoly
	 * @param roundNumber - the round number
	 */
	private void doComputerRound(int roundNumber)
	{
		int currDieSum;
		for (playerIndex=0; playerIndex<gamePlayers.size() ; playerIndex++)
		{
			currentActivePlayer = gamePlayers.get(playerIndex);
			userInterface.notifyNewRound(currentActivePlayer, roundNumber, gameBoard.get(currentActivePlayer.getCurrentPosition()));

			{
				if (currentActivePlayer.getGoOnNextTurn()) //If player cannot move due to new position, he can't sell offers.
					currentActivePlayer.makeSellOffers();
			}
		}
	}



	/**
	 * method private int rollDie()
	 * this method rool all the dice
	 * @return the sum of the dice roll
	 */
	private int rollDie()
	{
		int result=0;
		for (OldDice d : die)
		{
			int currRoll = d.rollDice();
			result+=currRoll;
		}
		return result;
	}

	/**
	 * method public boolean rollForADouble()
	 * this is a method to be used by jail square . it rolls the die once
	 * @return true if the dice are the same false otherwise
	 */
	public boolean rollForADouble()
	{
		int[] result = Dice.getGameDice().getDieOutcome();
		return (result[0]==result[1]);
	}	

	/**
	 * method private int getActualNumPlayers()
	 * @return the actual number of players at the time of the request
	 */
	private int getActualNumPlayers()
	{
		return getGamePlayers().size();
	}

	/**
	 * Method private void movePlayer(Player player, int currDieSum, boolean getBonus)
	 * this method moves a player on the game board 
	 * @param player - a non null player who is in the game to be moved
	 * @param currDieSum - a number from 2 to 12 - how many steps should the player take
	 * @param getBonus - true if the player should get a bonus if he goes thru start. false otherwise
	 */
	private void movePlayer(Player player, int currDieSum, boolean getBonus) {
		int playerPos = player.getCurrentPosition();
		playerPos+=currDieSum;//currDieSum;
		if (playerPos>=GameManager.NUMBER_OF_SQUARES)
		{
			if (getBonus)
			{
				userInterface.notifyPassStartSquare(GameManager.START_PASS_BONUS);
				player.ChangeBalance(GameManager.START_PASS_BONUS, GameManager.ADD);
			}
			playerPos=playerPos % GameManager.NUMBER_OF_SQUARES;
		}
		player.setCurrentPosition(playerPos);
		userInterface.notifyPlayerLanded(player, gameBoard.get(player.getCurrentPosition()));
		gameBoard.get(playerPos).playerArrived(player);
	}

	/**
	 * method public void gotoNextSquareOfType(Player player, Class<? extends Square> type, boolean getBonus)
	 * this method moves player to next square of type type;
	 * @param player - a non null active player to be moved
	 * @param type - a class which extends Square
	 * @param getBonus -  true if the player should get a bonus if he goes thru start. false otherwise
	 */
	public void gotoNextSquareOfType(Player player, Class<? extends Square> type, boolean getBonus) {
		int currpos=(player.getCurrentPosition()+1)%GameManager.NUMBER_OF_SQUARES;
		int numOfSteps = 1;
		while(!gameBoard.get(currpos).getClass().getSimpleName().equals(type.getSimpleName()))
		{
			currpos=(currpos+1)%GameManager.NUMBER_OF_SQUARES;
			numOfSteps++;
		}

		movePlayer(player, numOfSteps, getBonus);
	}

	/**
	 * method public void removePlayerFromGame(Player player)
	 * this method safely removes a player from game (returns all his assets to treasury ,demolish houses and so on)
	 * @param player a non null active player to be removed
	 */
	public void removePlayerFromGame(Player player)
	{
		ArrayList<Asset> assetList=player.getAssetList();
		while (!assetList.isEmpty())//remove ownership from all remaining assets
		{
			assetList.get(0).setOwner(GameManager.assetKeeper);//set owner removes itself from the list
		}
		if (player.getGetOutOfJailFreeCardPlaceHolder()!=null)
			surprise.add(player.getGetOutOfJailFreeCardPlaceHolder());
		gamePlayers.remove(player);
		GameManager.CurrentUI.notifyPlayerLeftGame(player);
	}

	/**
	 * method public ArrayList<Player> getGamePlayers() 
	 * @return a list of  all players in the game
	 */
	public ArrayList<Player> getGamePlayers() {
		return gamePlayers;
	}

	/** 
	 * method public ShaffledDeck getSurprise()
	 * @return the surprise Deck
	 */
	public ShaffledDeck getSurprise() {
		return surprise;
	}

	/** 
	 * method public ShaffledDeck getCallUp()
	 * @return the CallUp Deck
	 */
	public ShaffledDeck getCallUp() {
		return callUp;
	}

	public ArrayList<Square> getGameBoard(){
		return gameBoard;
	}


	private void endTurn()
	{
		playerIndex++;
		if (playerIndex>gamePlayers.size())
		{
			playerIndex=0;
			roundNumber++;
		}
		Player p = gamePlayers.get(playerIndex);
		if (getActualNumPlayers()==1)
			GameManager.CurrentUI.notifyGameWinner(gamePlayers.get(0));
		else
			GameManager.CurrentUI.notifyNewRound(p, roundNumber, gameBoard.get(p.getCurrentPosition()));
	}

	public void eventDispatch(String message) {
		currentActivePlayer = gamePlayers.get(playerIndex);
		Square sq = gameBoard.get(currentActivePlayer.getCurrentPosition());
		if(message.equals("forfeit"))
		{
			removePlayerFromGame(currentActivePlayer);
			playerIndex--;
			endTurn();
		}
		else if(message.equals("endTurn"))
		{
			endTurn();
		}
		else if(message.equals("getOutOfJail"))
		{
			((JailSlashFreePassSquare)sq).playerUsesGetOutOfJailCard(currentActivePlayer);
			GameManager.CurrentUI.getFrame().getPlayerPanel().setGetOutOfJailButtonStatus(false);
		}
		else if (message.equals("buyHouse"))
		{ 
			((City)sq).BuyHouse(currentActivePlayer);
			GameManager.CurrentUI.getFrame().getPlayerPanel().setBuyHouseButtonStatus(false);
			
		}
		else if (message.equals("buyAsset"))
		{
			((Asset)sq).buyAsset(currentActivePlayer);	
			GameManager.CurrentUI.getFrame().getPlayerPanel().setBuyAssetButtonStatus(false);

		}
		else if(message.equals("throwDie"))
		{
			if (sq instanceof JailSlashFreePassSquare && !sq.shouldPlayerMove(currentActivePlayer))
				{
					boolean hasDouble = rollForADouble();
					if (hasDouble)
						GameManager.CurrentUI.notifyJailAction(currentActivePlayer, jailActions.ROLLED_DOUBLE);
					else
						GameManager.CurrentUI.notifyJailAction(currentActivePlayer, jailActions.STAY_IN_JAIL);
					GameManager.CurrentUI.getFrame().getPlayerPanel().setGetOutOfJailButtonStatus(false);
				}
			if (gameBoard.get(currentActivePlayer.getCurrentPosition()).shouldPlayerMove(currentActivePlayer))
			{
				int[] result = ui.guiComponents.dice.Dice.getGameDice().getDieOutcome();
				int dieSum = result[0]+result[1];
				movePlayer(currentActivePlayer, dieSum, true);
			}
		
		}
		
	}
}
