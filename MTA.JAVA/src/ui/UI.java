package ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.RuntimeErrorException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import listeners.gameActions.GameActionEvent;
import listeners.gameActions.GameActionEventListener;
import monopoly.GameManager;
import monopoly.GameManager.jailActions;
import players.ComputerPlayer;
import players.Player;
import squares.Square;
import ui.guiComponents.CardPanel;
import ui.guiComponents.MainWindow;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import assets.Offerable;
import cards.ActionCard;

/**
 * public class ConsoleUI implements IUI
 * @see IUI
 * public
 * A console UI for the Monopoly game
 * @author Omer Shenhar and Shachar Butnaro
 */
public class UI implements IUI {

	private MainWindow frame;
	
	public UI()
	{
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new MainWindow();
                frame.setExtendedState(Frame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            }
        });
	}
	
	public MainWindow getFrame()
	{
		return frame;
	}
	
	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerLanded(players.Player, squares.Square)
	 */
	public void notifyPlayerLanded(Player p, Square currSQ)
	{
		String message = "Player " + p.getName() + " landed on square "
		+ (p.getCurrentPosition()+1) + ": "
		+ currSQ.getName(); 
		displayMessage(message);
		frame.movePlayer(p);
		frame.getPlayerPanel().setSquarePanelContent(currSQ,p);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyNewRound(players.Player, int, squares.Square)
	 */
	public void notifyNewRound(Player p, int roundNumber, Square currSQ)
	{
		String message = "\nRound: " + roundNumber +"\t Player: " + p.getName() + "\t Balance : " + p.getBalance()
		+"\nIs currently on square " + (p.getCurrentPosition()+1) + ": " + currSQ.getName();
		displayMessage(message);
		frame.setPlayerPanel(p).addGameActionsListener(new GameActionEventListener() {
			
			@Override
			public void eventHappened(GameActionEvent gameActionEvent) {
					GameManager.currentGame.eventDispatch(gameActionEvent.getMessage());
			}
		});
		if(p instanceof ComputerPlayer)
		{
			GameManager.currentGame.eventDispatch("computer");
		}
		frame.validate();
		frame.repaint();
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyGameWinner(players.Player)
	 */
	public void notifyGameWinner(Player player)
	{
		String message = "The winner is: " + player.getName() + "!!!";
		JOptionPane.showMessageDialog(frame,message);
		frame.clearPlayerPanelArea();
		
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
		return true;
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
		return 0;
	}

	/* (non-Javadoc)
	 * @see ui.IUI#askName()
	 */
	public String askName() {
		return "mox";
	}

	/* (non-Javadoc)
	 * @see ui.IUI#displayMessage(java.lang.String)
	 */
	public void displayMessage (String message)
	{
		frame.addLineToConsole(message);
	}

	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerGotCard(players.Player, cards.ActionCard)
	 */
	public void notifyPlayerGotCard(Player player, ActionCard card) {
		displaySelfClosingCardDialog(player, card);
		String message = player.getName() + " received card : " + card;
		displayMessage(message);
	}

	private void displaySelfClosingCardDialog(Player player, ActionCard card)
	{
		final JDialog cardDialog = new JDialog(frame, player.getName()+" got a card!", true);
		cardDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		cardDialog.add(new CardPanel(card));
		cardDialog.pack();
		
		Timer timer = new Timer(5000, new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                cardDialog.setVisible(false); 
                cardDialog.dispose(); 
            } 
        }); 
        timer.setRepeats(false); 
        timer.start();
        cardDialog.setVisible(true);
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
	 * @see ui.IUI#notifyNumOfPlayers(int, int)
	 */
	public void notifyNumOfPlayers(int numOfPlayers, int numOfComputerPlayers) {
		displayMessage("Starting game with " + numOfPlayers + " players, " + numOfComputerPlayers + " are computer controlled.");
		displayMessage("Randomly setting playing order.\n");
	}


	/* (non-Javadoc)
	 * @see ui.IUI#notifyPlayerLeftGame(players.Player)
	 */
	@Override
	public void notifyPlayerLeftGame(Player p) {
		String message="player "+p.getName()+	" has left the game!";
		frame.getGameboard().removePlayerIcon(p);
		displayMessage(message);
		
	}
}

