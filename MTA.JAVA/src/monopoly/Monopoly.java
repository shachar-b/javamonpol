/**
 * 
 */
package monopoly;

import java.util.ArrayList;

import players.Player;
import squares.Square;
import ui.ConsoleUI;
import assets.Asset;
import cards.ShaffledDeck;

/**
 * public class Monopoly
 * this is the monopoly game
 * @author Omer Shenhar & Shachar Butnaro
 *
 */
public class Monopoly
{
	public static void main(String[] args) {
		Monopoly game = new Monopoly();
		GameManager.currentGame = game;
		game.init();
		game.play();
	}

	private ConsoleUI userInterface = (ConsoleUI) GameManager.CurrentUI;
	private ArrayList<Player> gamePlayers;
	private Dice[] die; 
	private ShaffledDeck surprise = new ShaffledDeck();
	private ShaffledDeck callUp = new ShaffledDeck();
	private ArrayList<Square> gameBoard;


	/**
	 * method private void init
	 * this method initializes the game data members
	 */
	private void init()
	{
		MonopolyInitilizer gameInitializer = new Init();
		//init players
		gamePlayers = gameInitializer.initPlayers();
		//init die
		die = gameInitializer.initDie();
		//init CARDS
		surprise = gameInitializer.initSurprise();
		callUp = gameInitializer.initCallUp();		
		// init gameBoard
		gameBoard = gameInitializer.initBoard();
	}

	/**
	 * method public void play()
	 * this methods handles the game
	 * 
	 */
	public void play()
	{
		int roundNumber = 1;
		while (getActualNumPlayers()>1)
		{
			doRound(roundNumber);
			roundNumber++;
		}
		userInterface.notifyGameWinner(gamePlayers.get(0));
	}

	/**
	 * method private void doRound(int roundNumber)
	 * this method handles a single round of monopoly
	 * @param roundNumber - the round number
	 */
	private void doRound(int roundNumber)
	{
		int currDieSum;
		for (int playerIndex=0; playerIndex<gamePlayers.size() ; playerIndex++)
		{
			Player p = gamePlayers.get(playerIndex);
			userInterface.notifyNewRound(p, roundNumber, gameBoard.get(p.getCurrentPosition()));
			if (p.chooseToForfeit())
			{
				removePlayerFromGame(p);
				playerIndex--;
				if (gamePlayers.size()==1)
					break;
				else
					continue;
			}
			if (gameBoard.get(p.getCurrentPosition()).shouldPlayerMove(p))
			{
				currDieSum=rollDie();
				movePlayer(p ,currDieSum,true);
				if (p.getGoOnNextTurn()) //If player cannot move due to new position, he can't sell offers.
					p.makeSellOffers();
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
		for (Dice d : die)
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
		int firstRollOutcome = die[0].rollDice();
		for (int i=1; i<GameManager.NUM_OF_DIE ; i++)
		{
			if (die[i].rollDice()!=firstRollOutcome)
				return false;
		}
		return true; //Will only get here if all rolls were the same
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
}
