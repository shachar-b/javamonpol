package squares;

import monopoly.GameManager;
import monopoly.GameManager.jailActions;
import players.Player;

/**
 * public class JailSlashFreePassSquare extends Square
 * @see Square
 * @visibility public
 * a Jail and FreePassSquare Square in the monopoly game 
 * @author Omer Shenhar and Shachar Butnaro
 *
 */

public class JailSlashFreePassSquare extends Square {

	/* (non-Javadoc)
	 * @see squares.Square#playerArrived(players.Player)
	 */
	@Override
	public void playerArrived(Player player)
	{
		if (player.getGoOnNextTurn()==true)
		{
			GameManager.CurrentUI.notifyPlayerLandsOnJailFreePass(player);
			// nothing to do - player is on free pass
		}

	}

	/* (non-Javadoc)
	 * @see squares.Square#shouldPlayerMove(players.Player)
	 * this method returns true if the Player isn't been thrown to jail -free pass mode
	 * otherwise if the player has a get out of jail card and he want to use it returns false and release it from jail on next turn(return true).-bribe mode
	 * otherwise it will try to roll a double. on either case returns false on turn call it would release(return true) him-lucky mode
	 */
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
				GameManager.CurrentUI.getFrame().getPlayerPanel().setGetOutOfJailButtonStatus(true);
				currAction=jailActions.USED_CARD;
			}
			else if (GameManager.currentGame.rollForADouble())
			{	//TODO : FIX THIS SHIT
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
	
	public void playerUsesGetOutOfJailCard(Player player)
	{
		player.setGoOnNextTurn(true);
		GameManager.currentGame.getSurprise().add(player.getGetOutOfJailFreeCardPlaceHolder());
		player.setGetOutOfJailFreeCardPlaceHolder(null);
	}
}
