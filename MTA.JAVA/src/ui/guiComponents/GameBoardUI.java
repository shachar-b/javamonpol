package ui.guiComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import monopoly.GameManager;
import players.Player;
import squares.Square;
import ui.guiComponents.Squares.SquarePanel;
import ui.guiComponents.Squares.SqurePanelFactory;
import ui.utils.RotateableJPanel.Diractions;

public class GameBoardUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int LINE_SIZE = 9;
	private List<SquarePanel> components;
	private HashMap<Player,ImageIcon> playersIcons =new HashMap<Player,ImageIcon>();
	
	
    public GameBoardUI() {
		super();
        initUI();
	}
    
    public void movePlayer(Player player,int from,int to)
    {
    	components.get(from).removePlayer(playersIcons.get(player));
    	components.get(to).addPlayer(playersIcons.get(player));
    }
    public void addPlayerIcon(Player p, ImageIcon icon)
    {
    	playersIcons.put(p, icon);
    }
    public void removePlayerIcon(Player p)
    {
    	playersIcons.remove(p);
    }
    
    

    private void initUI() {
        //init layout
        this.setLayout(new GridBagLayout());

        components = new LinkedList<SquarePanel>();
        ArrayList<Square> bord=GameManager.currentGame.getGameBoard();
        for (int i=0 ; i < LINE_SIZE * 4 ; i++) {
        	//int diraction=i/LINE_SIZE;
        	Diractions d = Diractions.NORTH;
        /*	switch (diraction) {
			case 0://i<linesize
				d=Diractions.SOUTH;
				break;

			case 1://linesize<i<2linesize
				d=Diractions.WEST;
				break;
				
			case 2:
				d=Diractions.NORTH;
				break;

			case 3:
				d=Diractions.EAST;
				break;
					
			}*/
            components.add(SqurePanelFactory.makeCorrectSqurePanel(bord.get(i),d));
        }

        Iterator<SquarePanel> componentIterator = components.iterator();

        //Add Panels for Each of the four sides
        for (int sideIndex = 0; sideIndex < 4; sideIndex++) {
            for (int lineIndex = 0; lineIndex < LINE_SIZE; lineIndex++) {
            	SquarePanel component = componentIterator.next();
                switch(sideIndex)
                {
                    case 0:
                        //top line
                        addComponent (lineIndex, 0, component);
                        break;
                    case 1:
                        //right line
                        addComponent (LINE_SIZE, lineIndex, component);
                        break;
                    case 2:
                        //bottom line - and in reverse order
                        addComponent (LINE_SIZE - lineIndex, LINE_SIZE, component);
                        break;
                    case 3:
                        //left line - and in reverse order
                        addComponent (0, LINE_SIZE - lineIndex, component);
                        break;
                }
            }
        }

        // Main Inner Area Notice Starts at (1,1) and takes up 11x11
        JPanel innerPanel = createInnerPanel("CENTER");
        this.add(innerPanel,
            new GridBagConstraints(1,
                    1,
                    LINE_SIZE +1,
                    LINE_SIZE +1,
                    0.1, 0.1,
                    GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
	}

    private JPanel createInnerPanel(String text) {
        JPanel panel = new JPanel(new BorderLayout()) ;
        panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private void addComponent(int gridX, int gridY, JComponent component) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridX;
        c.gridy = gridY;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        this.add(component, c);
    }
}