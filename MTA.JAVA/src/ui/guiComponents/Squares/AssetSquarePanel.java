package ui.guiComponents.Squares;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import assets.Asset;

import monopoly.GameManager;

public class AssetSquarePanel extends SquarePanel {
	
	JTable AssetInformation = new JTable(0, 2);

	public AssetSquarePanel(Asset representedAsset) {
		super(representedAsset);
		titleLabel.setText(representedAsset.getGroup().getName()+" : "+titleLabel.getText());
		initTable(representedAsset);
	}

	private void initTable(Asset representedAsset){
		String [] columnNames ={"Key","Value"};
		
		String[][] data = {
				{"Owner:", (representedAsset.getOwner()==GameManager.assetKeeper)?"NONE":representedAsset.getOwner().getName()},
				{"Current rent price:", ""+representedAsset.getRentPrice()}
		};
		AssetInformation = new JTable(data,columnNames);
	}

	private static final long serialVersionUID = 1L;

}
