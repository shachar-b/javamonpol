/*
 * Created by JFormDesigner on Fri Apr 08 15:03:58 IDT 2011
 */

package ui.guiComponents;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import monopoly.GameManager;
import ui.utils.TransparentTable;
import ui.utils.Utils;

/**
 * @author Shachar
 */
public class CenterPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public CenterPanel() {
		initComponents();
		initGameLogo();
		initCardPanels();
		initLegend();
	}
	
	private void initLegend()
	{
		TransparentTable legendTable = new TransparentTable();
		LegendPanel.add(new JScrollPane(legendTable)); //JScrollPane is to show column headers
		DefaultTableModel model =(DefaultTableModel)legendTable.getModel();
		model.addColumn("Icon");
		model.addColumn("Name");
		model.addColumn("Balance"); 
		
		LegendPanel.setBorder(new EtchedBorder());
	}
	
	private void updateLegend()
	{
		
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
}
