/**
 * 
 */
package ui.utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author The WWW
 * Based on : http://stackoverflow.com/questions/4061908/how-to-maximize-image-size-as-the-size-of-the-jpanel
 */
public class ImagePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Image image = null; 
	 
    public ImagePanel(ImageIcon imgIcn) { 
        super();
        this.setImage(imgIcn);
     
    } 
 
    public void setImage(ImageIcon icon) { 
    	this.setPreferredSize(new Dimension(50, 50));
    	this.setMinimumSize(new Dimension(50, 50));
    	this.validate();
    	this.repaint();
    	image = icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
        repaint(); 
    } 
 
    @Override 
    public void paintComponent(Graphics g) { 
        super.paintComponent(g); 
        if (image != null) { 
            g.drawImage(image,0,0,getWidth(),getHeight(),null); 
        } 
    } 	
}
