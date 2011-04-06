package monopoly;

import java.util.Random;

/**
 * class Dice
 * @visibility public
 * this represent the dice in the monopoly game
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class OldDice {
	private Random generator;
	
	/**
	 * method void initGenerator()
	 * @visibility public
	 * this method initializes the dice generator 
	 */
	public void initGenerator()
	{
		generator = new Random();
	}
	
	
	/**
	 * method int rollDice()
	 * @visibility public
	 * rolls the dice and returns the outcome
	 * @return a int from 1 to 6 representing a single dice toss
	 */
	public int rollDice()
	{
		int LastRollOutcome = generator.nextInt(6)+1; 
		GameManager.CurrentUI.notifyDiceRoll(LastRollOutcome);
		return LastRollOutcome;
	}

}
