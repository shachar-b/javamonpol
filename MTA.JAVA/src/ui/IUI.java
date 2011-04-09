package ui;

import java.util.ArrayList;

import monopoly.GameManager.jailActions;
import monopoly.buyOffer;
import players.Player;
import squares.Square;
import ui.guiComponents.MainWindow;
import assets.Asset;
import assets.AssetGroup;
import assets.City;
import assets.Offerable;
import cards.ActionCard;

/**
 * public interface IUI
 * public
 * An interface for a game's user interface
 * @author Omer Shenhar and Shachar Butnaro
 */
public interface IUI {

	public MainWindow getFrame();

	/**
	 * method void displayMessage(String message)
	 * public
	 * Displays a message to the output 
	 * @param message - A String depicting the message displayed
	 */
	public void displayMessage(String message);

	/**
	 * method boolean askYesNoQuestion(String question)
	 * public.
	 * Prompts the user for his/her name.
	 * @return String containing the user's name.
	 */
	public String askName();

	/**
	 * method boolean askYesNoQuestion(String question);
	 * public
	 * Asks the player a question and returns a boolean.
	 * @param question - A String depicting the question asked.
	 * @return true IFF received y(or Y).
	 */
	public boolean askYesNoQuestion(String question);

	/**
	 * method int askNumericQuestion(String question)
	 * public
	 * Asks the player a question and returns an unbounded integer.
	 * @param question - A String depicting the question asked.
	 * @return - The integer received from user.
	 */
	public int askNumericQuestion(String question);

	/**
	 * method int askNumericQuestion(String question, int lowerBound, int upperBound)
	 * public
	 * Asks the player a question and returns an integer within the specified bounds.
	 * @param question - A String depicting the question asked.
	 * @param lowerBound - The lowest integer that can be returned.
	 * @param upperBound - The highest integer that can be returned.
	 * @return - The integer received from user, within bounds.
	 */
	public int askNumericQuestion(String question, int lowerBound, int upperBound);

	/**
	 * method void askOfferableSellQuestions(Player player, buyOffer offer, OfferType type, boolean multipleSelection)
	 * public
	 * Displays the list of Assets/Groups available to sell and prompts the user to choose.
	 * @param player - The valid non-null player
	 * @param offer - An initialized helper offer which fills up with the player's choice.
	 * @param type - Assets/Groups
	 * @param multipleSelection - Signifies whether the player can select one or many assets/groups.
	 */
	public void askOfferableSellQuestions(Player player, buyOffer offer, OfferType type, boolean multipleSelection);

	/**
	 * method int chooseAnOffer(ArrayList<buyOffer> buyOffers)
	 * public
	 * Displays available offers and prompts the user to choose from it.
	 * @param buyOffers A list of available offers to choose from.
	 * @return An integer containing the player's choice.
	 */
	public int chooseAnOffer(ArrayList<buyOffer> buyOffers);

	/**
	 * method void notifyPlayerLanded(Player p, Square currSQ)
	 * public
	 * Notifies the output where the player landed after moving.
	 * @param p the valid non-null player.
	 * @param currSQ the square that the player landed on.
	 */
	public void notifyPlayerLanded(Player p, Square currSQ);

	/**
	 * method void notifyPlayerLandsOnParkingSquare(Player player)
	 * public
	 * Notifies the output that the player landed on Parking.
	 * @param player the valid non-null player
	 */
	public void notifyPlayerLandsOnParkingSquare(Player player);

	/**
	 * method void notifyPlayerLandsOnStartSquare(Player player)
	 * public
	 * Notifies the output that the player landed on Start.
	 * @param player the valid non-null player
	 */
	public void notifyPlayerLandsOnStartSquare(Player player);

	/**
	 * method void notifyPlayerLandsOnJailFreePass(Player player)
	 * public
	 * Notifies the output that the player landed on Jail/Free Pass
	 * @param player the valid non-null player
	 */
	public void notifyPlayerLandsOnJailFreePass(Player player);

	/**
	 * method void notifyPlayerLandsOnGoToJail(Player player)
	 * public
	 * Notifies the output that the player landed on GoToJail.
	 * @param player the valid non-null player
	 */
	public void notifyPlayerLandsOnGoToJail(Player player);

	/**
	 * method void notifyPlayerBoughtHouse(Player player, City asset)
	 * public
	 * Notifies the output that the player bought a house in a city.
	 * @param player The valid non-null player.
	 * @param asset The city in which the house was bought.
	 */
	public void notifyPlayerBoughtHouse(Player player, City asset);

	/**
	 * method void notifyPlayerBoughtAsset(Player player, Asset asset)
	 * public
	 * Notifies the output that the player bought an asset
	 * @param player The valid non-null player.
	 * @param asset The asset bought
	 */
	public void notifyPlayerBoughtAsset(Player player, Asset asset);

	/**
	 * method void notifyPlayerPaysRent(Player player, Asset asset, Player owner)
	 * public
	 * Notifies the output that a player has to pay rent for landing on another player's asset.
	 * @param player The valid non-null player that has to pay.
	 * @param asset The asset that the rent is for.
	 * @param owner The valid non-null player the owns the asset.
	 */
	public void notifyPlayerPaysRent(Player player, Asset asset, Player owner);

