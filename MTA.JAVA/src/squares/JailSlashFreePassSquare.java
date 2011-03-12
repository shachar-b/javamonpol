package squares;

import monopoly.GameManager;
import players.Player;

public class JailSlashFreePassSquare extends Square {

	@Override
	public void playerArrived(Player player)
	{
		if (player.getGoOnNextTurn()==true)
		{
			GameManager.CurrentUI.notifyPlayerLandsOnJailFreePass(player);
			// nothing to do - player is on free pass
		}

	}

	@Override
	public boolean shouldPlayerMove(Player player)
	{
		String message;
		if (player.getGoOnNextTurn())
			return true;
		else
		{
			if (player.hasGetOutOfJailFreeCard()) //Player will also be asked if he wants to use
			{
					message = player.getName() + " used a get out of jail freeCard and is out of jail in next turn!";
					player.setGoOnNextTurn(true);
					GameManager.currentGame.getSurprise().add(player.getGetOutOfJailFreeCardPlaceHolder());
					player.setGetOutOfJailFreeCardPlaceHolder(null);
			}
			else if (GameManager.currentGame.rollForADouble())
			{
				message = player.getName() + " has a double and is out of jail in next turn!";
				player.setGoOnNextTurn(true);
			}
			else
			{
				message = player.getName() + " stays in jail!";
				player.setGoOnNextTurn(false);
			}
			GameManager.CurrentUI.displayMessage(message);
			return false;
		}
	}
}
