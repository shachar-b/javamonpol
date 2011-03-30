package ui.guiComponents.Squares;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import squares.Square;

public class ImageOnlySquarePanel extends SquarePanel {
	private static final long serialVersionUID = 1L;

	public ImageOnlySquarePanel(Square representedSquare, ImageIcon imageIcon) {
		super(representedSquare);
		JLabel imageLabel = new JLabel(imageIcon);
		//ImagePanel imageLabel = new ImagePanel(imageIcon);
		this.add(imageLabel, BorderLayout.CENTER);
	}
	
}
