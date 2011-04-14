package ui.utils;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	private Image scaledImage;
	private int imageWidth = 0;
	private int imageHeight = 0;
	private String imagePath;
	//private long paintCount = 0;
	private boolean retainAspectRatio=false;

	public ImagePanel(String string) {
		this(string,false);
	}
	
	public ImagePanel(String string, boolean keepAspectRatio) {
		super();
		imagePath = string;
		retainAspectRatio = keepAspectRatio;
		try {
			loadImage(string);
		} catch (IOException e) {
			throw new RuntimeException("missing component:  A compunent failed to load an Image from "+string);
		}
		setOpaque(true);
	}

	public ImagePanel getCopy()
	{
		return new ImagePanel(imagePath);
	}

	public ImageIcon getIcon()
	{
		return (new ImageIcon(image));
	}

	public void loadImage(String file) throws IOException {
		image = ImageIO.read(new File(file));
		//might be a situation where image isn't fully loaded, and
		//  should check for that before setting...
		imageWidth = image.getWidth(this);
		imageHeight = image.getHeight(this);
		setScaledImage();
	}

	//e.g., containing frame might call this from formComponentResized
	public void scaleImage() {
		setScaledImage();
	}

	//override paintComponent
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if ( scaledImage != null ) {
			//System.out.println("ImagePanel paintComponent " + ++paintCount);
			scaleImage();
			g.drawImage(scaledImage, 0, 0, this);
		}
	}

	private void setScaledImage() {
		if ( image != null ) {

			//use floats so division below won't round
			float iw = imageWidth;
			float ih = imageHeight;
			float pw = this.getWidth();   //panel width
			float ph = this.getHeight();  //panel height

			/* compare some ratios and then decide which side of image to anchor to panel
                   and scale the other side
                   (this is all based on empirical observations and not at all grounded in theory)*/

			//System.out.println("pw/ph=" + pw/ph + ", iw/ih=" + iw/ih);
			if(retainAspectRatio)
			{
				if ( (pw / ph) > (iw / ih) ) { 
					iw = -1; 
					ih = ph; 
				} else { 
					iw = pw; 
					ih = -1; 
				} 
			}
			else
			{
				iw =pw;
				ih = ph;
			}

			//prevent errors if panel is 0 wide or high
			if (iw == 0) {
				iw = -1;
			}
			if (ih == 0) {
				ih = -1;
			}

			scaledImage = image.getScaledInstance(
					new Float(iw).intValue(), new Float(ih).intValue(), Image.SCALE_REPLICATE);

		} else {
			scaledImage = image;
		}

	}
	
	public void setRetainAspectRatio(boolean retain) {
		retainAspectRatio=retain;
	}


}