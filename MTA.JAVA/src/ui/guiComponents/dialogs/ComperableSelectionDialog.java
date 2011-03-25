/*
 * Created by JFormDesigner on Thu Mar 24 21:37:44 IST 2011
 */

package ui.guiComponents.dialogs;



import java.awt.*;
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		Container contentPane = getContentPane();

		//======== tabbedPane1 ========
		{

			//======== panel1 ========
			{
				panel1.setLayout(new BorderLayout());
			}
			tabbedPane1.addTab("add money", panel1);


			//======== panel2 ========
			{
				panel2.setLayout(new BorderLayout());
			}
			tabbedPane1.addTab("add single Assets", panel2);


			//======== panel3 ========
			{
				panel3.setLayout(new BorderLayout());
			}
			tabbedPane1.addTab("add assetGroups", panel3);

		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addComponent(tabbedPane1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
