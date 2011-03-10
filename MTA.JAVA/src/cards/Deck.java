/**
 * 
 */
package cards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class Deck implements Iterable<ActionCard>  {

	private ArrayList<ActionCard> deck = new ArrayList<ActionCard>(); 
	private Random randomGen=new Random();

	
	public boolean isEmpty() {
		return deck.isEmpty();
	}

	@Override
	public Iterator<ActionCard> iterator() {
		return deck.iterator();
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
	
	/**
	 * @return a random card from the deck
	 */
	public ActionCard poll() {
		return deck.get(randomGen.nextInt(deck.size()));
	}
	
}
