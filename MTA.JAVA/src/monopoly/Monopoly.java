/**
 * 
 */
package monopoly;

import java.util.ArrayList;

import players.ComputerPlayer;
import players.HumanPlayer;
import players.Player;
import squares.ActionCardSquare;
import squares.GoToJailSquare;
import squares.JailSlashFreePassSquare;
import squares.ParkingSquare;
import squares.Square;
import squares.StartSquare;
import ui.ConsoleUI;
import assets.Asset;
import assets.City;
import assets.Country;
import assets.UtilOrTranspoAsset;
import assets.UtilOrTranspoAssetGroup;
import cards.ActionCard;
import cards.ShaffledDeck;

/**
 * @author Omer Shenhar & Shachar Butnaro
 *
 */
public class Monopoly
{
	private ArrayList<Player> gamePlayers = new ArrayList<Player>();
	private ArrayList<Square> gameBoard = new ArrayList<Square>(GameManager.NUMBER_OF_SQUARES);
	private ConsoleUI userInterface = (ConsoleUI) GameManager.CurrentUI;
	private Dice[] die = new Dice[GameManager.NUM_OF_DIE]; 
	private ArrayList<Country> countries = new ArrayList<Country>();
	private UtilOrTranspoAssetGroup utilities = new UtilOrTranspoAssetGroup("Utilities", 800);
	private UtilOrTranspoAssetGroup transportation = new UtilOrTranspoAssetGroup("Transportation", 800);
	private ShaffledDeck surprise = new ShaffledDeck();
	private ShaffledDeck callUp = new ShaffledDeck();


