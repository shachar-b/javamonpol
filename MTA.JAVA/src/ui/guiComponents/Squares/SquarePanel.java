/*
 * Created by JFormDesigner on Mon Mar 28 11:26:28 IST 2011
 */

package ui.guiComponents.Squares;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import monopoly.GameManager;

import squares.Square;
import ui.utils.ImagePanel;

/**
 * @author Shachar
 */
public class SquarePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel playerPanel;
	private Square representedSquare;
	protected JLabel titleLabel;
	protected JLabel groupLabel;
	private ArrayList<ImagePanel> iconList = new ArrayList<ImagePanel>();
	
	public SquarePanel(Square representedSquare) {
		super();
		this.representedSquare=representedSquare;
		initComponents();
		initPlayersPanel();
	}

	private void initPlayersPanel()
	{
		playerPanel = new JPanel (new FlowLayout());
		//playerPanel.add(new JLabel(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"/playerIcons/placeHolder.png")));
		this.add(playerPanel,BorderLayout.SOUTH);
	}
	
    public void addPlayer(ImagePanel iconPanel) {
        iconList.add(iconPanel);
        playerPanel.add(iconPanel);
        this.validate();
        this.repaint();
    }
    
    public void removePlayer(ImagePanel icon)
    {
    	if (iconList.contains(icon)){
    		playerPanel.remove(icon);
    		iconList.remove(icon);
    		this.validate();
    		this.repaint();
    	}
    	else throw new RuntimeException("Error: Player not found when trying to remove from general square.");
    }

	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPanel titleArea = new JPanel();
		titleArea.setLayout(new BoxLayout(titleArea, BoxLayout.Y_AXIS));
		titleLabel = new JLabel();

		//======== this ========
		setBorder(new EtchedBorder());
		setLayout(new BorderLayout());

		//---- Group ----
		groupLabel = new JLabel();
		groupLabel.setEnabled(false);
		groupLabel.setFont(GameManager.DefaultFont);
		titleArea.add(groupLabel);
		
		//---- Name ----
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBackground(new Color(51, 51, 255));
		titleArea.setFont(GameManager.DefaultFont);
		titleArea.add(titleLabel);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		titleLabel.setText(representedSquare.getName());
		
		add(titleArea,BorderLayout.NORTH);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
