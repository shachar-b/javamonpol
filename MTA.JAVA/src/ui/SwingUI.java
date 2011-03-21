package ui;

import java.util.ArrayList;

import monopoly.buyOffer;
import monopoly.GameManager.jailActions;
import players.Player;
import squares.Square;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import assets.Offerable;
import cards.ActionCard;

public class SwingUI implements IUI {

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public String askName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean askYesNoQuestion(String question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int askNumericQuestion(String question) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askNumericQuestion(String question, int lowerBound,
			int upperBound) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void askOfferableSellQuestions(Player player, buyOffer offer,
			OfferType type, boolean multipleSelection) {
		// TODO Auto-generated method stub

	}

	@Override
	public int chooseAnOffer(ArrayList<buyOffer> buyOffers) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notifyPlayerLanded(Player p, Square currSQ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerLandsOnParkingSquare(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerLandsOnStartSquare(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerLandsOnJailFreePass(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPlayerLandsOnGoToJail(Player player) {
		// TODO Auto-generated method stub

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

}
