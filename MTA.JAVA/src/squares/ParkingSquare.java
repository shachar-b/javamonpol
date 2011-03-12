package squares;

import monopoly.GameManager;
import players.Player;

public class ParkingSquare extends Square {

	@Override
	public void playerArrived(Player player) {
		GameManager.CurrentUI.notifyPlayerLandsOnParkingSquare(player);
		player.setGoOnNextTurn(false);
	}
	
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