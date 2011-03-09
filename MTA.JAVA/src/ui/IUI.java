package ui;

import cards.ActionCard;
import assets.Asset;
import assets.City;
import players.Player;

public interface IUI {
	
	public void notifyPlayerMove(Player player, int from, int to);
	
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
}
