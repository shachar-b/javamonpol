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
		if (player.getGoOnNextTurn())
			return true;
		else
		{
			if (player.hasGetOutOfJailFreeCard()) //enable the option to use card
			{		
				GameManager.CurrentUI.getFrame().getPlayerPanel().setGetOutOfJailButtonStatus(true);
			}
			
			return false;
		}
	}
	
	public void playerUsesGetOutOfJailCard(Player player)
	{
		player.setGoOnNextTurn(true);
		GameManager.currentGame.getSurprise().add(player.getGetOutOfJailFreeCardPlaceHolder());
		player.setGetOutOfJailFreeCardPlaceHolder(null);
		GameManager.CurrentUI.notifyJailAction(player, jailActions.USED_CARD);

		
	}

	public void release(Player player,boolean hasDouble) {
		if(hasDouble)
		{
			player.setGoOnNextTurn(true);
			GameManager.CurrentUI.notifyJailAction(player, jailActions.ROLLED_DOUBLE);

		}
		else
		{
			player.setGoOnNextTurn(false);
			GameManager.CurrentUI.notifyJailAction(player, jailActions.STAY_IN_JAIL);
		}
	}
}
