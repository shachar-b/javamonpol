/**
 * 
 */
package players;

import ui.utils.ImagePanel;

/**
 * class HumanPlayer extends Player
 * @see Player
 * public
 * A human player in the Monopoly game.
 * @author Omer Shenhar and Shachar Butnaro
 */
public class HumanPlayer extends Player {

	//TODO : Edit documentation
	/**
	 * Constructor for human player.
	 * Gets a name from the UI and transfers it the the super constructor.
	 */
	public HumanPlayer(String name,String playerIconPath) {
		super(name,new ImagePanel(playerIconPath));
	}
}