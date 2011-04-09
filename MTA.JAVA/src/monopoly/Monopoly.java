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
	//private OldDice[] die; 
	private ShaffledDeck surprise = new ShaffledDeck();
	private ShaffledDeck callUp = new ShaffledDeck();
	private ArrayList<Square> gameBoard;
	private int playerIndex=0;
	private int roundNumber = 1;
	private Player currentActivePlayer;
	private Square currentPlayerSquare;
	private int state=0;
	private  Thread stateMechThread= new Thread(new Runnable() {
		
		@Override
		public void run() {
			PlayerPanel pane=GameManager.CurrentUI.getFrame().getPlayerPanel();
			currentPlayerSquare=gameBoard.get(currentActivePlayer.getCurrentPosition());
			switch (state) {
			case 0:
				state++;//next state is roll die
				if(currentActivePlayer.hasGetOutOfJailFreeCard() &&
						!currentPlayerSquare.shouldPlayerMove(currentActivePlayer) &&(currentPlayerSquare instanceof JailSlashFreePassSquare))
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
				
			case 1:
				state++;//next state is 2- try to buy an asset 
				if (currentPlayerSquare instanceof JailSlashFreePassSquare ||currentPlayerSquare.shouldPlayerMove(currentActivePlayer))
					{//dont do it only on parking- if GOJC was used this wont be reached
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException("state mechin problem");
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
							throw new RuntimeException("state mechin problem");
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
							throw new RuntimeException("state mechin problem");
						}
						pane.ClickBuyHouseButton();
						break;
					}

				}
			case 4://case 4
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException("state mechin problem");
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
	void init()
	{
		MonopolyInitilizer gameInitializer = new Init();
		// init gameBoard
		gameBoard = gameInitializer.initBoard();
		// init UI
		GameManager.CurrentUI = new UI();
		userInterface = (UI)GameManager.CurrentUI;
		//init die
		//	die = gameInitializer.initDie();
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

	private void doComputerRound()
	{
		stateMechThread.run();
	}




	/**
	 * method private int rollDie()
	 * this method rool all the dice
	 * @return the sum of the dice roll
	 */
	private void rollDie()
	{
		Dice.getGameDice().makeItRoll();
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
	
	public int getRoundNumber()
	{
		return roundNumber;
	}
	
	public void resetRoundNumber()
	{
		roundNumber=1;
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
		{//TODO : Remove this! (...?)
			userInterface.notifyGameWinner(gamePlayers.get(0));
		}

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
		if (!gamePlayers.isEmpty()) //To prevent trying to end when all players were removed
		{							//(Happens if trying to start a new game, mid current game.)
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
	}

	private void thrownDie() {
		if (currentPlayerSquare instanceof JailSlashFreePassSquare && !currentPlayerSquare.shouldPlayerMove(currentActivePlayer))
		{
			boolean hasDouble = rollForADouble();
			((JailSlashFreePassSquare)currentPlayerSquare).release(currentActivePlayer, hasDouble);		}
		else if (gameBoard.get(currentActivePlayer.getCurrentPosition()).shouldPlayerMove(currentActivePlayer))
		{
			int[] result = ui.guiComponents.dice.Dice.getGameDice().getDieOutcome();
			int dieSum = result[0]+result[1];
			movePlayer(currentActivePlayer, dieSum, true);
		}

	}

	private void buyHouse() {
		((City)currentPlayerSquare).BuyHouse(currentActivePlayer);
		GameManager.CurrentUI.getFrame().getPlayerPanel().setBuyHouseButtonStatus(false);

	}

	private void useGetOutOfJail() {
		((JailSlashFreePassSquare)currentPlayerSquare).playerUsesGetOutOfJailCard(currentActivePlayer);
		GameManager.CurrentUI.getFrame().getPlayerPanel().setGetOutOfJailButtonStatus(false);

	}

	private void forfit() {
		removePlayerFromGame(currentActivePlayer);
		playerIndex--;
		endTurn();
	}
	
	private void buyAsset() {
		((Asset)currentPlayerSquare).buyAsset(currentActivePlayer);	
		GameManager.CurrentUI.getFrame().getPlayerPanel().setBuyAssetButtonStatus(false);

	}

	public void eventDispatch(String message) {
		currentActivePlayer = gamePlayers.get(playerIndex);
		currentPlayerSquare = gameBoard.get(currentActivePlayer.getCurrentPosition());
		if(message.equals("computer"))
		{
			doComputerRound();
		}
		else if(message.equals("forfeit"))
		{
			forfit();
		}
		else if(message.equals("endTurn"))
		{
			endTurn();
		}
		else if(message.equals("getOutOfJail"))
		{
			useGetOutOfJail();
			if(currentActivePlayer instanceof ComputerPlayer)//go on next state
			{
				doComputerRound();
			}
		}
		else if (message.equals("buyHouse"))
		{ 
			buyHouse();
			if(currentActivePlayer instanceof ComputerPlayer)//go on next state
			{
				doComputerRound();
			}
		}
		else if (message.equals("buyAsset"))
		{
			buyAsset();
			if(currentActivePlayer instanceof ComputerPlayer)//go on next state
			{
				doComputerRound();
			}

		}
		else if(message.equals("throwDie"))
		{
			thrownDie();
			if(currentActivePlayer instanceof ComputerPlayer)//go on next state
			{
				doComputerRound();
			}

		}

	}
}
