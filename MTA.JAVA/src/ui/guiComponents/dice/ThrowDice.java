package ui.guiComponents.dice;

import java.util.Random;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.JLabel;

import listeners.gameActions.GameActionEvent;
import listeners.gameActions.GameActionEventListener;
import monopoly.GameManager;
import ui.utils.Utils;

class ThrowDice extends TimerTask {
    private JLabel dice1;
    private JLabel dice2;
    private JLabel text;
    private Random rg = new Random();
    private int count;
    int dice1Outcome;
    int dice2Outcome;
    GameActionEventListener preformWhenDone;
    public final static int NUM_OF_THROWS = 20;

    public ThrowDice(JLabel dice1, JLabel dice2, JLabel text,GameActionEventListener preformWhenDone) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.text = text;
        count = NUM_OF_THROWS;
        this.preformWhenDone=preformWhenDone;
    }
    public void run(){
        if(count > 0){
            count --;
            dice1Outcome = rg.nextInt(6)+1;
            dice2Outcome = rg.nextInt(6)+1;
            Icon icon1 = Utils.getImageIcon(GameManager.IMAGES_FOLDER+"dice/"+"stone" + (dice1Outcome) + ".gif");
            Icon icon2 = Utils.getImageIcon(GameManager.IMAGES_FOLDER+"dice/"+"stone" + (dice2Outcome) + ".gif");
            dice1.setIcon(icon1);
            dice1.revalidate();
            dice1.repaint();
            dice2.setIcon(icon2);
            dice2.revalidate();
            dice2.repaint();
            text.setText("Total: " + (dice1Outcome+dice2Outcome));
        }
        else{
            Dice.getGameDice().setDieOutcome(dice1Outcome,dice2Outcome);
            preformWhenDone.eventHappened(new GameActionEvent(this, "throwDie",GameManager.gameID));
            this.cancel();
        }
    }
}
