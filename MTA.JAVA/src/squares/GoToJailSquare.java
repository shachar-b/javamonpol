package squares;

import monopoly.GameManager;
import players.Player;

/**
 * public class GoToJailSquare extends Square
 * @see Square
 * @visibility public
 * a GoToJailSquare Square in the monopoly game 
 * @author Omer Shenhar and Shachar Butnaro
 *
 */

public class GoToJailSquare extends Square {

	/* (non-Javadoc)
	 * @see squares.Square#playerArrived(players.Player)
	 * on player arrivel the player is thrown to jail
	 */
	@Override
	public void playerArrived(Player player) {
		GameManager.CurrentUI.notifyPlayerLandsOnGoToJail(player);
		//TODO : CHECK FOR BUGS
		player.setGoOnNextTurn(false);
		GameManager.currentGame.gotoNextSquareOfType(player, JailSlashFreePassSquare.class, false);
	}

}
