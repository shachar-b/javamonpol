package ui.guiComponents;

import java.awt.Color;

import javax.swing.*;

import monopoly.GameManager;

import ui.utils.Utils;

import cards.ActionCard;

/**
 * public class CardPanel extends JPanel
 * A pop up dialog showing the card drawn from a cards deck.
 * @author Omer Shenhar and Shachar Butnaro
 */
public class CardPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel CardTypeLabel;
	private JLabel CardPictureLabel;
	private JLabel CardStringLabel;
	
	/**
	 * public CardPanel(ActionCard currentCard)
	 * Receives a card and constructs the correct dialog for it.
	 * @param currentCard An ActionCard which was drawn from the deck.
	 */
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

	/**
	 * Initializes all components for the class.
	 */
	private void initComponents() {
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
	}
}
