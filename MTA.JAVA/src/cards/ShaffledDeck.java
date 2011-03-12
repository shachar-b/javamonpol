/**
 * 
 */
package cards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Omer Shenhar & Shachar Butnaro
 * this is a shuffled Deck of action cards were each card appears once
 * each card has the same probability of being polled
 */
public class ShaffledDeck  {

	private ArrayList<ActionCard> deck = new ArrayList<ActionCard>(); 
	private Random randomGen=new Random();

	
	/**
	 * @return true if the deck is empty. false otherwise
	 */
	public boolean isEmpty() {
		return deck.isEmpty();
	}


	public int size() {
		return deck.size();
	}
	
	/**
	 * @param card - a card which is not in the Deck
	 */
	public void add(ActionCard card) {
		 if(!deck.contains(card))
			 deck.add(card);
	}
	
	/**this function returns a random card from the deck without removing it
	 * @return a random card from the deck
	 */
	public ActionCard takeCard() {
		ActionCard card = deck.get(randomGen.nextInt(deck.size())); 
		if (card.isGetOutOfJailFreeCard()){
			deck.remove(card);
		}
		return card;
	}
	
}
