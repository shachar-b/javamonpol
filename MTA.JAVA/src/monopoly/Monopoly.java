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

	public void doRound(int roundNumber)
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

	private int getActualNumPlayers()
	{
		return getGamePlayers().size();
	}

	private void movePlayer(Player p, int currDieSum, boolean getBonus) {
		int playerPos = p.getCurrentPosition();
		playerPos+=currDieSum;//currDieSum;
		if (playerPos>=GameManager.NUMBER_OF_SQUARES)
		{
			if (getBonus)
			{
				userInterface.notifyPassStartSquare(GameManager.START_PASS_BONUS);
				p.ChangeBalance(GameManager.START_PASS_BONUS, GameManager.ADD);
			}
			playerPos=playerPos % GameManager.NUMBER_OF_SQUARES;
		}
		p.setCurrentPosition(playerPos);
		userInterface.notifyPlayerLanded(p, gameBoard.get(p.getCurrentPosition()));
		gameBoard.get(playerPos).playerArrived(p);
	}

	public void gotoNextSquareOfType(Player p, Class<? extends Square> type, boolean getBonus) {
		int currpos=(p.getCurrentPosition()+1)%GameManager.NUMBER_OF_SQUARES;
		int numOfSteps = 1;
		while(!gameBoard.get(currpos).getClass().getSimpleName().equals(type.getSimpleName()))
		{
			currpos=(currpos+1)%GameManager.NUMBER_OF_SQUARES;
			numOfSteps++;
		}

		movePlayer(p, numOfSteps, getBonus);
	}

	public void removePlayerFromGame(Player p)
	{
		ArrayList<Asset> assetList=p.getAssetList();
		while (!assetList.isEmpty())//remove ownership from all remaining assets
		{
			assetList.get(0).setOwner(GameManager.assetKeeper);//set owner removes itself from the list
		}
		if (p.getGetOutOfJailFreeCardPlaceHolder()!=null)
			surprise.add(p.getGetOutOfJailFreeCardPlaceHolder());
		gamePlayers.remove(p);
	}

	public Player getPlayer(int index) throws IndexOutOfBoundsException
	{
		return getGamePlayers().get(index);
	}

	public ArrayList<Player> getGamePlayers() {
		return gamePlayers;
	}

	public ShaffledDeck getSurprise() {
		return surprise;
	}

	public ShaffledDeck getCallUp() {
		return callUp;
	}
}
