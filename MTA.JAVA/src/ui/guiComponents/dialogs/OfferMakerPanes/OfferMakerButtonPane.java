/**
 * 
 */
package ui.guiComponents.dialogs.OfferMakerPanes;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class OfferMakerButtonPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static OfferMakerButtonPane pane=new OfferMakerButtonPane();
	private JButton OK;
	private JButton cancel;
	
	private OfferMakerButtonPane() {
		initComponents();
		
	}
	private void initComponents() {
		OK=new JButton("Offer this");
		cancel = new JButton("Offer Nothing");
		this.setLayout(new FlowLayout());
		this.add(OK);
		this.add(cancel);
		
	}
	public  static OfferMakerButtonPane getOfferMakerButtonPane()
	{
		return pane;
	}
	public JButton getOK()
	{
		return OK;
		
	}
	public JButton getCancel() {
		return cancel;
	}

}
