package monopoly;

import java.util.Random;

public class Dice {
	private Random generator;
	
	public void initGenerator()
	{
		generator = new Random();
	}
	
	
	public int rollDice()
	{
		int LastRollOutcome = generator.nextInt(6)+1; 
		GameManager.CurrentUI.notifyDiceRoll(LastRollOutcome);
		return LastRollOutcome;
	}

}
