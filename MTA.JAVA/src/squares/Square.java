package squares;

import monopoly.Dice;
import players.Player;

public abstract class Square {
	
	protected String name=this.getClass().getSimpleName();
	
	public boolean shouldPlayerMove(Player player)
	{
		return true; //To be overridden by Jail and Parking squares.
					//Also prevents the player from rolling the die (on parking).
	}
	
	public abstract void playerArrived(Player player);

	public String getName() {
		return name;
	}
}