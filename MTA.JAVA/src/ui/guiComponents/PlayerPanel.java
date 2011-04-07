/*
 * Created by JFormDesigner on Wed Mar 30 14:35:56 IST 2011
 */

package ui.guiComponents;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import listeners.gameActions.GameActionEvent;
import listeners.gameActions.GameActionEventListener;
import listeners.gameActions.GameActionsListenableClass;
import monopoly.GameManager;
import players.ComputerPlayer;
import players.Player;
import squares.Square;
import ui.guiComponents.Squares.SqurePanelFactory;
import ui.guiComponents.dialogs.AssetGroupDialog;
import ui.guiComponents.dice.Dice;
import assets.Asset;
import assets.Offerable;

/**
 * @author Shachar
 */
public class PlayerPanel extends GameActionsListenableClass {
	private static final long serialVersionUID = 1L;
	private Player representedPlayer;
	private GameActionEventListener dieListner= new GameActionEventListener() {
		
		@Override
		public void eventHappened(GameActionEvent gameActionEvent) {
			dieRolled();
			
		}
	};
	private void dieRolled()
	{
		EndTurn.setEnabled(true);
		useJailFreeCard.setEnabled(false);
		fireEvent("throwDie");
	}
	public PlayerPanel(Player player)
	{
		representedPlayer=player;
		initComponents();
		Dice.getGameDice().addGameActionsListener(dieListner);
		nameLabel.setText(player.getName());
		Square currentSquare =GameManager.currentGame.getGameBoard().get(player.getCurrentPosition());
		setSquarePanelContent(currentSquare);
		initTreeModel();
	}

	public void setGetOutOfJailButtonStatus(boolean value)
	{//value==true -> enable button, otherwise -> disable button.
		useJailFreeCard.setEnabled(value);
	}
	public void ClickGetOutOfJailButton()
	{
		useJailFreeCard.doClick();
	}
	
	public void setBuyAssetButtonStatus(boolean value)
	{//value==true -> enable button, otherwise -> disable button.
		buyAsset.setEnabled(value);
	}
	public void ClickBuyAssetButton()
	{
		buyAsset.doClick();
	}
	
	public void setShowGroupButtonStatus(boolean value)
	{//value==true -> enable button, otherwise -> disable button.
		showGroup.setEnabled(value);
	}
	
	public void setBuyHouseButtonStatus(boolean value)
	{//value==true -> enable button, otherwise -> disable button.
		buyHouse.setEnabled(value);
	}
	public void ClickBuyHouseButton()
	{
		buyAsset.doClick();
	}
	
	public void setEndTurnButtonStatus(boolean value)
	{//value==true -> enable button, otherwise -> disable button.
		EndTurn.setEnabled(value);
	}
	public void ClickEndTurnButton()
	{
		EndTurn.doClick();
	}
	
	public void setBiddingButtonStatus(boolean value)
	{//value==true -> enable button, otherwise -> disable button.
		Bidding.setEnabled(value);
	}
	
	
	public void setSquarePanelContent(Square currentSquare)
	{
		if (CurrentSquare.getComponentCount()>3)
			CurrentSquare.remove(0);
		CurrentSquare.add(SqurePanelFactory.makeCorrectSqurePanel(currentSquare,false),0);
		if(currentSquare instanceof Asset)
		{
			showGroup.setEnabled(true);
		}
		initTreeModel();
	}
	
	private void initTreeModel()
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(representedPlayer.getName());

		DefaultMutableTreeNode moneyNode = new DefaultMutableTreeNode("Money");
		moneyNode.add(new DefaultMutableTreeNode("Balance = "+representedPlayer.getBalance()));

		DefaultMutableTreeNode assetsNode = new DefaultMutableTreeNode("Assets");
		ArrayList<Asset> assetsList = representedPlayer.getAssetList();
		for (Asset asset : assetsList)
			assetsNode.add(new DefaultMutableTreeNode(asset.getName()));

		DefaultMutableTreeNode groupsNode = new DefaultMutableTreeNode("Groups");
		ArrayList<Offerable> groupsList = representedPlayer.tradeableGroups();
		for (Offerable group : groupsList)
			groupsNode.add(new DefaultMutableTreeNode(group.getName()));

		DefaultTreeModel playerModel = new DefaultTreeModel(root);
		playerModel.insertNodeInto(moneyNode, root, playerModel.getChildCount(root));
		playerModel.insertNodeInto(assetsNode,root, playerModel.getChildCount(root));
		playerModel.insertNodeInto(groupsNode,root, playerModel.getChildCount(root));

