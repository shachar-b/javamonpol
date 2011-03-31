/*
 * Created by JFormDesigner on Wed Mar 30 14:35:56 IST 2011
 */

package ui.guiComponents;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import players.Player;
import assets.Asset;
import assets.Offerable;

/**
 * @author Shachar
 */
public class PlayerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public PlayerPanel(Player player)
	{
		initComponents();
		initTreeModel(player);
	}

	private void initTreeModel(Player player)
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(player.getName());

		DefaultMutableTreeNode moneyNode = new DefaultMutableTreeNode("Money");
		moneyNode.add(new DefaultMutableTreeNode("Balance = "+player.getBalance()));

		DefaultMutableTreeNode assetsNode = new DefaultMutableTreeNode("Assets");
		ArrayList<Asset> assetsList = player.getAssetList();
		for (Asset asset : assetsList)
			assetsNode.add(new DefaultMutableTreeNode(asset.getName()));

		DefaultMutableTreeNode groupsNode = new DefaultMutableTreeNode("Groups");
		ArrayList<Offerable> groupsList = player.tradeableGroups();
		for (Offerable group : groupsList)
			groupsNode.add(new DefaultMutableTreeNode(group.getName()));

		DefaultTreeModel playerModel = new DefaultTreeModel(root);
		playerModel.insertNodeInto(moneyNode, root, playerModel.getChildCount(root));
		playerModel.insertNodeInto(assetsNode,root, playerModel.getChildCount(root));
		playerModel.insertNodeInto(groupsNode,root, playerModel.getChildCount(root));

		PlayerHoldings.setModel(playerModel);
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
		CurrentSquare = new JPanel();
		showGroup = new JButton();
		PlayerInformation = new JPanel();
		scrollPane1 = new JScrollPane();
		PlayerHoldings = new JTree();

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

		//======== CurrentSquare ========
		{
			CurrentSquare.setLayout(new BoxLayout(CurrentSquare, BoxLayout.Y_AXIS));

			//---- showGroup ----
			showGroup.setText("Show Group");
			CurrentSquare.add(showGroup);
		}
		add(CurrentSquare, BorderLayout.CENTER);

		//======== PlayerInformation ========
		{
			PlayerInformation.setLayout(new FlowLayout());

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(PlayerHoldings);
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
	private JPanel CurrentSquare;
	private JButton showGroup;
	private JPanel PlayerInformation;
	private JScrollPane scrollPane1;
	private JTree PlayerHoldings;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
