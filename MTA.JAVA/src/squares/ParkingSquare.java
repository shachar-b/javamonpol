package squares;

import monopoly.GameManager;
import players.Player;

/**
 * public class StartSquare extends Square
 * @see Square
 * @visibility public
 * a parking Square in the monopoly game 
 * @author Omer Shenhar and Shachar Butnaro
 *
 */

public class ParkingSquare extends Square {

	/* (non-Javadoc)
	 * @see squares.Square#playerArrived(players.Player)
	 * makes the player wait  a turn
	 */
	@Override
	public void playerArrived(Player player) {
		GameManager.CurrentUI.notifyPlayerLandsOnParkingSquare(player);
		player.setGoOnNextTurn(false);
	}
	
	/* (non-Javadoc)
	 * @see squares.Square#shouldPlayerMove(players.Player)
	 * @return false if the Player landed on the last turn false otherwise
	 */
	@Override
	public boolean shouldPlayerMove (Player player)
	{//No need to check the die here - This is Parking
		if(player.getGoOnNextTurn()==false)
		{
			GameManager.CurrentUI.notifyPlayerIsParked(player);
			player.setGoOnNextTurn(true);
			return false;
		}
		else
		{
			return true;
		}
	}
}