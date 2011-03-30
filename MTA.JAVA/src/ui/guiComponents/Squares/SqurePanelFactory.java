/**
 * 
 */
package ui.guiComponents.Squares;

import java.awt.Toolkit;

import cards.ActionCard;

import monopoly.GameManager;
import assets.Asset;
import squares.ActionCardSquare;
import squares.GoToJailSquare;
import squares.JailSlashFreePassSquare;
import squares.ParkingSquare;
import squares.Square;
import squares.StartSquare;
import ui.utils.Utils;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class SqurePanelFactory {
	
	
	public static SquarePanel  makeCorrectSqurePanel(Square represent)
	{
		
		if((represent instanceof Asset))
		{
			return new AssetSquarePanel((Asset) represent);
			
		}
		else if((represent instanceof StartSquare))
		{
			return new ImageOnlySquarePanel(represent, Utils.getImageIcon(GameManager.IMAGES_FOLDER+"SquareIcons/GO.gif‬"));
		}
		else if((represent instanceof GoToJailSquare))
		{
			return new ImageOnlySquarePanel(represent, Utils.getImageIcon(GameManager.IMAGES_FOLDER+"SquareIcons/GoToJail.gif‬‬"));
		}
		else if((represent instanceof ParkingSquare))
		{
			
			return new ImageOnlySquarePanel(represent, Utils.getImageIcon(GameManager.IMAGES_FOLDER+"SquareIcons/parking.gif‬‬"));
		}
		else if((represent instanceof ActionCardSquare))
		{
			if(((ActionCardSquare)represent).IsCallUp())
			{
				return new ImageOnlySquarePanel(represent,  Utils.getImageIcon(GameManager.IMAGES_FOLDER+"SquareIcons/CallUp.gif‬‬"));
				
			}
			else//supprise
				return new ImageOnlySquarePanel(represent, Utils.getImageIcon(GameManager.IMAGES_FOLDER+"SquareIcons/supprise.gif‬‬‬"));
		}
		else if((represent instanceof JailSlashFreePassSquare))
		{
			
			return new SquarePanel(represent);		}
		else
			return new SquarePanel(represent);
	}

}
