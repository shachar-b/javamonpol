package ui.guiComponents.dice;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.gameActions.GameActionEvent;
import listeners.gameActions.GameActionEventListener;
import listeners.gameActions.GameActionsListenableClass;
import monopoly.GameManager;
import ui.utils.Utils;

/**
 * @author Stijn Strickx, from http://www.proglogic.com/code/java/game/rolldice.php.
 * Modified by Omer Shenhar and Shachar Butnaro.
 *
 */
public class Dice extends GameActionsListenableClass{
	private static final long serialVersionUID = 1L;
	private static Dice gameDice = new Dice();
	ButtonListener throwButton;
	JButton button;
	JLabel dice1;
	JLabel dice2;
	int dice1Outcome;
	int dice2Outcome;
	JLabel text;
	JPanel eastPane=new JPanel();
	JPanel westPane=new JPanel();
	
	/**
	 * private Dice() 
	 * a constructor for the dice pane of the game
	 * 
	 */
	private Dice() {
		dice1 = new JLabel(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"dice/"+"stone1.gif"));
		dice2 = new JLabel(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"dice/"+"stone1.gif"));
		button = new JButton("Throw");
		text = new JLabel("Total: 2");
		this.setLayout(new BorderLayout());
		westPane.add(dice1);
		eastPane.add(dice2);

		this.add(westPane,BorderLayout.WEST);
		this.add(eastPane,BorderLayout.EAST);
		this.add(button,BorderLayout.NORTH);
		this.add(text,BorderLayout.CENTER);
		throwButton = new ButtonListener(dice1, dice2, text);
		throwButton.addGameActionsListener(new GameActionEventListener() {

			@Override
			public void eventHappened(GameActionEvent gameActionEvent) {
				RollButtonPressed();
			}
		});
		button.addActionListener(throwButton);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setVisible(true);
	}
	
	/**
	 * private void RollButtonPressed()
	 * disables the throw button and informs all listening classes of the throw
	 */
	private void RollButtonPressed(){
		button.setEnabled(false);
		fireEvent("throwDie");
	}
	
	/**
	 * public void makeItRoll()
	 * clicks the roll die button 
	 */
	public void makeItRoll()
	{
		button.doClick();
	}
	
	/**
	 * public void resetDiceButtonAndLisners() 
	 * makes the throw button enabled and dumps all listeners
	 * 
	 */
	public void resetDiceButtonAndLisners() {
		button.setEnabled(true);
		removeAllListeners();//this is done to avoid dead listeners
	}
	
	/**
	 * public static Dice getGameDice()
	 * a getter for the gameDice
	 * @return the singleton game dice
	 */
	public static Dice getGameDice() {
		return gameDice;
	}

	/**	
	 * public int[] getDieOutcome()
	 * @return an array of the die outcomes
	 */
	public int[] getDieOutcome()
	{
		int[] results = {dice1Outcome,dice2Outcome};
		return results;
	}

	/**
	 * void setDieOutcome(int dice1Roll, int dice2Roll)
	 * sets the die outcome
	 * @param dice1Roll - an int
	 * @param dice2Roll- an int
	 */
	public void setDieOutcome(int dice1Roll, int dice2Roll)
	{
		dice1Outcome = dice1Roll;
		dice2Outcome = dice2Roll;
	}

	/**	
	 * public void setButtonEnabled(boolean value)
	 * @param value a boolean indicating if the throw button should be enabled
	 */
	public void setButtonEnabled(boolean value)
	{//Will activate the button IFF value==true.
		button.setEnabled(value);
	}

}
