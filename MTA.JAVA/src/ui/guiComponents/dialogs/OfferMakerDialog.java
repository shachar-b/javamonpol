/*
 * Created by JFormDesigner on Thu Mar 24 21:37:44 IST 2011
 */

package ui.guiComponents.dialogs;



import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import monopoly.buyOffer;
import ui.guiComponents.dialogs.OfferMakerPanes.MoneyOfferPane;
import ui.guiComponents.dialogs.OfferMakerPanes.OfferMakerButtonPane;
import ui.guiComponents.dialogs.OfferMakerPanes.OfferableSelectionPane;
import assets.Offerable;

/**
 * @author Omer
 */
public class OfferMakerDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private MoneyOfferPane moneyPane;
	private OfferMakerButtonPane buttonPane=OfferMakerButtonPane.getOfferMakerButtonPane();
	private OfferableSelectionPane GroupPane;
	private OfferableSelectionPane SinglesPane;
	private buyOffer res;
	
	
	public OfferMakerDialog(Dialog owner,Collection<Offerable> assets, Collection<Offerable> Groups,buyOffer res,boolean IsSingelSelection) {
		super(owner);
		this.res=res;
		initComponents(assets, Groups,res,IsSingelSelection);
		this.setVisible(true);
		this.pack();
	}


	public OfferMakerDialog(Frame owner,Collection<Offerable> assets, Collection<Offerable> Groups,buyOffer res,boolean IsSingelSelection) {
		super(owner);
		this.res=res;
		initComponents(assets, Groups,res,IsSingelSelection);
		this.setVisible(true);
		this.pack();
	}
	


	private void initComponents(Collection<Offerable> assets, Collection<Offerable> Groups,buyOffer res,boolean IsSingelSelection) {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		OfferPane = new JTabbedPane();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(OfferPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		moneyPane=new MoneyOfferPane(res);
		if(IsSingelSelection)
		{
			ButtonGroup group =new ButtonGroup();
			GroupPane=new OfferableSelectionPane(res, Groups,group);
			SinglesPane=new OfferableSelectionPane(res, assets,group);
			
		}
		else
		{
			GroupPane=new OfferableSelectionPane(res, Groups);
			SinglesPane=new OfferableSelectionPane(res, assets);	
			
		}
		if(!IsSingelSelection)//single selection is only for the seller and he can't sell money
		{
			OfferPane.addTab("add money",moneyPane);
		}
		if(!Groups.isEmpty())
		{
			OfferPane.addTab("add Assats Groups",GroupPane);
		}
		OfferPane.addTab("add single Assets",SinglesPane);
		this.add(buttonPane,BorderLayout.SOUTH);
		buttonPane.getOK().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okButtonActionPerformed(e);
			}

			
		});
		buttonPane.getCancel().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelButtonActionPerformed(e);
			}
		});
		
		
		this.setTitle("hey "+res.getOfferMaker().getName()+" ,what do you want to offer for sale?");
		
		
	}
	private void okButtonActionPerformed(ActionEvent e) {
		moneyPane.updateOfferAccordingToSelection();
		GroupPane.updateOfferAccordingToSelection();
		SinglesPane.updateOfferAccordingToSelection();
		this.dispose();
	}
	private void CancelButtonActionPerformed(ActionEvent e) {
		res=new buyOffer(res.getOfferMaker());//make an empty res
		this.dispose();
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTabbedPane OfferPane;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
