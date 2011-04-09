/*
 * Created by JFormDesigner on Sat Apr 09 21:27:23 IDT 2011
 */

package ui.guiComponents.dialogs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import monopoly.GameManager;
import monopoly.Monopoly;

/**
 * @author Shachar
 */
public class midGameEntryDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	public midGameEntryDialog(Frame owner) {
		super(owner);
		initComponents();
	}

	public midGameEntryDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void cancelButtonActionPerformed(ActionEvent e) {
		this.dispose();
	}

	private void okButtonActionPerformed(ActionEvent e) {
		GameManager.CurrentUI.getFrame().dispose();
		GameManager.currentGame = new Monopoly();
		GameManager.currentGame.init();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		TextLabel = new JLabel();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("End game");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

				//---- TextLabel ----
				TextLabel.setText("Are you sure you want to end the current game?");
				TextLabel.setVerticalAlignment(SwingConstants.TOP);
				contentPanel.add(TextLabel);
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

				//---- okButton ----
				okButton.setText("OK - End game");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed(e);
					}
				});
				buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- cancelButton ----
				cancelButton.setText("Cancel - Stay in game");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelButtonActionPerformed(e);
					}
				});
				buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
	private JLabel TextLabel;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
