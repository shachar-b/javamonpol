package monopoly;

import players.Player;
import ui.ConsoleUI;
import ui.IUI;

public class GameManager {
	public static final int MAX_NUMBER_OF_PLAYERS = 6;
	public static final int MIN_NUMBER_OF_PLAYERS = 2;
	public static final int MAX_NUMBER_OF_HOUSES = 3;
	public static final int NUMBER_OF_SQUARES = 36;
	public static final int START_LAND_BONUS = 200; //Is actually 400 with pass bonus
	public  static final int START_PASS_BONUS = 200;
	public static final int INITAL_FUNDS=1500;
	public static final IUI CurrentUI=new ConsoleUI();
	public static final int ADD = 1;
	public static final int SUBTRACT = -1;
	public static final int NUM_OF_DIE = 2;
	public static final int MAX_NUM_OF_SELL_OFFERS = 3;
	public static final Player assetKeeper = null; //This is the "Kupa"
	public static Monopoly currentGame;
	

	public static enum jailActions {USED_CARD, ROLLED_DOUBLE, STAY_IN_JAIL}
	
	public static enum SquareIndex
	{
		START(0), JAIL(9);

		private int index;

		private SquareIndex(int si)
		{
			index = si;
		}

		public int getIndex()
		{
			return index;
		}
	}

	public static enum AgainstWho
	{
		Treasury(1), OtherPlayers(2);

		private int code;

		private AgainstWho(int code)
		{
			this.code = code;
		}
		public int getCode()
		{
			return code;
		}
	}
}