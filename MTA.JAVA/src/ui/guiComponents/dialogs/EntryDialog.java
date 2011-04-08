/*
 * Created by JFormDesigner on Wed Mar 23 13:30:51 IST 2011
 */

package ui.guiComponents.dialogs;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import monopoly.GameManager;
import players.ComputerPlayer;
import players.HumanPlayer;
import players.Player;
import ui.UI;
import ui.guiComponents.CenterPanel;
import ui.guiComponents.MainWindow;

/**
 * @author Shachar
 */
public class EntryDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<JTextField> names=new ArrayList<JTextField>();
	public ArrayList<JLabel> namesLabels=new ArrayList<JLabel>();
	private static boolean firstGame=true;
	
	public EntryDialog(Frame owner) {
		super(owner);
		initComponents();
	}

	public EntryDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void changeTable()
	{
		int numOfHumens=(totalSlider.getValue()-computersSlider.getValue());
		
		for(int i=0;i<names.size(); i++)
		{
				namesLabels.get(i).setVisible(i<numOfHumens);
				names.get(i).setVisible(i<numOfHumens);	
		}
	}
	private void totalSliderStateChanged(ChangeEvent e) {
		computersSlider.setMaximum(totalSlider.getValue());
		computersSlider.validate();
		computersSlider.repaint();
	}

	private void computersSliderStateChanged(ChangeEvent e) {
		changeTable();
		this.validate();
		this.repaint();
		this.pack();
	}

	private void GameInstructionsButtonMouseClicked(MouseEvent e) {
		GameInstructionsDialog diag=new GameInstructionsDialog(this);
		diag.setVisible(true);
	}

	private void cancelButtonActionPerformed(ActionEvent e) {
		this.dispose();
	}

	private void okButtonActionPerformed(ActionEvent e) {
		MainWindow MW = (MainWindow) getParent();
		int computerPlayers = computersSlider.getValue();
		int totalPlayers = totalSlider.getValue();
		ArrayList<Player> gamePlayers = new ArrayList<Player>();
		
		for (int i=0; i<computerPlayers; i++)
		{
			gamePlayers.add(new ComputerPlayer());
		}
		for (int i=0; i<(totalPlayers-computerPlayers); i++)
		{
			gamePlayers.add(new HumanPlayer(names.get(i).getText()
					,GameManager.IMAGES_FOLDER+"/playerIcons/"+(i+computerPlayers+1)+".png"));
		}
		Collections.shuffle(gamePlayers);
		if(!firstGame)
			((UI)(GameManager.CurrentUI)).cleanupSequence();
		else
			firstGame=false;
		GameManager.currentGame.setGamePlayers(gamePlayers);
		for (Player player : gamePlayers)
		{
			MW.getGameboard().addPlayerIcon(player,player.getIconPanel());
		}
		this.dispose();
		GameManager.CurrentUI.getFrame().getGameboard().updatePlayersLegend();
		GameManager.currentGame.play();
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		NumOfPlayers = new JLabel();
		totalSlider = new JSlider();
		label1 = new JLabel();
		computersSlider = new JSlider();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		helpButton = new JButton();

		//======== this ========
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new GridBagLayout());
				((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0};
				((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
				((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
				((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

				//---- NumOfPlayers ----
				NumOfPlayers.setText("Select total number of players");
				contentPanel.add(NumOfPlayers, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- totalSlider ----
				totalSlider.setMajorTickSpacing(1);
				totalSlider.setMinorTickSpacing(1);
				totalSlider.setPaintLabels(true);
				totalSlider.setSnapToTicks(true);
				totalSlider.setPaintTicks(true);
				totalSlider.setToolTipText("Total number of players must be between 2 to 6");
				totalSlider.setValue(6);
				totalSlider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						totalSliderStateChanged(e);
					}
				});
				contentPanel.add(totalSlider, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- label1 ----
				label1.setText("Select number of computer players");
				contentPanel.add(label1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- computersSlider ----
				computersSlider.setValue(6);
				computersSlider.setToolTipText("Total number of computer players is between 0 to all players.");
				computersSlider.setSnapToTicks(true);
				computersSlider.setPaintLabels(true);
				computersSlider.setPaintTicks(true);
				computersSlider.setMinorTickSpacing(1);
				computersSlider.setMajorTickSpacing(1);
				computersSlider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						computersSliderStateChanged(e);
					}
				});
				contentPanel.add(computersSlider, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 85, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

				//---- okButton ----
				okButton.setText("Start Game");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed(e);
					}
				});
				buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- cancelButton ----
				cancelButton.setText("Cancel New Game");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelButtonActionPerformed(e);
					}
				});
				buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- helpButton ----
				helpButton.setText("Game Instructions");
				helpButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						GameInstructionsButtonMouseClicked(e);
					}
				});
				buttonBar.add(helpButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		//added code
		
		totalSlider.setMaximum(GameManager.MAX_NUMBER_OF_PLAYERS);
		totalSlider.setMinimum(GameManager.MIN_NUMBER_OF_PLAYERS);
		totalSlider.setValue(GameManager.MAX_NUMBER_OF_PLAYERS);
		computersSlider.setMaximum(GameManager.MAX_NUMBER_OF_PLAYERS);
		computersSlider.setMinimum(0);
		computersSlider.setValue(GameManager.MAX_NUMBER_OF_PLAYERS);
		
		
		for(int i=0; i<GameManager.MAX_NUMBER_OF_PLAYERS; i++)
		{
			JTextField curr=new JTextField("HumaPlayer"+(i+1));
			JLabel currLabel= new JLabel("name for human player"+(i+1)+":");
			names.add(curr);
			namesLabels.add(currLabel);
			contentPanel.add(currLabel, new GridBagConstraints(0, 2+i, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			contentPanel.add(curr, new GridBagConstraints(1, 2+i, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			curr.setVisible(false);
			currLabel.setVisible(false);
			
		}
		this.pack();
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel NumOfPlayers;
	private JSlider totalSlider;
	private JLabel label1;
	private JSlider computersSlider;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	private JButton helpButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
