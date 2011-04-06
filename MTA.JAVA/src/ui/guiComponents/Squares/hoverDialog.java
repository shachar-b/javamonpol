package ui.guiComponents.Squares;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class hoverDialog extends JDialog {

	JPanel dataArea;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AssetSquarePanel owner;
	public hoverDialog(AssetSquarePanel owner) {
		this.owner=owner;
		this.setLayout(new BorderLayout());
		this.setUndecorated(true);
		dataArea=new JPanel(new BorderLayout());
		JLabel exit=new JLabel("click to exit");
		exit.setOpaque(true);
		exit.setBackground(Color.RED);
		dataArea.add(exit,BorderLayout.SOUTH);
		this.add(dataArea,BorderLayout.CENTER);
		dataArea.add(new AssetSquarePanel(owner.representedAsset, false),BorderLayout.CENTER);
		this.setLocation(owner.getLocation());
		this.setMinimumSize(owner.getSize());
		this.pack();
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				exitHover();
			}

			
		});
		owner.AssetInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				exitHover();
			}

			
		});
	}
	private void exitHover() {
		this.dispose();
		owner.invalidate();
		owner.repaint();
		
	}	

}
