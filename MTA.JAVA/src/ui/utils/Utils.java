package ui.utils;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Utils {
    public static ImageIcon getImageIcon (String name) {
        Image image;
		try {
			image = Toolkit.getDefaultToolkit().createImage(name);
		} catch (SecurityException e) {
			throw new RuntimeException("Could not access resource "+name);
		} 
        if (image == null) {
        	return null;
        }
    	ImageIcon result =new ImageIcon(image);
        return result;
    }

}
