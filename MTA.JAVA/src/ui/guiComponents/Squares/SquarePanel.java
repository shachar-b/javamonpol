/*
 * Created by JFormDesigner on Mon Mar 28 11:26:28 IST 2011
 */

package ui.guiComponents.Squares;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import squares.Square;

/**
 * @author Shachar
 */
public class SquarePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel playerPanel;
	private Square representedSquare;
	protected JLabel titleLabel;
	private HashMap<Icon, JLabel> iconList = new HashMap<Icon, JLabel>();
	
	public SquarePanel(Square representedSquare) {
		this.representedSquare=representedSquare;
		initComponents();
		initPlayersPanel();
	}

	private void initPlayersPanel()
	{
		playerPanel = new JPanel ();
        BoxLayout boxLayout = new BoxLayout(playerPanel, BoxLayout.X_AXIS);
        playerPanel.setLayout(boxLayout);
        playerPanel.setOpaque(true);
		this.add(playerPanel,BorderLayout.SOUTH);
	}
	
    public void addPlayer(Icon icon) {
        iconList.put(icon, new JLabel (icon));
        playerPanel.add(iconList.get(icon));
        this.validate();
        this.repaint();
    }
    
    public void removePlayer(Icon icon)
    {
    	if (iconList.containsKey(icon)){
    		playerPanel.remove(iconList.get(icon));
    		iconList.remove(icon);
    		this.validate();
    		this.repaint();
    	}
    	else throw new RuntimeException("Error: Player not found when trying to remove from general square.");
    }

	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		Name = new JLabel();

		//======== this ========
		setLayout(new BorderLayout());

		//---- Name ----
		Name.setText("text");
		Name.setHorizontalAlignment(SwingConstants.CENTER);
		Name.setBackground(new Color(51, 51, 255));
		add(Name, BorderLayout.NORTH);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		Name.setText(representedSquare.getName());
		titleLabel = Name;
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel Name;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
