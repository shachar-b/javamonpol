/*
 * Created by JFormDesigner on Wed Mar 23 13:30:51 IST 2011
 */

package ui.guiComponents.dialogs;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 * @author Shachar
 */
public class EntryDialog extends JDialog {
	public EntryDialog(Frame owner) {
		super(owner);
		initComponents();
	}

	public EntryDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void totalSliderStateChanged(ChangeEvent e) {
		computersSlider.setMaximum(totalSlider.getValue());
		computersSlider.validate();
		computersSlider.repaint();
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
				totalSlider.setMaximum(6);
				totalSlider.setMinimum(2);
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
				computersSlider.setMaximum(6);
				computersSlider.setValue(6);
				computersSlider.setToolTipText("Total number of computer players is between 0 to all players.");
				computersSlider.setSnapToTicks(true);
				computersSlider.setPaintLabels(true);
				computersSlider.setPaintTicks(true);
				computersSlider.setMinorTickSpacing(1);
				computersSlider.setMajorTickSpacing(1);
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
				buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- cancelButton ----
				cancelButton.setText("Cancel New Game");
				buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- helpButton ----
				helpButton.setText("Game Instructions");
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
