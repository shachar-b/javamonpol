package ui.guiComponents.dice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import javax.swing.JLabel;

class ButtonListener implements ActionListener {
    
    private JLabel dice1;
    private JLabel dice2;
    private JLabel text;

    public ButtonListener(JLabel dice1, JLabel dice2, JLabel text) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.text = text;
    }

    public void actionPerformed(ActionEvent e) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new ThrowDice(dice1, dice2, text), 0, 100);
    }
}
