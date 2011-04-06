package ui.guiComponents.dice;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import monopoly.GameManager;

import ui.utils.Utils;

public class Dice extends JLabel{
	private static final long serialVersionUID = 1L;
	private static Dice gameDice = new Dice();
	ButtonListener throwButton;
	int dice1Outcome;
	int dice2Outcome;

    private Dice(){
        JLabel dice1 = new JLabel(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"dice/"+"stone1.gif"));
        JLabel dice2 = new JLabel(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"dice/"+"stone1.gif"));
        JButton button = new JButton("Throw");
        JLabel text = new JLabel("Total: 2");
        this.setLayout(new BorderLayout());
        this.add(dice1,BorderLayout.WEST);
        this.add(dice2,BorderLayout.EAST);
        this.add(button,BorderLayout.NORTH);
        this.add(text,BorderLayout.SOUTH);
        throwButton = new ButtonListener(dice1, dice2, text);
        button.addActionListener(throwButton);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setVisible(true);
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
	
}
