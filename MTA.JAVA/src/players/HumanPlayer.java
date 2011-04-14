/**
 * 
 */
package players;

import monopoly.GameManager;
import ui.utils.ImagePanel;
import assets.Asset;
import assets.City;

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

	/* (non-Javadoc)
	 * @see players.Player#buyDecision(java.lang.String, assets.Asset, int)
	 * Prompts the player to choose.
	 */
	@Override
	public Boolean buyDecision(Asset asset) {
		String message;
		if (asset.getCost()<Balance)
		{
			message = "Would you like to buy " + (asset.getName()) + " in " + asset.getGroup().getName() + " for " + asset.getCost() + "?";
			return GameManager.CurrentUI.askYesNoQuestion(message);
		}
		else
		{
			GameManager.CurrentUI.notifyPlayerCantBuy(this, asset.getName(), asset.getCost());
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see players.Player#buyHouseDecision(assets.City)
	 * Prompts the player to choose.
	 */
	@Override
	public Boolean buyHouseDecision(City asset)
	{
		if (asset.getCostOfHouse()<Balance) //Player can't buy if not enough cash!
		{
			String message = "Would you like to buy house number " + (asset.getNumHouses()+1) + " for " + asset.getCostOfHouse() + "?";
			return GameManager.CurrentUI.askYesNoQuestion(message);
		}
		else
		{
			GameManager.CurrentUI.notifyPlayerCantBuy(this, "house in " + asset.getName(), asset.getCostOfHouse());
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see players.Player#chooseToForfeit()
	 * Prompts the player to choose.
	 */
	@Override
	public boolean chooseToForfeit() {
		return GameManager.CurrentUI.askYesNoQuestion("Would you like to forfeit?");		
	}
}