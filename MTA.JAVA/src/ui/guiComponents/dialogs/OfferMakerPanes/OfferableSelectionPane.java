/**
 * 
 */
package ui.guiComponents.dialogs.OfferMakerPanes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import monopoly.buyOffer;
import assets.Offerable;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class OfferableSelectionPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel checkPanel = new JPanel(new GridLayout(0, 1));	
	private buyOffer res;
	
	
	//where JTooggleButton is the button and the Offerable is the offerable item it reprent
	HashMap<JToggleButton, Offerable> offerbles;
	ButtonGroup group;
	
	
	public void updateOfferAccordingToSelection()
	{
		for(JToggleButton curr:offerbles.keySet())
		{
			if(curr.isSelected())
			{
				res.addToOffer(offerbles.get(curr));
			}	
		}
	}
	public OfferableSelectionPane(buyOffer offer,Collection<Offerable> assets,ButtonGroup anotherGroup)
	{
		offerbles = new HashMap<JToggleButton, Offerable> ();
		res=offer;
		JToggleButton curr;
		group=anotherGroup;
		this.setLayout(new BorderLayout());
		for (Offerable offerable : assets) {
			if(group==null)//more then one selection allowed
			{
				curr=new JCheckBox(offerable.getName());
			}
			else
			{
				curr=new JRadioButton(offerable.getName());
				group.add(curr);
			}
			offerbles.put(curr, offerable);
			checkPanel.add(curr);
		}
		this.add(checkPanel,BorderLayout.CENTER);	


		
	}
	public OfferableSelectionPane(buyOffer offer,Collection<Offerable> assets)
	{
		this(offer,assets,null);
		
	}

}
