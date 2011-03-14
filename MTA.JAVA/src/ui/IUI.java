package ui;

import java.util.ArrayList;

import monopoly.GameManager.jailActions;
import monopoly.buyOffer;
import players.Player;
import squares.Square;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import assets.Offerable;
import cards.ActionCard;

public interface IUI {
	
	public void notifyPlayerLanded(Player p, Square currSQ);
	
	public void notifyPlayerLandsOnParkingSquare(Player player);
	
	public void notifyPlayerLandsOnStartSquare(Player player);
	
	public void notifyPlayerLandsOnJailFreePass(Player player);
	
	public void notifyPlayerLandsOnGoToJail(Player player);
	
	public void notifyPlayerMakesBuyOffer(Player buyer, Player seller, Asset asset);	
	
	public boolean askYesNoQuestion(String question);
	
	public int askNumericQuestion(String question);
	
	public void printAssetList(Player p);
	
	public void displayMessage(String message);

	public String askName();

	public void notifyPlayerBoughtHouse(Player player, City asset);

	public void notifyPlayerBoughtAsset(Player player, Asset asset);

	public void notifyPlayerPaysRent(Player player, Asset asset, Player owner);
	
	public void notifyPlayerGotCard(Player player,ActionCard card);	
	
	public void notifyPlayerCantBuy(Player player, String what, int cost);

	public void notifyPlayerIsParked(Player player);

	public void notifyBidEvent(Offerable asset);

	public void notifyBidEvent(Asset asset);

	public void notifyBidEvent(AssetGroup group);

	public void notifyPassStartSquare(int bonus);

	public void notifyNewRound(Player p, int roundNumber, Square currSQ);

	public void notifyGameWinner(Player player);

	public void notifyDiceRoll(int LastRollOutcome);

	public void notifyJailAction(Player player, jailActions action);

	public void notifyPlayerExceededSellOfferCount(Player player);

	public void notifyPlayerOutOfAssets(Player player);

	public void askOfferableSellQuestions(Player player, buyOffer offer, OfferType type, boolean multipleSelection);

	public int askNumericQuestion(String question, int lowerBound, int upperBound);

	public void notifyTradeEvent(Player player, Offerable asset,buyOffer winningOffer);

	public void notifyTradeCanceled(Player player);

	public int chooseAnOffer(ArrayList<buyOffer> buyOffers);
}
