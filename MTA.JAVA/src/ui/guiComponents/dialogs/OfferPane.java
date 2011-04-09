/*
 * Created by JFormDesigner on Tue Apr 05 17:14:55 IDT 2011
 */

package ui.guiComponents.dialogs;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTree;

import monopoly.buyOffer;

/**
 * @author Omer Shenhar and Shachar Butnaro
 */
public class OfferPane extends JPanel {
	private static final long serialVersionUID = 1L;

	public OfferPane(buyOffer offer) {
		initComponents(offer);
	}

	private void initComponents(buyOffer offer) {
		//======== this ========
		setLayout(new FlowLayout());
		JTree tree;
		
	}

}
