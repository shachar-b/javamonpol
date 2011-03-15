/**
 * 
 */
package squares;

import java.io.File;

import cards.ActionCard;
import cards.ShaffledDeck;

import monopoly.GameManager;
import monopoly.Monopoly;
import players.Player;

/**
 * public class ActionCardSquare extends Square
 * @see Square
 * @visibility public
 * a Call up or Surprise Square in the monopoly game 
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class ActionCardSquare extends Square {
	
	int sign;
	
	/**
	 * method public ActionCardSquare(int sign1)
	 * @visibility public
	 * a constructor for ActionCardSquare
	 * @param sign1 - either -1 for call up or 1 for surprise
	 */
	public ActionCardSquare(int sign1)
	{
		sign=sign1;
	}

	
	/* (non-Javadoc)
	 * @see squares.Square#getName()
	 */
	@Override
	public String getName() {return sign==1?"Surprise":"CallUp";}
	
	/* (non-Javadoc)
	 * @see squares.Square#playerArrived(players.Player)
	 * gives the player a card from the correct deck and makes an action according to it
	 */
	@Override
	public void playerArrived(Player player) {
		ShaffledDeck currDeck=sign==1?GameManager.currentGame.getSurprise():GameManager.currentGame.getCallUp();
		ActionCard currCard = currDeck.takeCard();
		GameManager.CurrentUI.notifyPlayerGotCard(player, currCard);
		
		if (currCard.isGetOutOfJailFreeCard())
			player.setGetOutOfJailFreeCardPlaceHolder(currCard);
		else
			currCard.doCard(player);
	}
}
