/**
 * 
 */
package players;

import monopoly.GameManager;
import ui.utils.ImagePanel;
import assets.Asset;
import assets.City;

/**
 * class ComputerPlayer extends Player
 * @see Player
 * public
 * A computer player in the Monopoly game.
 * @author Omer Shenhar and Shachar Butnaro
 */
public class ComputerPlayer extends Player {

	private static final int BUY_THRESHHOLD=300;// the minimal amount of funds to leave in balance when buying something
	private static int generatedComputerNumber=1;

	/**
	 * Constructor for computer player.
	 * Generated a name and sends to super constructor.
	 */
	public ComputerPlayer() {
		super("Computer"+generatedComputerNumber
				,new ImagePanel(GameManager.IMAGES_FOLDER+"/playerIcons/"+generatedComputerNumber+".png"));
		generatedComputerNumber++;
	}

	/* (non-Javadoc)
	 * @see players.Player#buyDecision(java.lang.String, assets.Asset, int)
	 * If computer will be left with BUY_THRESHHOLD or more after buying - chooses to buy.
	 */
	@Override
	public Boolean buyDecision(Asset asset) {
		if( Balance-asset.getCost()>= BUY_THRESHHOLD )
		{
			return true;
		}
		else
		{
			GameManager.CurrentUI.notifyPlayerCantBuy(this, asset.getName(), asset.getCost());
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see players.Player#buyHouseDecision(assets.City)
	 * Same buying algorithm as an asset.
	 */
	@Override
	public Boolean buyHouseDecision(City asset)
	{
		if (Balance-asset.getCostOfHouse()>=BUY_THRESHHOLD)
		{
			return true;
		}
		else
		{
			GameManager.CurrentUI.notifyPlayerCantBuy(this, "house in " + asset.getName(), asset.getCostOfHouse());
			return false;
		}
	}
}