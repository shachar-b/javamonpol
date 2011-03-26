/**
 * 
 */
package ui.guiComponents.dialogs.OfferMakerPanes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import monopoly.buyOffer;

/**
 * @author Omer Shenhar and Shachar Butnaro
 *
 */
public class MoneyOfferPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSlider MoneySelectionSlider;
	private JTextField textArea;
	
	private buyOffer res;

	/**
	 * 
	 */
	public MoneyOfferPane(buyOffer offer) {
		initComponets(offer.getOfferMaker().getBalance());
		res=offer;
		
	}
	public void updateText()
	{
		int colCount=Math.max((int)(Math.log10(MoneySelectionSlider.getValue())),1);
		textArea.setText(""+MoneySelectionSlider.getValue());
		textArea.setColumns(colCount);
	}
	
	public void updateOfferAccordingToSelection()
	{
		res.addToOffer(MoneySelectionSlider.getValue());
	}
	
	
	private void initComponets(int maxAmount) {
		MoneySelectionSlider = new JSlider();
		this.setLayout(new BorderLayout());
		//---- MoneySlider ----
		MoneySelectionSlider.setValue(0);
		MoneySelectionSlider.setMinimum(0);
		MoneySelectionSlider.setMaximum(maxAmount-1);
		MoneySelectionSlider.setMajorTickSpacing(Math.max((maxAmount/10),1));
		MoneySelectionSlider.setMinorTickSpacing(Math.max((maxAmount/20),1));
		MoneySelectionSlider.setPaintLabels(true);
		MoneySelectionSlider.setPaintTicks(true);
		this.add(MoneySelectionSlider,BorderLayout.CENTER);
		textArea=new JTextField();
		int colCount=Math.max((int)(Math.log10(MoneySelectionSlider.getValue())),1);
		textArea.setColumns(colCount);
		textArea.setText(""+MoneySelectionSlider.getValue());
		textArea.setEditable(false);
		MoneySelectionSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateText();
				
			}
		});
		this.add(textArea,BorderLayout.EAST);

		
		
		
		
		
	}

}
