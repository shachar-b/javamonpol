/*
 * Created by JFormDesigner on Thu Mar 24 21:37:44 IST 2011
 */

package ui.guiComponents.dialogs;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import monopoly.buyOffer;

import assets.Offerable;

/**
 * @author Omer
 */
public class ComperableSelectionDialog extends JDialog {
	
	private buyOffer res;
	private ButtonGroup group ;
	private JButton submit= new JButton("submit");
	private Map<JToggleButton,Offerable> inner=new HashMap<JToggleButton, Offerable>();
	
	public ComperableSelectionDialog(Dialog owner,Collection<Offerable> assets, Collection<Offerable> Groups,buyOffer res,boolean IsSingelSelection) {
		super(owner);
		initComponents(assets, Groups,res,IsSingelSelection);
	}


	public ComperableSelectionDialog(Frame owner,Collection<Offerable> assets, Collection<Offerable> Groups,buyOffer res,boolean IsSingelSelection) {
		super(owner);
		initComponents(assets, Groups,res,IsSingelSelection);
		this.setVisible(true);
		this.pack();
	}
	

	protected static void initres() {
		
		
	}


	private void initComponents(Collection<Offerable> assets, Collection<Offerable> Groups,buyOffer res,boolean IsSingelSelection) {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		moneySelection = new JSlider();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		group= new ButtonGroup();
		JToggleButton curr;
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//Don't cancel
		JPanel groupsPanel = new JPanel(new GridLayout(0,2));
		JPanel AssetPanel = new JPanel(new GridLayout(0, 2));
		for(Offerable currentAsset: assets)
		{	
			curr=new JCheckBox(currentAsset.getName(),false);
			inner.put(curr,currentAsset);
			if(IsSingelSelection)
			{
				group.add(curr);
			}
			AssetPanel.add(curr);
		}
		for(Offerable currentAsset: Groups)
		{	
			
			
			if(IsSingelSelection)
			{
				curr=new JRadioButton(currentAsset.getName(),false);
				group.add(curr);
			}
			else
			{
				curr=new JCheckBox(currentAsset.getName(),false);
			}
			inner.put(curr,currentAsset);
			groupsPanel.add(curr);
		}
	
		contentPane.add(AssetPanel);
		contentPane.add(groupsPanel);
		contentPane.add(moneySelection);
		contentPane.add(submit);
	

		//---- moneySelection ----
		moneySelection.setPaintTicks(true);
		moneySelection.setSnapToTicks(true);
		moneySelection.setPaintLabels(true);
		moneySelection.setMinorTickSpacing(1);
		moneySelection.setMajorTickSpacing((res.getOfferMaker().getBalance()/10)+1);
		moneySelection.setMaximum(res.getOfferMaker().getBalance()-1);
		moneySelection.setValue(0);
		contentPane.add(moneySelection);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JSlider moneySelection;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
