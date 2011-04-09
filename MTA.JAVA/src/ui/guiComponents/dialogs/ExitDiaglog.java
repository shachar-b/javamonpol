/*
 * Created by JFormDesigner on Wed Mar 23 13:53:09 IST 2011
 */

package ui.guiComponents.dialogs;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
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
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author Shachar
 */
public class ExitDiaglog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExitDiaglog(Frame owner) {
		super(owner);
		initComponents();
}

	public ExitDiaglog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void ReturnToGameActionPerformed(ActionEvent e) {
		this.dispose();
	}

	private void QuitGameActionPerformed(ActionEvent e) {
		System.exit(0);
		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		label1 = new JLabel();
		buttonBar = new JPanel();
		ReturnToGame = new JButton();
		QuitGame = new JButton();

		//======== this ========
		setTitle("Exit game?");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

				//---- label1 ----
				label1.setText("Are you sure you want to quit the game?");
				label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				contentPanel.add(label1);
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 0, 85, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

				//---- ReturnToGame ----
				ReturnToGame.setText("Return to game");
				ReturnToGame.setToolTipText("Because nobodoy likes a quitter...");
				ReturnToGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ReturnToGameActionPerformed(e);
					}
				});
				buttonBar.add(ReturnToGame, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- QuitGame ----
				QuitGame.setText("Quit");
				QuitGame.setToolTipText("Are you sure you want to be a quitter?");
				QuitGame.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
				QuitGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						QuitGameActionPerformed(e);
					}
				});
				buttonBar.add(QuitGame, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
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
	private JLabel label1;
	private JPanel buttonBar;
	private JButton ReturnToGame;
	private JButton QuitGame;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
