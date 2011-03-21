package ui.guiComponents.dice;

import java.util.Random;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.JLabel;

class ThrowDice extends TimerTask {
    private JLabel dice1;
    private JLabel dice2;
    private JLabel text;
    private Random rg = new Random();
    private IconGetter getter;
    private int count;
    public final static int NUM_OF_THROWS = 20;

    public ThrowDice(JLabel dice1, JLabel dice2, JLabel text) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.text = text;
        count = NUM_OF_THROWS;
        getter = new IconGetter();
    }
    public void run(){
        if(count > 0){
            count --;
            int num1 = rg.nextInt(6);
            int num2 = rg.nextInt(6);
            Icon icon1 = getter.getIcon("stone" + (num1+1) + ".gif");
            Icon icon2 = getter.getIcon("stone" + (num2+1) + ".gif");
            dice1.setIcon(icon1);
            dice2.setIcon(icon2);
            text.setText("Total: " + (num1+num2+2));
        }
        else{
            this.cancel();
        }
    }

}
