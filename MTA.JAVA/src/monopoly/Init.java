package monopoly;

import java.util.ArrayList;

import squares.ActionCardSquare;
import squares.GoToJailSquare;
import squares.JailSlashFreePassSquare;
import squares.ParkingSquare;
import squares.Square;
import squares.StartSquare;
import assets.City;
import assets.Country;
import assets.UtilOrTranspoAsset;
import assets.UtilOrTranspoAssetGroup;
import cards.ActionCard;
import cards.ShaffledDeck;

/**
 * Init implements MonopolyInitilizer
 * @visibility public
 * @see MonopolyInitilizer
 * a hard coded board Initializer
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class Init implements MonopolyInitilizer{
		
	/* (non-Javadoc)
	 * @see monopoly.MonopolyInitilizer#initDie()
	 */
	@Override
	public OldDice[] initDie()
	{
		OldDice[] die = new OldDice[GameManager.NUM_OF_DIE];
		for (int i =0; i<die.length; i++)
		{
			die[i]=new OldDice();
			die[i].initGenerator();
		}
		return die;
	}
	
	/* (non-Javadoc)
	 * @see monopoly.MonopolyInitilizer#initSurprise()
	 */
	@Override
	public ShaffledDeck initSurprise()
	{
		ShaffledDeck surprise = new ShaffledDeck();
		// read surprise cards
		surprise.add(new ActionCard(ActionCard.SURPRISE_CARD, "you recived an inhertnce of 200", 200, GameManager.AgainstWho.Treasury, null, true));
		surprise.add(new ActionCard(ActionCard.SURPRISE_CARD, "for your army service get 250", 250, GameManager.AgainstWho.Treasury, null, true));		
		
		surprise.add(new ActionCard(ActionCard.SURPRISE_CARD, "Go start and get the bounus 200 from treasury", 0, GameManager.AgainstWho.Treasury, StartSquare.class, true));
		surprise.add(new ActionCard(ActionCard.SURPRISE_CARD, "Go to next utility or transport squre you dont get a start bounus", 0, GameManager.AgainstWho.Treasury, UtilOrTranspoAsset.class, false));

		surprise.add(new ActionCard(ActionCard.SURPRISE_CARD, "for your wedding get 200 from each other player", 200, GameManager.AgainstWho.OtherPlayers, null, true));
		surprise.add(new ActionCard(ActionCard.SURPRISE_CARD, "you won the anuual player poker tournumet take 450 from all the other losers", 450, GameManager.AgainstWho.OtherPlayers, null, true));
		surprise.add(new ActionCard(ActionCard.SURPRISE_CARD, "Get out of jail free card!", 0, GameManager.AgainstWho.Treasury, Square.class, false));

		return surprise;
	}
	
	/* (non-Javadoc)
	 * @see monopoly.MonopolyInitilizer#initCallUp()
	 */
	@Override
	public ShaffledDeck initCallUp()
	{
		ShaffledDeck callUp = new ShaffledDeck();
		// read call-up cards
		callUp.add(new ActionCard(ActionCard.CALLUP_CARD, "income tax is on to you! you have to pay them 245 ", 200, GameManager.AgainstWho.Treasury, null, true));
		callUp.add(new ActionCard(ActionCard.CALLUP_CARD, "DOH! your car got stuck becuse it has no gas pay 200 for a taxi and 400 for a tank of gas", 600, GameManager.AgainstWho.Treasury, null, true));
		callUp.add(new ActionCard(ActionCard.CALLUP_CARD, "parked in a handicap parking pay 1000", 1000, GameManager.AgainstWho.Treasury, null, true));
		
		callUp.add(new ActionCard(ActionCard.CALLUP_CARD, "you lost a law suite,pay all other players 100 for giving them food poisening", 100, GameManager.AgainstWho.OtherPlayers, null, true));
		callUp.add(new ActionCard(ActionCard.CALLUP_CARD, "its saint patric day! go and have a drink in the next city and pay 100 for the texi", 100, GameManager.AgainstWho.OtherPlayers, City.class, false));
		callUp.add(new ActionCard(ActionCard.CALLUP_CARD, "you were scammed! you bought a car with no engine pay 350 for the engine parts and go to the parking lot for a turn dont collect start bouns", 350, GameManager.AgainstWho.Treasury, ParkingSquare.class, false));

		return callUp;
	}

	
	/* (non-Javadoc)
	 * @see monopoly.MonopolyInitilizer#initUtilities()
	 */
	public UtilOrTranspoAssetGroup initUtilities()
	{
		return new UtilOrTranspoAssetGroup("Utilities", 800);
	}
	
	public UtilOrTranspoAssetGroup initTransportation()
	{
		return new UtilOrTranspoAssetGroup("Transportation", 800);
	}
	
	/**
	 * @return
	 */
	private ArrayList<Country> initCountries()
	{
		ArrayList<Country> countries = new ArrayList<Country>();
		
		countries.add(new Country("Slovakia"));
		countries.add(new Country("England"));
		countries.add(new Country("France"));
		countries.add(new Country("Thailand"));
		countries.add(new Country("Israel"));
		countries.add(new Country("Argentina"));
		countries.add(new Country("USA"));
		countries.add(new Country("Bahrain"));
		
		return countries;
	}
	
	/* (non-Javadoc)
	 * @see monopoly.MonopolyInitilizer#initBoard()
	 */
	@Override
	public ArrayList<Square> initBoard()
	{
		UtilOrTranspoAssetGroup utilities = initUtilities();
		UtilOrTranspoAssetGroup transportation = initTransportation();
		ArrayList<Country> countries = initCountries();
		
		ArrayList<Square> gameBoard = new ArrayList<Square>(GameManager.NUMBER_OF_SQUARES);
		int temp[] = {100, 200, 300, 400};
		int tempCostCity = 50;
		int tempCostHouse = 80;
		int itrCountries=0;
		gameBoard.add(0, new StartSquare());
		gameBoard.add(1,new City(countries.get(itrCountries),"Bratislava",tempCostCity,tempCostHouse,temp));
		gameBoard.add(2,new City(countries.get(itrCountries),"Trencin",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		gameBoard.add(3, new ActionCardSquare(1) /*surprise*/);
		gameBoard.add(4, new UtilOrTranspoAsset(transportation, "Jump Jets", 250, 150));
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
		gameBoard.add(13, new UtilOrTranspoAsset(utilities, "Electricity Co.1", 250, 150));
		gameBoard.add(14, new UtilOrTranspoAsset(transportation, "Bus", 250, 150));
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
		gameBoard.add(23, new UtilOrTranspoAsset(transportation, "Plane1", 250, 150));
		gameBoard.add(24,new City(countries.get(itrCountries),"Buenos Aires",tempCostCity,tempCostHouse,temp));
		gameBoard.add(25,new City(countries.get(itrCountries),"Mendoza",tempCostCity,tempCostHouse,temp));
		gameBoard.add(26,new City(countries.get(itrCountries),"Salta",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		gameBoard.add(27, new GoToJailSquare());
		gameBoard.add(28,new City(countries.get(itrCountries),"New York",tempCostCity,tempCostHouse,temp));
		gameBoard.add(29,new City(countries.get(itrCountries),"Washington",tempCostCity,tempCostHouse,temp));
		gameBoard.add(30,new City(countries.get(itrCountries),"Las Vegas",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		gameBoard.add(31, new UtilOrTranspoAsset(utilities, "Electricity Co.2", 250, 150));
		gameBoard.add(32, new UtilOrTranspoAsset(transportation, "Plane2", 250, 150));
		gameBoard.add(33,new City(countries.get(itrCountries),"Manama",tempCostCity,tempCostHouse,temp));
		gameBoard.add(34,new City(countries.get(itrCountries),"Riffa",tempCostCity,tempCostHouse,temp));
		gameBoard.add(35,new City(countries.get(itrCountries),"Sitra",tempCostCity,tempCostHouse,temp));
		++itrCountries;
		return gameBoard;
		
	}
}
