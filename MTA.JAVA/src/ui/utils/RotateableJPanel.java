package ui.utils;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RotateableJPanel extends JPanel {
	
	public enum Diractions{
			NORTH(0.0),SOUTH(180.0),EAST(90.0),WEST(270.0);
			
			
			private double diracions;
			Diractions(double d)
			{
				diracions=d;
			}
			
			/**
			 * @return the diracions
			 */
			public double getDiracions() {
				return diracions;
			}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double degree;
  public RotateableJPanel(Diractions drawDiraction) {
	  degree=drawDiraction.getDiracions();
	  this.add(new JLabel("whooo"));;
  }
  
  
  public void paintComponent(Graphics graphics) {
    Graphics2D g = (Graphics2D) graphics;
    int x = this.getWidth()/2;
    int y = this.getHeight()/2;
    g.rotate(Math.toRadians(degree), x, y);
    super.paintComponent(g);
  }
}