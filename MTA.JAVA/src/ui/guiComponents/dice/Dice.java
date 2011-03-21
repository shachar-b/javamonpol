package ui.guiComponents.dice;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dice {
    private IconGetter getter;

    public Dice(){
        getter = new IconGetter();
    }

    public void start() {        
        JLabel dice1 = new JLabel(getter.getIcon("stone1.gif"));
        JLabel dice2 = new JLabel(getter.getIcon("stone1.gif"));
        JButton button = new JButton("Throw");
        JLabel text = new JLabel("Total: 2");
        JFrame window = new JFrame("Dice Thrower");
        Container cp = window.getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(dice1);
        cp.add(dice2);
        cp.add(button);
        cp.add(text);
        
        button.addActionListener(new ButtonListener(dice1, dice2, text));
        
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
