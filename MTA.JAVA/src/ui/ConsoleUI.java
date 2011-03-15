package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

import monopoly.GameManager;
import monopoly.GameManager.jailActions;
import monopoly.buyOffer;
import players.Player;
import squares.Square;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import assets.Offerable;
import cards.ActionCard;

public class ConsoleUI implements IUI {
	
	private Scanner sc = new Scanner(System.in);
	
	@Override
	public void notifyPlayerLanded(Player p, Square currSQ)
	{
		String message = "Player " + p.getName() + " landed on square "
						+ (p.getCurrentPosition()+1) + ": "
						+ currSQ.getName(); 
		displayMessage(message);
	}
	
	@Override
	public void notifyNewRound(Player p, int roundNumber, Square currSQ)
	{
		String message = "\nRound: " + roundNumber +"\t Player: " + p.getName() + "\t Balance : " + p.getBalance()
		+"\nIs currently on square " + (p.getCurrentPosition()+1) + ": " + currSQ.getName();
		displayMessage(message);
	}
	
	@Override
	public void notifyGameWinner(Player player)
	{
		String message = "The winner is: " + player.getName() + "!!!";
		displayMessage(message);
	}
	
	@Override
	public void notifyDiceRoll(int LastRollOutcome)
	{
		String message = "Rolled a " + LastRollOutcome + ".";
		displayMessage(message);
	}
	
	@Override
	public void notifyPassStartSquare(int bonus)
	{
		displayMessage("Received Start square pass bonus of " + bonus + "!");
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
	public void notifyJailAction(Player player, jailActions action){
		String message;
		switch (action) {
		case USED_CARD:
			message = player.getName() + " used a get out of jail freeCard and is out of jail in next turn!"; 
			break;
		case ROLLED_DOUBLE:
			message = player.getName() + " has a double and is out of jail in next turn!"; 
			break;

		case STAY_IN_JAIL:
			message = player.getName() + " stays in jail!";
			break;
			
		default: //Shouldn't get here
			throw new RuntimeErrorException(null, "Unknown agrument for function \"notifyJailAction\"");
		}
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
	}

	@Override
	public boolean askYesNoQuestion(String question) {
		displayMessage(question);
		System.out.print("INPUT (y/n): ");
		return (sc.next().toLowerCase().equals("y"));
	}

	@Override
	public int askNumericQuestion(String question) {
		return askNumericQuestion(question, Integer.MIN_VALUE+1, Integer.MAX_VALUE);		
	}

	
	public int askNumericQuestion(String question, int lowerBound, int upperBound) {
		displayMessage(question);
		return utilGetSafeIntInput(lowerBound, upperBound);
	}

	
	@Override
	public String askName() {
		System.out.print("Enter your name: ");
		return (sc.nextLine());
	}
	
	public void printAssetList(Player p)
	{
		int count = 1;
		for (Asset asset : p.getAssetList())
		{
			displayMessage(count + " : " + asset.getName());
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
	public void notifyBidEvent(Offerable asset)
	{
		switch (asset.getType()) {
		case Groups:
			notifyBidEvent((AssetGroup)asset);
			break;
		case Assets:
			notifyBidEvent((Asset)asset);
			break;
			
		default: //Shouldn't get here
			throw new RuntimeErrorException(null, "Unknown agrument for function \"notifyBidEvent\"");
		}
		
	}
	
	@Override
	public void notifyBidEvent(Asset asset) {
		String message = asset.getName() + " is up for bidding. It is a part of " +
		asset.getGroup().getName() + ". It's listed price is " + asset.getCost() +
		" and the current rental cost is " + asset.getRentPrice();
		displayMessage(message);
	}

	@Override
	public void notifyBidEvent(AssetGroup group) {
		String message = group.getName() + " is up for bidding. It includes:\n";
		for (Asset asset:group)
		{
			message+="\t"+asset.getName() +": It's listed price is " + asset.getCost() +
			" and the current rental cost is " + asset.getRentPrice();
		}
		displayMessage(message);
	}
	
	@Override
	public void notifyPlayerOutOfAssets(Player player)
	{
		String message = player.getName() + " has no assets left to sell!"; 
		displayMessage(message);
	}
	
	@Override
	public void notifyPlayerExceededSellOfferCount(Player player)
	{
		String message = player.getName() + " exceeded maximum number of sell offers!";
		displayMessage(message);
	}
	
	@Override
	public void askOfferableSellQuestions(Player player, buyOffer offer,OfferType type, boolean multipleSelection)
	{
		int numberOfChoosen=0;
		int playerChoice=-1;
		ArrayList<Offerable> tradeables;

		switch (type) {
		case Groups:
			tradeables=player.tradeableGroups();
			break;

		case Assets:
			tradeables=player.tradeableAssets();
			break;
			
		default: //Shouldn't get here
			throw new RuntimeErrorException(null, "Unknown type in function \"askOfferableSellQuestions\"");
		}
		

		System.out.println("You have the following "+type.name()+": ");
		for (int i=0; i<tradeables.size();i++)
		{
			System.out.println((i+1) + " : " + tradeables.get(i).getName());
		}
		String message = "Enter the index of the "+type.name() + " you want to sell";
		if (multipleSelection)
			message+=" (one by one), press 0 to end offer: ";
		else
			message+=", press 0 to cancel offer: ";
		while (playerChoice!=0)
		{
			System.out.print(message);
			playerChoice =utilGetSafeIntInput(0, tradeables.size());
			if(playerChoice!=0)
			{
				if(offer.has(tradeables.get(playerChoice-1)))
				{
					displayMessage("you allready choose this");
					
				}
				else
				{
					offer.addToOffer(tradeables.get(playerChoice-1));
					numberOfChoosen++;
				}
				
			}
			
			if (!multipleSelection)
					playerChoice=0; //To force single-selection
			if(numberOfChoosen==tradeables.size())
			{
				displayMessage("you have choosen all your available assets");
				break;
			}
			
	
		}
	}

	@Override
	public void notifyTradeEvent(Player player, Offerable asset,buyOffer winningOffer) {
		String message=player.getName()+ " sold " + asset.getName()+" to "+winningOffer.getOfferMaker().getName()
		+" for "+ winningOffer;
		displayMessage(message);
	}

	@Override
	public void notifyTradeCanceled(Player player) {
		displayMessage(player.getName() + " has called off the sale!");
		
	}

	@Override
	public int chooseAnOffer(ArrayList<buyOffer> buyOffers)
	{
		int index=0;
		displayMessage("choose one of the follwing offers or press 0 to choose none: ");
		for(buyOffer offer:buyOffers)
		{
			displayMessage((index+1)+" "+offer.getOfferMaker().getName()+" : "+offer);
			index++;
			
			
		}
		System.out.print("choose one or press zero: ");
		return utilGetSafeIntInput(0, buyOffers.size())-1;
	}
	private int utilGetSafeIntInput(int lowerBound, int upperBound)
	{
		Integer answer=Integer.MIN_VALUE;
		while(answer<lowerBound || answer >upperBound)
		{
			try {
				answer=sc.nextInt();
				if (answer<lowerBound || answer > upperBound)
					System.out.println("Invalid input, can accept integers from "+lowerBound+" to " + upperBound);
			}


			catch (InputMismatchException e) {
				sc.next();
				displayMessage("Invalid input - enter a numeric input");
			}

		}
		return answer;
	}
	
}
	
	