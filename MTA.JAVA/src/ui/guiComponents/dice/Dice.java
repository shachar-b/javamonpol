package ui.guiComponents.dice;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
	ActionListener cheatListner= new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			setDieOutcome(((Integer)diceA.getValue()), ((Integer)diceB.getValue()));
			text.setText("Total: " + (dice1Outcome+dice2Outcome));
			RollButtonPressed();
		}
	};
	JButton button;
	JSpinner diceA;
	JSpinner diceB;
	JLabel dice1;
	JLabel dice2;
	JCheckBox isCheatMode;
	int dice1Outcome;
	int dice2Outcome;
	JLabel text;
	JPanel eastPane=new JPanel();
	JPanel westPane=new JPanel();
	private Dice() {
		dice1 = new JLabel(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"dice/"+"stone1.gif"));
		dice2 = new JLabel(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"dice/"+"stone1.gif"));
		diceA=new JSpinner(new SpinnerNumberModel(1, 0, 18, 1));
		diceA.setVisible(false);
		diceB=new JSpinner(new SpinnerNumberModel(1, 0, 18, 1));
		diceB.setVisible(false);
		button = new JButton("Throw");
		text = new JLabel("Total: 2");
		this.setLayout(new BorderLayout());
		westPane.add(dice1);
		eastPane.add(dice2);
		westPane.add(diceA);
		eastPane.add(diceB);

		this.add(westPane,BorderLayout.WEST);
		this.add(eastPane,BorderLayout.EAST);
		this.add(button,BorderLayout.NORTH);
		this.add(text,BorderLayout.CENTER);
		isCheatMode=new JCheckBox("enable cheats");
		this.add(isCheatMode,BorderLayout.SOUTH);
		isCheatMode.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				revertState();

			}
		});
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
	private void RollButtonPressed(){
		button.setEnabled(false);
		fireEvent("throwDie");
	}
	public void makeItRoll()
	{
		button.doClick();
	}
	public void resetDiceButtonAndLisners() {
		button.setEnabled(true);
		removeAllListeners();//this is done to avoid dead listeners
	}
	private void revertState() {
		if(isCheatMode.isSelected())
		{
			dice1.setVisible(false);
			diceA.setVisible(true);
			dice2.setVisible(false);
			diceB.setVisible(true);
			button.removeActionListener(throwButton);
			button.addActionListener(cheatListner);

		}
		else
		{
			dice1.setVisible(true);
			diceA.setVisible(false);
			dice2.setVisible(true);
			diceB.setVisible(false);
			button.removeActionListener(cheatListner);
			button.addActionListener(throwButton);
		}

	}
	public static Dice getGameDice() {
		return gameDice;
	}

	public int[] getDieOutcome()
	{
		int[] results = {dice1Outcome,dice2Outcome};
		return results;
	}

	void setDieOutcome(int dice1Roll, int dice2Roll)
	{
		dice1Outcome = dice1Roll;
		dice2Outcome = dice2Roll;
	}

	public void setButtonEnabled(boolean value)
	{//Will activate the button IFF value==true.
		button.setEnabled(value);
	}

}