	/**
	 * method void notifyPlayerGotCard(Player player,ActionCard card)
	 * public
	 * Notifies the output that a player got a surprive/callup card, and which card.
	 * @param player The valid non-null player
	 * @param card The card drawn.
	 */
	public void notifyPlayerGotCard(Player player,ActionCard card);	

	/**
	 * method void notifyPlayerCantBuy(Player player, String what, int cost)
	 * public
	 * Notifies the output that a player can't buy an asset or a house.
	 * @param player The valid non-null player.
	 * @param what A String depicting what cannot be bought
	 * @param cost The cost of the transaction.
	 */
	public void notifyPlayerCantBuy(Player player, String what, int cost);

	/**
	 * method void notifyPlayerIsParked(Player player)
	 * public
	 * Notifies the output that a player is parked and cannot move.
	 * @param player The valid non-null player.
	 */
	public void notifyPlayerIsParked(Player player);

	/**
	 * method void notifyBidEvent(Offerable asset)
	 * public
	 * Checks whether a bid is for an Asset or a Group and calls the appropriate function.
	 * @param asset The Asset/Group being sold
	 */
	public void notifyBidEvent(Offerable asset);

	/**
	 * method void notifyBidEvent(Asset asset)
	 * public
	 * Notifies the output that an asset is up for sale.
	 * @param asset The asset that is being offered.
	 */
	public void notifyBidEvent(Asset asset);

	/**
	 * method void notifyBidEvent(AssetGroup group)
	 * public
	 * Notifies the output that an asset group is up for sale.
	 * @param group The asset group that is being offered.
	 */
	public void notifyBidEvent(AssetGroup group);

	/**
	 * method void notifyPassStartSquare(int bonus)
	 * public
	 * Notifies the output that a player has passed over Start and got a bonus.
	 * @param bonus The amount of the bonus received.
	 */
	public void notifyPassStartSquare(int bonus);

	/**
	 * method void notifyNewRound(Player p, int roundNumber, Square currSQ)
	 * public
	 * Notifies the output that a new turn has begun and shows relevant information.
	 * @param p The valid non-null player.
	 * @param roundNumber A positive integer containing the current round number.
	 * @param currSQ A monopoly game square on which the player is standing.
	 */
	public void notifyNewRound(Player p, int roundNumber, Square currSQ);

	/**
	 * method void notifyGameWinner(Player player)
	 * public
	 * Notifies the output that the game has ended and who the winner is.
	 * @param player A valid non-null player.
	 */
	public void notifyGameWinner(Player player);

	/**
	 * method void notifyDiceRoll(int LastRollOutcome)
	 * public
	 * Notifies the output that a dice has been rolled and what the outcome was.
	 * @param LastRollOutcome An integer from 1 to 6 containing the dice roll outcome.
	 */
	public void notifyDiceRoll(int LastRollOutcome);

	/**
	 * method void notifyJailAction(Player player, jailActions action)
	 * public
	 * Notifies the output what happened to the player in jail
	 * @param player A valid non-null player.
	 * @param action The action the player took.
	 */
	public void notifyJailAction(Player player, jailActions action);

	/**
	 * method void notifyPlayerExceededSellOfferCount(Player player)
	 * public
	 * Notifies the output that the player cannot sell anymore due to exceeding the limit.
	 * @param player A valid non-null player.
	 */
	public void notifyPlayerExceededSellOfferCount(Player player);

	/**
	 * method void notifyPlayerOutOfAssets(Player player)
	 * public
	 * Notifies the output that the player cannot sell anymore due to running out of assets.
	 * @param player A valid non-null player.
	 */
	public void notifyPlayerOutOfAssets(Player player);

	/**
	 * method void notifyTradeEvent(Player player, Offerable asset,buyOffer winningOffer)
	 * public
	 * Notifies the output the result of a successful trade event.
	 * @param player A valid non-null player - the seller.
	 * @param asset The asset/group sold
	 * @param winningOffer The offer chosen by the seller.
	 */
	public void notifyTradeEvent(Player player, Offerable asset,buyOffer winningOffer);

	/**
	 * method void notifyTradeCanceled(Player player)
	 * public
	 * Notifies the output that a player has canceled a trade.
	 * @param player A valid non-null player.
	 */
	public void notifyTradeCanceled(Player player);

	/**
	 * method void notifyNumOfPlayers(int numOfPlayers, int numOfComputerPlayers)
	 * public
	 * Notifies the output of the players in game.
	 * @param numOfPlayers An integer between 2 to 6
	 * @param numOfComputerPlayers An integer between 0 to numOfPlayers
	 */
	public void notifyNumOfPlayers(int numOfPlayers, int numOfComputerPlayers);

	/**
	 * method public void notifyPlayerLeftGame(Player p)
	 * public
	 * notifies the console that a player has left the game.
	 * @param p a valid non-null player.
	 */
	public void notifyPlayerLeftGame(Player p);
}