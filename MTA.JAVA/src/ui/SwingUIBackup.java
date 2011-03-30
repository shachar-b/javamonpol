package ui;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import monopoly.GameManager.jailActions;
import monopoly.GameManager;
import monopoly.buyOffer;
import players.Player;
import squares.Square;
import ui.guiComponents.MainWindow;
import ui.guiComponents.dialogs.OfferMakerDialog;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import assets.Offerable;
import cards.ActionCard;

public class SwingUIBackup implements IUI {
	
	MainWindow frame;

	public SwingUIBackup() {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new MainWindow();
                frame.setVisible(true);
            }
        });
	}
	@Override
	//Display message in side console window
	public void displayMessage(String message) {
		//frame.addLineToConsole(message);
	}

	@Deprecated
	public String askName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	public boolean askYesNoQuestion(String question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	public int askNumericQuestion(String question) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Deprecated
	public int askNumericQuestion(String question, int lowerBound,
			int upperBound) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void askOfferableSellQuestions(Player player, buyOffer offer,
			OfferType type, boolean multipleSelection) {
		new OfferMakerDialog(frame, player.tradeableAssets(), player.tradeableGroups(), offer, !multipleSelection);

	}

	@Override
	public int chooseAnOffer(ArrayList<buyOffer> buyOffers) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notifyPlayerLanded(Player p, Square currSQ) {
		frame.getGameBoardUI().movePlayer(p, p.getLastKnownPosition(), p.getCurrentPosition());
	}

	@Override
	public void notifyPlayerLandsOnParkingSquare(Player player) {
		String message =  player.getName() + " will wait a turn on parking.";
		displayMessage(message);
	}

	@Override
	public void notifyPlayerLandsOnStartSquare(Player player) {
		String message = player.getName() + " has stepped on Start and gets and additional " + GameManager.START_LAND_BONUS  + "!";
		displayMessage(message);
	}

	@Override
	public void notifyPlayerLandsOnJailFreePass(Player player) {
		String message = player.getName() + " is walking through the free pass.";
		displayMessage(message);
	}

	@Override
	public void notifyPlayerLandsOnGoToJail(Player player) {
		String message =  player.getName() + " is thrown to Jail!";
		displayMessage(message);
	}

	@Override
	public void notifyPlayerBoughtHouse(Player player, City asset) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerBoughtAsset(Player player, Asset asset) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerPaysRent(Player player, Asset asset, Player owner) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerGotCard(Player player, ActionCard card) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerCantBuy(Player player, String what, int cost) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerIsParked(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyBidEvent(Offerable asset) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyBidEvent(Asset asset) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyBidEvent(AssetGroup group) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPassStartSquare(int bonus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyNewRound(Player p, int roundNumber, Square currSQ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyGameWinner(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyDiceRoll(int LastRollOutcome) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyJailAction(Player player, jailActions action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerExceededSellOfferCount(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerOutOfAssets(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyTradeEvent(Player player, Offerable asset,
			buyOffer winningOffer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyTradeCanceled(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyNumOfPlayers(int numOfPlayers, int numOfComputerPlayers) {
		// TODO Auto-generated method stub

	}
	@Override
	public void notifyPlayerLeftGame(Player p) {
		
	}

}
