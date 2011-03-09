package squares;

import monopoly.GameManager;
import players.Player;

public class GoToJailSquare extends Square {

	@Override
	public void playerArrived(Player player) {
		GameManager.CurrentUI.notifyPlayerLandsOnGoToJail(player);
		//TODO : CHECK FOR BUGS
		player.setGoOnNextTurn(false);
		GameManager.currentGame.gotoNextSquareOfType(player, JailSlashFreePassSquare.class, false);
	}

}
