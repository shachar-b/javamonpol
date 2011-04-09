/*
 * Created by JFormDesigner on Sat Apr 09 20:06:53 IDT 2011
 */

package ui.guiComponents;

import java.awt.Color;

import javax.swing.*;

import monopoly.GameManager;

import ui.utils.Utils;

import cards.ActionCard;

/**
 * @author Omer Shenhar and Shachar Butnaro
 */
public class CardPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public CardPanel(ActionCard currentCard) {
		initComponents();
		if (currentCard.isSurprise())
		{
			this.setBackground(new Color(255, 255, 157));
			CardTypeLabel.setText("Surprise Card");
			CardPictureLabel.setIcon(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"MiscIcons/Surprise2.gif"));
		}
		else //Card is a Callup card
		{
			this.setBackground(new Color(255, 168, 125));
			CardTypeLabel.setText("Call Up Card");
			CardPictureLabel.setIcon(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"MiscIcons/CallUp2.gif"));
		}
		CardStringLabel.setText(currentCard.toString());
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		CardTypeLabel = new JLabel();
		CardPictureLabel = new JLabel();
		CardStringLabel = new JLabel();

		//======== this ========
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		//---- CardTypeLabel ----
		CardTypeLabel.setText("TypeOfCard");
		add(CardTypeLabel);

		//---- CardPictureLabel ----
		CardPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(CardPictureLabel);

		//---- CardStringLabel ----
		CardStringLabel.setText("ValueOfCard");
		add(CardStringLabel);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel CardTypeLabel;
	private JLabel CardPictureLabel;
	private JLabel CardStringLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
