/*
 * Created by JFormDesigner on Wed Mar 30 14:35:56 IST 2011
 */

package ui.guiComponents;

import java.awt.*;
import javax.swing.*;

/**
 * @author Shachar
 */
public class PlayerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PlayerPanel() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		buttonPane = new JPanel();
		rollDie = new JButton();
		EndTurn = new JButton();
		Bidding = new JButton();
		Forfeit = new JButton();
		nameLabel = new JLabel();
		SquareActionPane = new JPanel();
		useJailFreeCard = new JButton();
		buyAsset = new JButton();
		buyHouse = new JButton();
		panel1 = new JPanel();
		showGroup = new JButton();
		PlayerInformation = new JPanel();
		scrollPane1 = new JScrollPane();
		tree1 = new JTree();

		//======== this ========
		setLayout(new BorderLayout());

		//======== buttonPane ========
		{
			buttonPane.setLayout(new GridLayout());

			//---- rollDie ----
			rollDie.setText("Roll Die");
			buttonPane.add(rollDie);

			//---- EndTurn ----
			EndTurn.setText("End Turn");
			buttonPane.add(EndTurn);

			//---- Bidding ----
			Bidding.setText("Go On Bidding");
			Bidding.setEnabled(false);
			Bidding.setToolTipText("Go On Bidding");
			buttonPane.add(Bidding);

			//---- Forfeit ----
			Forfeit.setText("Forfeit Game");
			buttonPane.add(Forfeit);
		}
		add(buttonPane, BorderLayout.SOUTH);

		//---- nameLabel ----
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setText("his name");
		add(nameLabel, BorderLayout.NORTH);

		//======== SquareActionPane ========
		{
			SquareActionPane.setLayout(new BoxLayout(SquareActionPane, BoxLayout.Y_AXIS));

			//---- useJailFreeCard ----
			useJailFreeCard.setText("Get out of jail for FREE!");
			SquareActionPane.add(useJailFreeCard);

			//---- buyAsset ----
			buyAsset.setText("Buy Asset");
			SquareActionPane.add(buyAsset);

			//---- buyHouse ----
			buyHouse.setText("Buy House");
			SquareActionPane.add(buyHouse);
		}
		add(SquareActionPane, BorderLayout.EAST);

		//======== panel1 ========
		{
			panel1.setLayout(new FlowLayout());

			//---- showGroup ----
			showGroup.setText("Show Group");
			panel1.add(showGroup);
		}
		add(panel1, BorderLayout.CENTER);

		//======== PlayerInformation ========
		{
			PlayerInformation.setLayout(new FlowLayout());

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(tree1);
			}
			PlayerInformation.add(scrollPane1);
		}
		add(PlayerInformation, BorderLayout.WEST);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel buttonPane;
	private JButton rollDie;
	private JButton EndTurn;
	private JButton Bidding;
	private JButton Forfeit;
	private JLabel nameLabel;
	private JPanel SquareActionPane;
	private JButton useJailFreeCard;
	private JButton buyAsset;
	private JButton buyHouse;
	private JPanel panel1;
	private JButton showGroup;
	private JPanel PlayerInformation;
	private JScrollPane scrollPane1;
	private JTree tree1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
