/**
 * 
 */
package cards;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class Deck implements Queue<ActionCard> {

	ConcurrentLinkedQueue<ActionCard> deck = new ConcurrentLinkedQueue<ActionCard>(); 
	
	@Override
	public boolean addAll(Collection<? extends ActionCard> c) {
		return deck.addAll(c);
	}

	@Override
	public void clear() {
		deck.clear();
	}

	@Override
	public boolean contains(Object o) {
		return deck.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return deck.isEmpty();
	}

	@Override
	public Iterator<ActionCard> iterator() {
		return deck.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return deck.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return deck.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return deck.retainAll(c);
	}

	@Override
	public int size() {
		return deck.size();
	}

	@Override
	public Object[] toArray() {
		return deck.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return deck.toArray(a);
	}

	@Override
	public boolean add(ActionCard e) {
		return deck.add(e);
	}

	@Override
	public ActionCard element() {
		return deck.element();
	}

	@Override
	public boolean offer(ActionCard e) {
		return deck.offer(e);
	}

	@Override
	public ActionCard peek() {
		return deck.peek();
	}

	@Override
	public ActionCard poll() {
		return deck.poll();
	}

	@Override
	public ActionCard remove() {
		return deck.remove();
	}
}
