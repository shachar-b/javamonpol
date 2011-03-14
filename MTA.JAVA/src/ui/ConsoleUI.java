package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import monopoly.GameManager;
import players.Player;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import cards.ActionCard;

public class ConsoleUI implements IUI {
	
	private Scanner sc = new Scanner(System.in);
	
	@Override
	public void notifyPlayerMove(Player player, int from, int to) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void notifyPlayerPaysRent(Player player, Asset asset, Player owner){
		String message = player.getName() + " has to pay rent of " + asset.getRentPrice() + " to " + owner.getName() + "!";
		displayMessage(message);

	}

	@Override
	public void notifyPlayerBoughtAsset(Player player, Asset asset)
	{
		String message = player.getName() + " bought " + asset.getName() + " for " + asset.getCost() + "!";
		displayMessage(message);
	}
	
	@Override
	public void notifyPlayerBoughtHouse(Player player, City asset)
	{
		String message = player.getName() + " bought house number " + asset.getNumHouses() + " in " + asset.getName() + " for " + asset.getCostOfHouse() + "!";
		displayMessage(message);
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
	public void notifyPlayerMakesBuyOffer(Player buyer, Player seller,
			Asset asset) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean askYesNoQuestion(String question) {
		displayMessage(question);
		System.out.print("INPUT (y/n): ");
		return (sc.next().toLowerCase().equals("y"));
	}

	@Override
	public int askNumericQuestion(String question) {
		displayMessage(question);
		int answer=-1;

		while(answer==-1)
		{
			try {
				answer=sc.nextInt();
				
			}


		 catch (InputMismatchException e) {
			 sc.next();
			 displayMessage("invaild input-enter a numeric input");
		}
	}
		
		return (answer);
	}
	
	@Override
	public String askName() {
		System.out.print("Enter your name: ");
		return (sc.nextLine());
	}
	
	public void printAssetList(Player p)
	{
		int count = 1;
		for (Asset a : p.getAssetList())
		{
			displayMessage(count + " : " + a.toString());
			count++;
		}
	}
	
	public void displayMessage (String message)
	{
		System.out.println(message);
	}

	@Override
	public void notifyPlayerGotCard(Player player, ActionCard card) {
		String message = player.getName() + " received card : " + card;
		displayMessage(message);
	}

	@Override
	public void notifyPlayerCantBuy(Player player, String what, int cost) {
		String message = player.getName() + " cant buy " + what + " for " + cost + " due to insufficient funds!"; 
		displayMessage(message);
	}

	@Override
	public void notifyPlayerIsParked(Player player) {
		String message = player.getName() + " is parked and cannot move!";
		displayMessage(message);
	}

	@Override
	public void notifyBidEvent(Asset asset) {
		String message = asset.getName() + " is up for bidding. It is a part of " +
		asset.getGroup().getNameOfGroup() + ". It's listed price is " + asset.getCost() +
		" and the current rental cost is " + asset.getRentPrice();
		displayMessage(message);
	}

	@Override
	public void notifyBidEvent(AssetGroup group) {
		String message = group.getNameOfGroup() + " is up for bidding. It includes:\n";
		for (Asset asset:group)
		{
			message+="\t"+asset.getName() +": It's listed price is " + asset.getCost() +
			" and the current rental cost is " + asset.getRentPrice();
		}
		displayMessage(message);
	}
}
