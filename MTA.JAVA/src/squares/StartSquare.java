package squares;

import monopoly.GameManager;
import players.Player;

public class StartSquare extends Square {

	@Override
	public void playerArrived(Player player) {
		GameManager.CurrentUI.notifyPlayerLandsOnStartSquare(player);
		player.ChangeBalance(GameManager.START_LAND_BONUS, GameManager.ADD);
	}
}
