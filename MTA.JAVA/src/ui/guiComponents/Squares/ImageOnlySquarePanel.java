package ui.guiComponents.Squares;

import java.awt.BorderLayout;

import squares.Square;
import ui.guiComponents.ImagePanel;

public class ImageOnlySquarePanel extends SquarePanel {
	private static final long serialVersionUID = 1L;

	public ImageOnlySquarePanel(Square representedSquare, String path) {
		super(representedSquare);
		//JLabel imageLabel = new JLabel(imageIcon);
		ImagePanel imageLabel = new ImagePanel(path);
		this.add(imageLabel, BorderLayout.CENTER);
	}
	
}
