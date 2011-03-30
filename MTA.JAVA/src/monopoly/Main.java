package monopoly;

import ui.SwingUI;

/**
 * Monopoly game
 * @author Omer Shenhar and Shachar Butnaro
 */
public class Main {
	
	/**
	 * This is the entry point.
	 */
	public static void main(String[] args) {
		Monopoly game = new Monopoly();
		GameManager.currentGame = game;
		game.init();
		SwingUI ui=new SwingUI();
		game.play();
	}
}
