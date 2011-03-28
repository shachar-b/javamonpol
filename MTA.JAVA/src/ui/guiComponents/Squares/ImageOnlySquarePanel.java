package ui.guiComponents.Squares;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import squares.Square;

public class ImageOnlySquarePanel extends SquarePanel {
	private static final long serialVersionUID = 1L;

	public ImageOnlySquarePanel(Square representedSquare, ImageIcon imageIcon) {
		super(representedSquare);
		this.add(new JLabel(imageIcon), BorderLayout.CENTER);
	}
	
}
