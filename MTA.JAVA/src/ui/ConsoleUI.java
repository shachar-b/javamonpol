package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.management.RuntimeErrorException;
import javax.swing.JFrame;

import monopoly.GameManager;
import monopoly.GameManager.jailActions;
import monopoly.buyOffer;
import players.Player;
import squares.Square;
import ui.guiComponents.dialogs.OfferMakerDialog;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import assets.Offerable;
import cards.ActionCard;

/**
 * public class ConsoleUI implements IUI
 * @see IUI
 * @visibility public
 * A console UI for the Monopoly game
 * @author Omer Shenhar and Shachar Butnaro
 */
public class ConsoleUI implements IUI {

	private Scanner sc = new Scanner(System.in);

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerLanded(players.Player, squares.Square)
	 */
	public void notifyPlayerLanded(Player p, Square currSQ)
	{
		String message = "Player " + p.getName() + " landed on square "
		+ (p.getCurrentPosition()+1) + ": "
		+ currSQ.getName(); 
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyNewRound(players.Player, int, squares.Square)
	 */
	public void notifyNewRound(Player p, int roundNumber, Square currSQ)
	{
		String message = "\nRound: " + roundNumber +"\t Player: " + p.getName() + "\t Balance : " + p.getBalance()
		+"\nIs currently on square " + (p.getCurrentPosition()+1) + ": " + currSQ.getName();
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyGameWinner(players.Player)
	 */
	public void notifyGameWinner(Player player)
	{
		String message = "The winner is: " + player.getName() + "!!!";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyDiceRoll(int)
	 */
	public void notifyDiceRoll(int LastRollOutcome)
	{
		String message = "Rolled a " + LastRollOutcome + ".";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPassStartSquare(int)
	 */
	public void notifyPassStartSquare(int bonus)
	{
		displayMessage("Received Start square pass bonus of " + bonus + "!");
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerPaysRent(players.Player, assets.Asset, players.Player)
	 */
	public void notifyPlayerPaysRent(Player player, Asset asset, Player owner){
		String message = player.getName() + " has to pay rent of " + asset.getRentPrice() + " to " + owner.getName() + "!";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerBoughtAsset(players.Player, assets.Asset)
	 */
	public void notifyPlayerBoughtAsset(Player player, Asset asset)
	{
		String message = player.getName() + " bought " + asset.getName() + " for " + asset.getCost() + "!";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerBoughtHouse(players.Player, assets.City)
	 */
	public void notifyPlayerBoughtHouse(Player player, City asset)
	{
		String message = player.getName() + " bought house number " + asset.getNumHouses() + " in " + asset.getName() + " for " + asset.getCostOfHouse() + "!";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerLandsOnParkingSquare(players.Player)
	 */
	public void notifyPlayerLandsOnParkingSquare(Player player) {
		String message =  player.getName() + " will wait a turn on parking.";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyJailAction(players.Player, monopoly.GameManager.jailActions)
	 */
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

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerLandsOnStartSquare(players.Player)
	 */
	public void notifyPlayerLandsOnStartSquare(Player player) {
		String message = player.getName() + " has stepped on Start and gets and additional " + GameManager.START_LAND_BONUS  + "!";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerLandsOnJailFreePass(players.Player)
	 */
	public void notifyPlayerLandsOnJailFreePass(Player player) {
		String message = player.getName() + " is walking through the free pass.";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerLandsOnGoToJail(players.Player)
	 */
	public void notifyPlayerLandsOnGoToJail(Player player) {
		String message =  player.getName() + " is thrown to Jail!";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#askYesNoQuestion(java.lang.String)
	 */
	public boolean askYesNoQuestion(String question) {
		displayMessage(question);
		System.out.print("INPUT (y/n): ");
		return (sc.next().toLowerCase().equals("y"));
	}

	/* (non-Javadoc)
	 * @see ui.IUI#askNumericQuestion(java.lang.String)
	 */
	public int askNumericQuestion(String question) {
		return askNumericQuestion(question, Integer.MIN_VALUE+1, Integer.MAX_VALUE);		
	}

	/* (non-Javadoc)
	 * @see ui.IUI#askNumericQuestion(java.lang.String, int, int)
	 */
	public int askNumericQuestion(String question, int lowerBound, int upperBound) {
		System.out.print(question+" Input: ");
		return utilGetSafeIntInput(lowerBound, upperBound);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#askName()
	 */
	public String askName() {
		System.out.print("Enter your name: ");
		return (sc.nextLine());
	}

	/* (non-Javadoc)
	 * @see ui.IUI#displayMessage(java.lang.String)
	 */
	public void displayMessage (String message)
	{
		System.out.println(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerGotCard(players.Player, cards.ActionCard)
	 */
	public void notifyPlayerGotCard(Player player, ActionCard card) {
		String message = player.getName() + " received card : " + card;
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerCantBuy(players.Player, java.lang.String, int)
	 */
	public void notifyPlayerCantBuy(Player player, String what, int cost) {
		String message = player.getName() + " cant buy " + what + " for " + cost + " due to insufficient funds!"; 
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerIsParked(players.Player)
	 */
	public void notifyPlayerIsParked(Player player) {
		String message = player.getName() + " is parked and cannot move!";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyBidEvent(assets.Offerable)
	 */
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

	/* (non-Javadoc)
	 * @see ui.IUI#notifyBidEvent(assets.Asset)
	 */
	public void notifyBidEvent(Asset asset) {
		String message = asset.getName() + " is up for bidding. It is a part of " +
		asset.getGroup().getName() + ". It's listed price is " + asset.getCost() +
		" and the current rental cost is " + asset.getRentPrice();
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyBidEvent(assets.AssetGroup)
	 */
	public void notifyBidEvent(AssetGroup group) {
		String message = group.getName() + " is up for bidding. It includes:\n";
		for (Asset asset:group)
		{
			message+="\t"+asset.getName() +": It's listed price is " + asset.getCost() +
			" and the current rental cost is " + asset.getRentPrice();
		}
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerOutOfAssets(players.Player)
	 */
	public void notifyPlayerOutOfAssets(Player player)
	{
		String message = player.getName() + " has no assets left to sell!"; 
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerExceededSellOfferCount(players.Player)
	 */
	public void notifyPlayerExceededSellOfferCount(Player player)
	{
		String message = player.getName() + " exceeded maximum number of sell offers!";
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#askOfferableSellQuestions(players.Player, monopoly.buyOffer, ui.OfferType, boolean)
	 */
	public void askOfferableSellQuestions(Player player, buyOffer offer,OfferType type, boolean multipleSelection)
	{
		int numberOfChoosen=0;
		int playerChoice=-1;
		ArrayList<Offerable> tradeables;
		OfferMakerDialog diag=new OfferMakerDialog(new JFrame(), player.tradeableAssets(), player.tradeableGroups(), offer, !multipleSelection);
		diag.setVisible(true);
		
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
					displayMessage("You already chose this");

				}
				else
				{
					offer.addToOffer(tradeables.get(playerChoice-1));
					numberOfChoosen++;
				}
			}
			if (!multipleSelection)
				playerChoice=0; //To force single-selection
			else if(numberOfChoosen==tradeables.size())//do this only for multiple selection
			{
				displayMessage("You have chosen all your available assets");
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyTradeEvent(players.Player, assets.Offerable, monopoly.buyOffer)
	 */
	public void notifyTradeEvent(Player player, Offerable asset,buyOffer winningOffer) {
		String message=player.getName()+ " sold " + asset.getName()+" to "
		+winningOffer.getOfferMaker().getName()+" for "+ winningOffer;
		displayMessage(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyTradeCanceled(players.Player)
	 */
	public void notifyTradeCanceled(Player player) {
		displayMessage(player.getName() + " has called off the sale!");

	}

	/* (non-Javadoc)
	 * @see ui.IUI#chooseAnOffer(java.util.ArrayList)
	 */
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

	/* (non-Javadoc)
	 * @see ui.IUI#notifyNumOfPlayers(int, int)
	 */
	public void notifyNumOfPlayers(int numOfPlayers, int numOfComputerPlayers) {
		displayMessage("Starting game with " + numOfPlayers + " players, " + numOfComputerPlayers + " are computer controlled.");
		displayMessage("Randomly setting playing order.\n");
	}

	/**
	 * method int utilGetSafeIntInput(int lowerBound, int upperBound)
	 * @visibility private
	 * Gets an integer from the Scanner used by the UI, and check that it's within bounds. 
	 * @param lowerBound An integer containing the low bound for the input.
	 * @param upperBound An integer containing the high bound for the input.
	 * @return The integer received from user.
	 */
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
		sc.nextLine(); //To clear CR/LF from buffer
		return answer;
	}

	@Override
	public void notifyPlayerLeftGame(Player p) {
		String message="player "+p.getName()+	" has left the game!"; 
		
		displayMessage(message);
		
	}
	
}