		PlayerHoldings.setModel(playerModel);
	}

	private void ForfeitActionPerformed(ActionEvent e) {
		Dice.getGameDice().resetThrowButton();
		fireEvent(new GameActionEvent(this, "forfeit"));
		this.setVisible(false);
		revalidate();
		repaint();
	}

	private void useJailFreeCardActionPerformed(ActionEvent e) {
		fireEvent(new GameActionEvent(this, "getOutOfJail"));
		useJailFreeCard.setEnabled(false);
		EndTurn.setEnabled(true);
		//TODO:disable DieRoll button
	}

	private void EndTurnActionPerformed(ActionEvent e) {
		Dice.getGameDice().resetThrowButton();
		Dice.getGameDice().removeListener(dieListner);
		fireEvent("endTurn");
		this.setVisible(false);
		revalidate();
		repaint();
	}

	private void BiddingActionPerformed(ActionEvent e) {
		//TODO:add my cool dialog here
	}

	private void showGroupActionPerformed(ActionEvent e) 
	{
		
		AssetGroupDialog groups=
			new AssetGroupDialog((Frame)GameManager.CurrentUI.getFrame() , ((Asset)GameManager.currentGame.getGameBoard().get(representedPlayer.getCurrentPosition())).getGroup());
		groups.setVisible(true);
	}

	private void buyAssetActionPerformed(ActionEvent e) {
		buyAsset.setEnabled(false);
		fireEvent("buyAsset");
		initTreeModel();
	}

	private void buyHouseActionPerformed(ActionEvent e) {
		buyHouse.setEnabled(false);
		fireEvent("buyHouse");
		initTreeModel();
	}
	

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		buttonPane = new JPanel();
		EndTurn = new JButton();
		Bidding = new JButton();
		Forfeit = new JButton();
		nameLabel = new JLabel();
		DicePane = new JPanel();
		useJailFreeCard = new JButton();
		CurrentSquare = new JPanel();
		showGroup = new JButton();
		buyAsset = new JButton();
		buyHouse = new JButton();
		PlayerInformation = new JPanel();
		scrollPane1 = new JScrollPane();
		PlayerHoldings = new JTree();

		//======== this ========
		setLayout(new BorderLayout());

		//======== buttonPane ========
		{
			buttonPane.setLayout(new GridLayout());

			//---- EndTurn ----
			EndTurn.setText("End Turn");
			EndTurn.setEnabled(false);
			EndTurn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EndTurnActionPerformed(e);
				}
			});
			buttonPane.add(EndTurn);

			//---- Bidding ----
			Bidding.setText("Go On Bidding");
			Bidding.setEnabled(false);
			Bidding.setToolTipText("Go On Bidding");
			Bidding.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BiddingActionPerformed(e);
				}
			});
			buttonPane.add(Bidding);

			//---- Forfeit ----
			Forfeit.setText("Forfeit Game");
			Forfeit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ForfeitActionPerformed(e);
				}
			});
			buttonPane.add(Forfeit);
		}
		add(buttonPane, BorderLayout.SOUTH);

		//---- nameLabel ----
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setText("his name");
		add(nameLabel, BorderLayout.NORTH);

		//======== DicePane ========
		{
			DicePane.setBorder(new EtchedBorder());
			DicePane.setLayout(new BorderLayout());

			//---- useJailFreeCard ----
			useJailFreeCard.setText("Get out of jail free");
			useJailFreeCard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					useJailFreeCardActionPerformed(e);
				}
			});
			DicePane.add(useJailFreeCard, BorderLayout.SOUTH);
		}
		add(DicePane, BorderLayout.EAST);

		//======== CurrentSquare ========
		{
			CurrentSquare.setBorder(new EtchedBorder());
			CurrentSquare.setLayout(new BoxLayout(CurrentSquare, BoxLayout.Y_AXIS));

			//---- showGroup ----
			showGroup.setText("Show Group");
			showGroup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showGroupActionPerformed(e);
				}
			});
			CurrentSquare.add(showGroup);

			//---- buyAsset ----
			buyAsset.setText("Buy Asset");
			buyAsset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buyAssetActionPerformed(e);
				}
			});
			CurrentSquare.add(buyAsset);

			//---- buyHouse ----
			buyHouse.setText("Buy House");
			buyHouse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buyHouseActionPerformed(e);
				}
			});
			CurrentSquare.add(buyHouse);
		}
		add(CurrentSquare, BorderLayout.CENTER);

		//======== PlayerInformation ========
		{
			PlayerInformation.setBorder(new EtchedBorder());
			PlayerInformation.setLayout(new FlowLayout());

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(PlayerHoldings);
			}
			PlayerInformation.add(scrollPane1);
		}
		add(PlayerInformation, BorderLayout.WEST);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		DicePane.add(Dice.getGameDice());
		showGroup.setEnabled(false);
		buyAsset.setEnabled(false);
		buyHouse.setEnabled(false);
		useJailFreeCard.setEnabled(false);

	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel buttonPane;
	private JButton EndTurn;
	private JButton Bidding;
	private JButton Forfeit;
	private JLabel nameLabel;
	private JPanel DicePane;
	private JButton useJailFreeCard;
	private JPanel CurrentSquare;
	private JButton showGroup;
	private JButton buyAsset;
	private JButton buyHouse;
	private JPanel PlayerInformation;
	private JScrollPane scrollPane1;
	private JTree PlayerHoldings;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
