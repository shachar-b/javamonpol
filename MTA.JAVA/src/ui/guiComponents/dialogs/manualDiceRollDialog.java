/*
 * Created by JFormDesigner on Tue Apr 26 17:48:23 IDT 2011
 */

package ui.guiComponents.dialogs;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import ui.guiComponents.dice.Dice;

/**
 * @author Shachar
 */

public class manualDiceRollDialog extends JDialog {
	private Runnable preformWhenDone;
	private static final long serialVersionUID = 1L;
	public manualDiceRollDialog(Frame owner, Runnable preformWhenDone) {
		super(owner,true);
		this.preformWhenDone = preformWhenDone;
		initComponents();
	}

	private void okButtonActionPerformed(ActionEvent e) {
		Dice.getGameDice().setDieOutcome((Integer)dice1.getValue(), (Integer)dice2.getValue());
		preformWhenDone.run();
		this.dispose();
	}

	private void CancelButtonActionPerformed(ActionEvent e) {
		this.dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		titleLabel = new JLabel();
		spinnersPanel = new JPanel();
		dice1 = new JSpinner();
		dice2 = new JSpinner();
		buttonBar = new JPanel();
		okButton = new JButton();
		CancelButton = new JButton();

		//======== this ========
		setTitle("Manual dice selector");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new BorderLayout(10, 10));

				//---- titleLabel ----
				titleLabel.setText("Set the die outcome:");
				contentPanel.add(titleLabel, BorderLayout.NORTH);

				//======== spinnersPanel ========
				{
					spinnersPanel.setLayout(new BoxLayout(spinnersPanel, BoxLayout.X_AXIS));

					//---- dice1 ----
					dice1.setModel(new SpinnerNumberModel(1, 0, 6, 1));
					spinnersPanel.add(dice1);

					//---- dice2 ----
					dice2.setModel(new SpinnerNumberModel(1, 1, 6, 1));
					spinnersPanel.add(dice2);
				}
				contentPanel.add(spinnersPanel, BorderLayout.CENTER);
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed(e);
					}
				});
				buttonBar.add(okButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- CancelButton ----
				CancelButton.setText("Cancel dice roll");
				CancelButton.setToolTipText("Choose this to pause game and inspect the board.");
				CancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CancelButtonActionPerformed(e);
					}
				});
				buttonBar.add(CancelButton, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
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
	private JLabel titleLabel;
	private JPanel spinnersPanel;
	private JSpinner dice1;
	private JSpinner dice2;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton CancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
