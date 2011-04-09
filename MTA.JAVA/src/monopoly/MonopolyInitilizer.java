package monopoly;

import java.util.ArrayList;

import squares.Square;
import assets.UtilOrTranspoAssetGroup;
import cards.ShaffledDeck;

/**
 * interface MonopolyInitilizer
 * public
 * this Interface represents a class which can initialize a monopoly game
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public interface MonopolyInitilizer {

	/**
	 * method ShaffledDeck initSurprise();
	 * public
	 * @return a ShaffledDeck of surprise cards
	 */
	public ShaffledDeck initSurprise();

	/**
	 * method abstract ShaffledDeck initCallUp();
	 * public
	 * @return a ShaffledDeck of call up cards
	 */	public abstract ShaffledDeck initCallUp();
	
	/**
	 * method UtilOrTranspoAssetGroup initUtilities()
	 * public
	 * @return a Utilities group
	 */
	public UtilOrTranspoAssetGroup initUtilities();
	
	/**
	 * method UtilOrTranspoAssetGroup initTransportation()
	 * public
	 * @return an Initialized Transportation group
	 */
	public UtilOrTranspoAssetGroup initTransportation();

	/**
	 * method ArrayList<Square> initBoard()
	 * public
	 * Initializes the game board
	 * @return an Initialized game board
	 */
	public ArrayList<Square> initBoard();

}