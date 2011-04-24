package monopoly;

import generated.jaxb.CardBase;
import generated.jaxb.GetOutOfJailCard;
import generated.jaxb.GotoCard;
import generated.jaxb.MonetaryCard;
import generated.jaxb.Monopoly;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import squares.GoToJailSquare;
import squares.Square;
import squares.StartSquare;
import squares.SurpriseCardSquare;
import squares.WarrantCardSquare;
import assets.UtilOrTranspoAssetGroup;
import cards.ActionCard;
import cards.ShaffledDeck;

public class XMLInitializer implements MonopolyInitilizer {

	generated.jaxb.Monopoly monopolyCollection;
	
	public void init()
	{
		File xmlFile = new File(GameManager.DataFile);
		JAXBContext jc;
		Unmarshaller unmarshaller;
		try {
			jc = JAXBContext.newInstance("generated.jaxb");
			unmarshaller = jc.createUnmarshaller();
			monopolyCollection = (Monopoly)unmarshaller.unmarshal(xmlFile);
		} catch (JAXBException e) {
			throw new RuntimeException("Error entering JAXB API!");
		}
	}
	
	private ActionCard cardFactory(CardBase cb, boolean isSurpriseCard)
	{
		ActionCard currentCard = null;
		GameManager.AgainstWho against = (cb.getNum()==1)?GameManager.AgainstWho.OtherPlayers:GameManager.AgainstWho.Treasury;
		String text = cb.getText();
		if (cb instanceof MonetaryCard)
		{
			MonetaryCard inputCard = (MonetaryCard)cb;
			text = text.replaceAll("%s", inputCard.getSum()+"");
			currentCard = new ActionCard(ActionCard.SURPRISE_CARD, text, (int)inputCard.getSum(), against, null, isSurpriseCard);
		}
		else if (cb instanceof GotoCard)
		{
			GotoCard inputCard = (GotoCard)cb;
			String gotoLocation = inputCard.getTo();
			Class<? extends Square> whereTo =null;
			if (gotoLocation.toUpperCase().equals("NEXT_WARRANT"))
				whereTo = WarrantCardSquare.class;
			else if (gotoLocation.toUpperCase().equals("NEXT_SURPRISE"))
				whereTo = SurpriseCardSquare.class;
			else if (gotoLocation.toUpperCase().equals("START"))
				whereTo = StartSquare.class;
			else //Then --> (gotoLocation.toUpperCase().equals("JAIL"))
				whereTo=GoToJailSquare.class;
			currentCard = new ActionCard(ActionCard.SURPRISE_CARD, text, 0, against, whereTo, isSurpriseCard);
		}
		else if (cb instanceof GetOutOfJailCard)
		{
			currentCard = new ActionCard(ActionCard.SURPRISE_CARD, text, 0, against, Square.class, isSurpriseCard);
		}
		else
		{//Invalid card type
			throw new RuntimeException("Invalid card type!");
		}
		return currentCard;
	}
	
	@Override
	public ShaffledDeck initSurprise() {
		ShaffledDeck surprise = new ShaffledDeck();
		// read surprise cards
		for (CardBase cb : monopolyCollection.getSurprise().getSurpriseCards())
		{
			surprise.add(cardFactory(cb, true));
		}
		return surprise;
	}

	@Override
	public ShaffledDeck initCallUp() {
		ShaffledDeck callUp = new ShaffledDeck();
		// read call-up cards
		for (CardBase cb : monopolyCollection.getWarrant().getWarrantCards())
		{
			callUp.add(cardFactory(cb, false));
		}
		return callUp;
	}

	@Override
	public UtilOrTranspoAssetGroup initUtilities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UtilOrTranspoAssetGroup initTransportation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Square> initBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
