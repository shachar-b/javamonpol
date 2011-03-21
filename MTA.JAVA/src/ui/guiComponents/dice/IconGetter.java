package ui.guiComponents.dice;

import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconGetter {
	
	private String imagePath = "res/images/dice/";
    public Icon getIcon(String name){
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(imagePath+name));
    }
   }
