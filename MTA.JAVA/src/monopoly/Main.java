package monopoly;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ui.guiComponents.MainWindow;

/**
 * Monopoly game
 * @author Omer Shenhar and Shachar Butnaro
 */
public class Main {
	
	/**
	 * This is the entry point.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MainWindow();
                frame.setVisible(true);
            }
        });
		Monopoly game = new Monopoly();
		GameManager.currentGame = game;
		game.init();
		game.play();
	}
}
