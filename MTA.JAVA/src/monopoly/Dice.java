package monopoly;

import java.util.Random;

public class Dice {
	private int LastRollOutcome=0;
	private Random generator;
	
	public void initGenerator()
	{
		generator = new Random();
	}
	
	
	public int rollDice()
	{
		LastRollOutcome = generator.nextInt(6)+1; 
		GameManager.CurrentUI.displayMessage("Rolled a " + LastRollOutcome + "." );
		return LastRollOutcome;
	}
	
	public int getLastRollOutcome()
	{
		return LastRollOutcome;
	}
}
