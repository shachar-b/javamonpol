/*
 * Created by JFormDesigner on Fri Mar 25 15:13:57 IST 2011
 */

package ui.guiComponents.dialogs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Omer
 */
public class GameInstructionsDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GameInstructionsDialog(Frame owner) {
		super(owner);
		initComponents();
		this.setMinimumSize(new Dimension(((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2),((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2) ));
	}

	public GameInstructionsDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void okButtonMouseClicked(MouseEvent e) {
		this.dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		InstrctionsArea = new JTextArea();
		buttonBar = new JPanel();
		okButton = new JButton();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("x");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new BorderLayout());

				//======== scrollPane1 ========
				{

					//---- InstrctionsArea ----
					InstrctionsArea.setBackground(UIManager.getColor("CheckBoxMenuItem.background"));
					InstrctionsArea.setEditable(false);
					InstrctionsArea.setLineWrap(true);
					InstrctionsArea.setText("put instructions here");
					InstrctionsArea.setToolTipText("Game Instrctions");
					scrollPane1.setViewportView(InstrctionsArea);
				}
				contentPanel.add(scrollPane1, BorderLayout.CENTER);
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

				//---- okButton ----
				okButton.setText("GOT IT");
				okButton.setToolTipText("the game dosent require explention");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						okButtonMouseClicked(e);
					}
				});
				buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		setSize(500, 350);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JScrollPane scrollPane1;
	private JTextArea InstrctionsArea;
	private JPanel buttonBar;
	private JButton okButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
