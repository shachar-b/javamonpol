/*
 * Created by JFormDesigner on Fri Apr 08 15:03:58 IDT 2011
 */

package ui.guiComponents;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import listeners.innerChangeEventListener.InnerChangeEventListner;
import listeners.innerChangeEventListener.InnerChangeEvet;
import monopoly.GameManager;
import players.Player;
import ui.utils.IconCellRenderer;
import ui.utils.TransparentTable;
import ui.utils.Utils;

/**
 * @author Omer Shenhar and Shachar Butnaro
 */
public class CenterPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public CenterPanel() {
		initComponents();
		initGameLogo();
		initCardPanels();
		initLegendArea();
		initLegend();
	}

	private void initLegendArea()
	{
		legendTable = new TransparentTable();
		LegendPanel.add(new JScrollPane(legendTable)); //JScrollPane is to show column headers
		legendTable.setRowHeight(66); //Set row height to enable showing images appropriately
	}

	private void initLegend()
	{
		DefaultTableModel model =(DefaultTableModel)legendTable.getModel();
		model.addColumn("Icon");
		model.addColumn("Name");
		model.addColumn("Balance");
		TableColumn col = legendTable.getColumnModel().getColumn(0);
		col.setCellRenderer(new IconCellRenderer());
	}

	public void updateLegend()
	{
		legendTable.resetModel();
		initLegend(); //To reset the model
		ArrayList<Player> players = GameManager.currentGame.getGamePlayers();
		DefaultTableModel model =(DefaultTableModel)legendTable.getModel();

		for (Player player : players)//Add players' information
		{
			player.removeListener(playerListner);//avoid duplicates
			player.addInnerChangeEventListner(playerListner);
			Object icon = player.getIconPanel();
			Object name = player.getName();
			Object balance = player.getBalance();
			Object[] rowData = {icon,name,balance};
			model.addRow(rowData);
		}
	}

	private void initGameLogo()
	{
		JLabel logoLabel = new JLabel(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"MiscIcons/logo.gif"));
		this.add(logoLabel, BorderLayout.NORTH);
	}

	private void initCardPanels()
	{
		JLabel callUpLabel = new JLabel(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"MiscIcons/CallUp.gif"));
		JLabel surpriseLabel = new JLabel(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"MiscIcons/surprise.gif"));
		CallUpCardsPanel.add(callUpLabel, BorderLayout.CENTER);
		CallUpCardsPanel.add(surpriseLabel, BorderLayout.CENTER);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		CallUpCardsPanel = new JPanel();
		LegendPanel = new JPanel();

		//======== this ========
		setLayout(new BorderLayout());

		//======== CallUpCardsPanel ========
		{
			CallUpCardsPanel.setLayout(new BoxLayout(CallUpCardsPanel, BoxLayout.Y_AXIS));
		}
		add(CallUpCardsPanel, BorderLayout.WEST);

		//======== LegendPanel ========
		{
			LegendPanel.setLayout(new FlowLayout());
		}
		add(LegendPanel, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel CallUpCardsPanel;
	private JPanel LegendPanel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	private TransparentTable legendTable;
	InnerChangeEventListner playerListner =new InnerChangeEventListner() {
		
		@Override
		public void eventHappened(InnerChangeEvet innerChangeEvet) {
			updateLegend();
			
		}
	};
}
