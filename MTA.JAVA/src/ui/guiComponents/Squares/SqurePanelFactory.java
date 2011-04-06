/**
 * 
 */
package ui.guiComponents.Squares;

import monopoly.GameManager;
import squares.ActionCardSquare;
import squares.GoToJailSquare;
import squares.JailSlashFreePassSquare;
import squares.ParkingSquare;
import squares.Square;
import squares.StartSquare;
import assets.Asset;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class SqurePanelFactory {
	
	public static SquarePanel  makeCorrectSqurePanel(Square represent)
	{
		return makeCorrectSqurePanel(represent, true);
	}

	
	public static SquarePanel  makeCorrectSqurePanel(Square represent,boolean isHover)
	{
		String SquareIconsFolder = GameManager.IMAGES_FOLDER+"SquareIcons/";
		if((represent instanceof Asset))
		{
			return new AssetSquarePanel((Asset) represent,isHover);
			
		}
		else if((represent instanceof StartSquare))
		{
			return new ImageOnlySquarePanel(represent, SquareIconsFolder+"GO.gif");
		}
		else if((represent instanceof GoToJailSquare))
		{
			return new ImageOnlySquarePanel(represent, SquareIconsFolder+"GoToJail.gif");
		}
		else if((represent instanceof ParkingSquare))
		{
			return new ImageOnlySquarePanel(represent,SquareIconsFolder+"parking.png");
		}
		else if((represent instanceof ActionCardSquare))
		{
			if(((ActionCardSquare)represent).IsCallUp())
			{
				return new ImageOnlySquarePanel(represent,  SquareIconsFolder+"callUp.gif");
				
			}
			else//Surprise
				return new ImageOnlySquarePanel(represent, SquareIconsFolder+"surprise.gif");
		}
		else if((represent instanceof JailSlashFreePassSquare))
		{		
			return new ImageOnlySquarePanel(represent, SquareIconsFolder+"jail.gif");
		}
		else
			return new SquarePanel(represent);
	}

}