	public Player getPlayer(int index) throws IndexOutOfBoundsException
	{
		return getGamePlayers().get(index);
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
				p.makeSellOffers();
			}
		}
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

	private void debtCollector()
	{

	}
	private void initCards() 
	{
		
		// read surprise cards
		surprise.add(new ActionCard(1, "you recived an inhertnce of 200", 200, GameManager.AgainstWho.Treasury, null, true));
		surprise.add(new ActionCard(1, "for your army service get 250", 250, GameManager.AgainstWho.Treasury, null, true));		
		
		surprise.add(new ActionCard(1, "Go start and get the bounus 200 from treasury", 0, GameManager.AgainstWho.Treasury, StartSquare.class, true));
		surprise.add(new ActionCard(1, "Go to next utility or transport squre you dont get a start bounus", 0, GameManager.AgainstWho.Treasury, UtilOrTranspoAsset.class, false));

		surprise.add(new ActionCard(1, "for your wedding get 200 from each other player", 200, GameManager.AgainstWho.OtherPlayers, null, true));
		surprise.add(new ActionCard(1, "you won the anuual player poker tournumet take 450 from all the other losers", 450, GameManager.AgainstWho.OtherPlayers, null, true));
		surprise.add(new ActionCard(1, "Get out of jail free card!", 0, GameManager.AgainstWho.Treasury, Square.class, false));
		

		


		// read call-up cards
		callUp.add(new ActionCard(-1, "income tax is on to you! you have to pay them 245 ", 200, GameManager.AgainstWho.Treasury, null, true));
		callUp.add(new ActionCard(-1, "DOH! your car got stuck becuse it has no gas pay 200 for a taxi and 400 for a tank of gas", 600, GameManager.AgainstWho.Treasury, null, true));
		callUp.add(new ActionCard(-1, "parked in a handicap parking pay 1000", 1000, GameManager.AgainstWho.Treasury, null, true));
		
		callUp.add(new ActionCard(-1, "you lost a law suite,pay all other players 100 for giving them food poisening", 100, GameManager.AgainstWho.OtherPlayers, null, true));
		callUp.add(new ActionCard(-1, "its saint patric day! go and have a drink in the next city and pay 100 for the texi", 100, GameManager.AgainstWho.OtherPlayers, City.class, false));
		callUp.add(new ActionCard(-1, "you were scammed! you bought a car with no engine pay 350 for the engine parts and go to the parking lot for a turn dont collect start bouns", 350, GameManager.AgainstWho.Treasury, ParkingSquare.class, false));

		
		
	}

	private void init()
	{
		//init die
		for (int i =0; i<die.length; i++)
		{
			die[i]=new Dice();
			die[i].initGenerator();
		}

		//init CARDS
		initCards();
		//surprise.setMonopoly(this);
		//callUp.setMonopoly(this);

		// init countries
		countries.add(new Country("Slovakia"));
		countries.add(new Country("England"));
		countries.add(new Country("France"));
		countries.add(new Country("Thailand"));
		countries.add(new Country("Israel"));
		countries.add(new Country("Argentina"));
		countries.add(new Country("USA"));
		countries.add(new Country("Bahrain"));
		// read transportations
		// read utilities
		
		// init board
		// 1
		int temp[] = {100, 200, 300, 400};
		int tempCostCity = 50;
		int tempCostHouse = 80;
		int itrCountries=0;
		gameBoard.add(0, new StartSquare());
		gameBoard.add(1,new City(countries.get(itrCountries),"Bratislava",tempCostCity,tempCostHouse,temp));
		gameBoard.add(2,new City(countries.get(itrCountries),"Trencin",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		gameBoard.add(3, new ActionCardSquare(1) /*surprise*/);
		gameBoard.add(4, new UtilOrTranspoAsset(transportation, "Jump Jets", 250, 150, 300));
		gameBoard.add(5,new City(countries.get(itrCountries),"London",tempCostCity,tempCostHouse,temp));
		gameBoard.add(6,new City(countries.get(itrCountries),"Wales",tempCostCity,tempCostHouse,temp));
		gameBoard.add(7,new City(countries.get(itrCountries),"Yorkshire",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		gameBoard.add(8, new ActionCardSquare(-1) /*callUp*/);
		gameBoard.add(9, new JailSlashFreePassSquare());
		gameBoard.add(10,new City(countries.get(itrCountries),"Paris",tempCostCity,tempCostHouse,temp));
		gameBoard.add(11,new City(countries.get(itrCountries),"Lyon",tempCostCity,tempCostHouse,temp));
		gameBoard.add(12,new City(countries.get(itrCountries),"Toulouse",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		gameBoard.add(13, new UtilOrTranspoAsset(utilities, "Electricity Co.1", 250, 150, 300));
		gameBoard.add(14, new UtilOrTranspoAsset(transportation, "Bus", 250, 150, 300));
		gameBoard.add(15,new City(countries.get(itrCountries),"Phuket",tempCostCity,tempCostHouse,temp));
		gameBoard.add(16,new City(countries.get(itrCountries),"Bangkok",tempCostCity,tempCostHouse,temp));
		gameBoard.add(17,new City(countries.get(itrCountries),"Yala",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		gameBoard.add(18, new ParkingSquare());
		gameBoard.add(19,new City(countries.get(itrCountries),"Tel Aviv",tempCostCity,tempCostHouse,temp));
		gameBoard.add(20,new City(countries.get(itrCountries),"Haifa",tempCostCity,tempCostHouse,temp));
		gameBoard.add(21,new City(countries.get(itrCountries),"Jerusalem",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		gameBoard.add(22, new ActionCardSquare(-1)/*callUp*/);
		gameBoard.add(23, new UtilOrTranspoAsset(transportation, "Plane1", 250, 150, 300));
		gameBoard.add(24,new City(countries.get(itrCountries),"Buenos Aires",tempCostCity,tempCostHouse,temp));
		gameBoard.add(25,new City(countries.get(itrCountries),"Mendoza",tempCostCity,tempCostHouse,temp));
		gameBoard.add(26,new City(countries.get(itrCountries),"Salta",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		gameBoard.add(27, new GoToJailSquare());
		gameBoard.add(28,new City(countries.get(itrCountries),"New York",tempCostCity,tempCostHouse,temp));
		gameBoard.add(29,new City(countries.get(itrCountries),"Washington",tempCostCity,tempCostHouse,temp));
		gameBoard.add(30,new City(countries.get(itrCountries),"Las Vegas",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		gameBoard.add(31, new UtilOrTranspoAsset(utilities, "Electricity Co.2", 250, 150, 300));
		gameBoard.add(32, new UtilOrTranspoAsset(transportation, "Plane2", 250, 150, 300));
		gameBoard.add(33,new City(countries.get(itrCountries),"Manama",tempCostCity,tempCostHouse,temp));
		gameBoard.add(34,new City(countries.get(itrCountries),"Riffa",tempCostCity,tempCostHouse,temp));
		gameBoard.add(35,new City(countries.get(itrCountries),"Sitra",tempCostCity,tempCostHouse,temp));
		++itrCountries;
	}


	public ArrayList<Player> getGamePlayers() {
		return gamePlayers;
	}

	public static void main(String[] args) {
		Monopoly game = new Monopoly();
		GameManager.currentGame = game;
		game.getGamePlayers().add(new HumanPlayer());
		game.getGamePlayers().add(new ComputerPlayer());
		//game.getGamePlayers().add(new ComputerPlayer());
		//game.getGamePlayers().add(new ComputerPlayer());
		//game.getGamePlayers().add(new ComputerPlayer());
		game.init();
		game.play();
	}

	public ShaffledDeck getSurprise() {
		return surprise;
	}

	public ShaffledDeck getCallUp() {
		return callUp;
	}
}
