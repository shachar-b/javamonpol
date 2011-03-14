package squares;

import monopoly.GameManager;
import monopoly.GameManager.jailActions;
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
		jailActions currAction;
		if (player.getGoOnNextTurn())
			return true;
		else
		{
			if (player.hasGetOutOfJailFreeCard()) //Player will also be asked if he wants to use
			{		
				currAction=jailActions.USED_CARD;
				player.setGoOnNextTurn(true);
				GameManager.currentGame.getSurprise().add(player.getGetOutOfJailFreeCardPlaceHolder());
				player.setGetOutOfJailFreeCardPlaceHolder(null);
			}
			else if (GameManager.currentGame.rollForADouble())
			{	
				currAction=jailActions.ROLLED_DOUBLE;
				player.setGoOnNextTurn(true);
			}
			else
			{
				currAction=jailActions.STAY_IN_JAIL;
				player.setGoOnNextTurn(false);
			}
			GameManager.CurrentUI.notifyJailAction(player, currAction);
			return false;
		}
	}
}
