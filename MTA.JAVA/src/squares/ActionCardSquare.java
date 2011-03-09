/**
 * 
 */
package squares;

import java.io.File;

import cards.ActionCard;
import cards.Deck;

import monopoly.GameManager;
import monopoly.Monopoly;
import players.Player;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class ActionCardSquare extends Square {

	Monopoly monopoly = GameManager.currentGame;
	int sign;
	
	public ActionCardSquare(int sign1)
	{
		sign=sign1;
	}

	public void init(File inFile)
	{
		//TODO : write
	}
	
	@Override
	public String getName() {return sign==1?"Surprise":"CallUp";}
	
	/* (non-Javadoc)
	 * @see squares.Square#playerArrived(players.Player)
	 */
	@Override
	public void playerArrived(Player player) {
		Deck currDeck=sign==1?GameManager.currentGame.getSurprise():GameManager.currentGame.getCallUp();
		ActionCard currCard = currDeck.poll();
		GameManager.CurrentUI.notifyPlayerGotCard(player, currCard);
		currCard.doCard(player);
		currDeck.add(currCard);
	}
}
