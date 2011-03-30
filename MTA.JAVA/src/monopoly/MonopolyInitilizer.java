package monopoly;

import java.util.ArrayList;

import squares.Square;
import assets.UtilOrTranspoAssetGroup;
import cards.ShaffledDeck;

/**
 * interface MonopolyInitilizer
 * @visibility public
 * this Interface represents a class which can initialize a monopoly game
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public interface MonopolyInitilizer {

	/**
	 * method Dice[] initDie()
	 * @visibility public
	 * @return a array of die with 2 dice
	 */
	public Dice[] initDie();

	/**
	 * method ShaffledDeck initSurprise();
	 * @visibility public
	 * @return a ShaffledDeck of surprise cards
	 */
	public ShaffledDeck initSurprise();

	/**
	 * method abstract ShaffledDeck initCallUp();
	 * @visibility public
	 * @return a ShaffledDeck of call up cards
	 */	public abstract ShaffledDeck initCallUp();
	
	/**
	 * method UtilOrTranspoAssetGroup initUtilities()
	 * @visibility public
	 * @return a Utilities group
	 */
	public UtilOrTranspoAssetGroup initUtilities();
	
	/**
	 * method UtilOrTranspoAssetGroup initTransportation()
	 * @visibility public
	 * @return an Initialized Transportation group
	 */
	public UtilOrTranspoAssetGroup initTransportation();

	/**
	 * method ArrayList<Square> initBoard()
	 * @visibility public
	 * Initializes the game board
	 * @return an Initialized game board
	 */
	public ArrayList<Square> initBoard();

}