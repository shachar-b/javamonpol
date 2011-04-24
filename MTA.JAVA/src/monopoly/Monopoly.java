package monopoly;

import java.util.ArrayList;

import players.ComputerPlayer;
import players.Player;
import squares.JailSlashFreePassSquare;
import squares.Square;
import ui.UI;
import ui.guiComponents.PlayerPanel;
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
	private ShaffledDeck surprise = new ShaffledDeck();
	private ShaffledDeck callUp = new ShaffledDeck();
	private ArrayList<Square> gameBoard;
	private boolean stopGame =false;
	private int playerIndex=0;
	private int roundNumber = 1;
	private Player currentActivePlayer;
	private Square currentPlayerSquare;
	private int state=0;
	private boolean gameRunning=false;
	private  Thread stateMechThread= new Thread(new Runnable() {
		
		/* (non-Javadoc)
		 * @see doComputerRound() documentation
		 */
		@Override
		public void run() {
			PlayerPanel pane=GameManager.CurrentUI.getFrame().getPlayerPanel();
			currentPlayerSquare=gameBoard.get(currentActivePlayer.getCurrentPosition());
			switch (state) {
			case 0:
				state++;//next state is roll die
				if (currentPlayerSquare instanceof JailSlashFreePassSquare)
				{
					if(currentActivePlayer.hasGetOutOfJailFreeCard() &&
							!currentPlayerSquare.shouldPlayerMove(currentActivePlayer))
					{
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							throw new RuntimeException("state machine problem");
						}
						state=4;//nothing more to do in this turn
						pane.ClickGetOutOfJailButton();
						break;

					}
				}
			case 1:
				state++;//next state is 2- try to buy an asset 
				if (currentPlayerSquare instanceof JailSlashFreePassSquare || currentPlayerSquare.shouldPlayerMove(currentActivePlayer))
				{//dont do it only on parking- if GOJC was used this wont be reached
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException("state machine problem");
					}
					rollDie();
					break;
				}
			case 2:
				state++;//if you dont buy the asset next state is 3-try to buy a house
				if(currentPlayerSquare instanceof Asset && ((Asset) currentPlayerSquare).getOwner()==GameManager.assetKeeper )
				{//unowned asset
					if(currentActivePlayer.buyDecision(((Asset) currentPlayerSquare)))
					{
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							throw new RuntimeException("state machine problem");
						}
						state=4;//Can't buy house in the same turn you bought the asset
						pane.ClickBuyAssetButton();
						//wait
						break;
					}
				}
			case 3:
				state++;//next state is end turn no meter what happens here
				if(currentPlayerSquare instanceof City)
				{
					if(((City)currentPlayerSquare).canHouseBeBuilt(currentActivePlayer))
					{
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							throw new RuntimeException("state machine problem");
						}
						pane.ClickBuyHouseButton();
						break;
					}
				}
			case 4://case 4
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException("state machine problem");
				}
				state=0;//restart state machine for next player
				pane.ClickEndTurnButton();
				break;
			}
		}
	});

	/**
	 * method void init (package protected)
	 * this method initializes the game data members
	 */
	public void init()
	{
		MonopolyInitilizer gameInitializer = new XMLInitializer();
		// init gameBoard
		gameBoard = gameInitializer.initBoard();
		// init UI
		GameManager.CurrentUI = new UI();
		userInterface = (UI)GameManager.CurrentUI;
		//init CARDS
		surprise = gameInitializer.initSurprise();
		callUp = gameInitializer.initCallUp();	
	}

	/**
	 * method public void setGamePlayers(ArrayList<Player> gamePlayers)
	 * Sets the game players.
	 * @param gamePlayers an ArrayList of Players
	 */
	public void setGamePlayers(ArrayList<Player> gamePlayers)
	{
		this.gamePlayers=gamePlayers;
	}

	/**
	 * method public void play()
	 * this methods starts the first round.
	 */
	public void play()
	{
		userInterface.notifyNewRound(gamePlayers.get(0), roundNumber, gameBoard.get(0));
	}

	/**
	 * private void doComputerRound()
	 * Computers are controlled by a state machine, on a separate Thread.
	 * The run() function of the Thread directs the computer player to take appropriate
	 * actions, depending on the current state (depicted by the field "state".
	 * The function also resets the state when the player is done. 
	 */
	private void doComputerRound()
	{
		stateMechThread.run();
	}

	/**
	 * method private void rollDie()
	 * this method rolls the die.
	 */
	private void rollDie()
	{
		Dice.getGameDice().makeItRoll();
	}

	/**
	 * method public boolean rollForADouble()
	 * this is a method to be used by jail square.
	 * @return true IFF the last die throw was a double.
	 */
	public boolean checkForDouble()
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
	 * public int getRoundNumber()
	 * @return an integer representing the current round number.
	 */
	public int getRoundNumber()
	{
		return roundNumber;
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
	 * public void removePlayerFromGame(Player player) 
	 * This method calls removePlayerFromGame(Player player, boolean gameAborted)
	 * with the boolean as false, for when the game is not aborted.
	 * @param player a non null active player to be removed
	 */
	public void removePlayerFromGame(Player player)
	{
		removePlayerFromGame(player, false);
	}

	/**
	 * method public void removePlayerFromGame(Player player)
	 * this method safely removes a player from game (returns all his assets to treasury ,demolish houses and so on)
	 * @param player a non null active player to be removed
	 * @param gameAborted a boolean to signify if removing player due to a starting a new game.
	 */
	public void removePlayerFromGame(Player player, boolean gameAborted)
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
		if(player==currentActivePlayer)
		{
			endTurn();
		}
		else if (!gameAborted)//No need to do the following if a new game was started in the middle of a current one.\
		{
			{//it must be in the list
				playerIndex=gamePlayers.lastIndexOf(currentActivePlayer);
			}
		}

		if(gamePlayers.size()==1 && !gameAborted )
		{
			userInterface.notifyGameWinner(gamePlayers.get(0));
			stopGame=true;
		}
		GameManager.CurrentUI.getFrame().getGameboard().updatePlayersLegend();
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

	/**
	 * public ArrayList<Square> getGameBoard()
	 * @return an ArrayList of Squares representing the game board.
	 */
	public ArrayList<Square> getGameBoard(){
		return gameBoard;
	}

	/**
	 * method private void endTurn()
	 * Ends the current turn of the active player.
	 */
	private void endTurn()
	{
			playerIndex++;
			if (playerIndex>=gamePlayers.size())
			{
				playerIndex=0;
				roundNumber++;
			}
			Player p = gamePlayers.get(playerIndex);
			if (getActualNumPlayers()!=1)
				GameManager.CurrentUI.notifyNewRound(p, roundNumber, gameBoard.get(p.getCurrentPosition()));
	}

	/**
	 * method private void thrownDie()
	 * This method handles the operations neccesary after throwing the die.
	 */
	private void thrownDie() {
		int [] dOutcome = Dice.getGameDice().getDieOutcome();
		userInterface.notifyDiceRoll(dOutcome[0], dOutcome[1]);
		if (currentPlayerSquare instanceof JailSlashFreePassSquare && !currentPlayerSquare.shouldPlayerMove(currentActivePlayer))
		{
			boolean hasDouble = checkForDouble();
			((JailSlashFreePassSquare)currentPlayerSquare).release(currentActivePlayer, hasDouble);		}
		else if (gameBoard.get(currentActivePlayer.getCurrentPosition()).shouldPlayerMove(currentActivePlayer))
		{
			int[] result = ui.guiComponents.dice.Dice.getGameDice().getDieOutcome();
			int dieSum = result[0]+result[1];
			movePlayer(currentActivePlayer, dieSum, true);
		}
	}

	/**
	 * method private void buyHouse()
	 * This method activates the buyHouse method in the currentPlayerSquare
	 */
	private void buyHouse() {
		((City)currentPlayerSquare).BuyHouse(currentActivePlayer);
	}

	/**
	 * method private void useGetOutOfJail() 
	 * This method activates the get out of jail method in a JailSlashFreePassSquare.
	 */
	private void useGetOutOfJail() {
		((JailSlashFreePassSquare)currentPlayerSquare).playerUsesGetOutOfJailCard(currentActivePlayer);
	}

	/**
	 * method private void forfeit()
	 * This method removes the current player from the game.
	 * As a by-product, ends his turn.
	 */
	private void forfeit() {
		playerIndex--;
		removePlayerFromGame(currentActivePlayer);
	}

	/**
	 * method private void buyAsset()
	 * This method invokes the buyAsset method in an Asset.
	 */
	private void buyAsset() {
		((Asset)currentPlayerSquare).buyAsset(currentActivePlayer);	
	}

	/**
	 * method public void signalGameRunning()
	 * Signifies that a game has been started - e.g. that Play() has been called.
	 */
	public void signalGameRunning()
	{
		gameRunning=true;
	}

	/**
	 * @return true IFF a game is running - e.g. that play() has been called.
	 */
	public boolean getGameRunning()
	{
		return gameRunning;
	}

	/**
	 * method public void eventDispatch(String message)
	 * @param message a String depicting the event being dispatched by the player.
	 * Note that for any illegal message nothing would happen.
	 */
	public void eventDispatch(String message) {
		currentActivePlayer = gamePlayers.get(playerIndex);
		currentPlayerSquare = gameBoard.get(currentActivePlayer.getCurrentPosition());
		if(!stopGame && message.equals("computer"))
		{
			doComputerRound();
		}
		else if(message.equals("forfeit"))
		{
			forfeit();
		}
		else if(message.equals("endTurn"))
		{
			endTurn();
		}
		else if(message.equals("getOutOfJail"))
		{
			useGetOutOfJail();
			if(!stopGame &&currentActivePlayer instanceof ComputerPlayer)//go on next state
			{
				doComputerRound();
			}
		}
		else if (message.equals("buyHouse"))
		{ 
			buyHouse();
			if(!stopGame && currentActivePlayer instanceof ComputerPlayer)//go on next state
			{
				doComputerRound();
			}
		}
		else if (message.equals("buyAsset"))
		{
			buyAsset();
			if(!stopGame &&currentActivePlayer instanceof ComputerPlayer)//go on next state
			{
				doComputerRound();
			}
		}
		else if(message.equals("throwDie"))
		{
			thrownDie();
			if(!stopGame && currentActivePlayer instanceof ComputerPlayer)//go on next state
			{
				doComputerRound();
			}
		}
	}
}
