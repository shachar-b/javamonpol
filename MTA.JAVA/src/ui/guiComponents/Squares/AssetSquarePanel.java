package ui.guiComponents.Squares;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import assets.Asset;
import assets.City;
import assets.UtilOrTranspoAsset;
import assets.UtilOrTranspoAssetGroup;

import monopoly.GameManager;

public class AssetSquarePanel extends SquarePanel {
	

	private DefaultTableModel AssetInformationModel;
	private JTable AssetInformation;
	private Asset representedAsset;
	public AssetSquarePanel(Asset representedAsset) {
		super(representedAsset);
		this.representedAsset=representedAsset;
		//to disallow editing
		AssetInformationModel= new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		    }
		};
		AssetInformation=new JTable(AssetInformationModel);
		titleLabel.setText(representedAsset.getGroup().getName()+" : "+titleLabel.getText());
		initTable();
		this.add(AssetInformation,BorderLayout.CENTER);
		this.validate();
		this.repaint();
		
	}

	private void initTable(){
			AssetInformation.setCellSelectionEnabled(false);
			AssetInformation.setColumnSelectionAllowed(false);
			AssetInformationModel.addColumn("what");
			AssetInformationModel.addColumn("value");

			AssetInformationModel.addRow(new String[]{"Market Price:",""+representedAsset.getCost()});
			AssetInformationModel.addRow(new String[]{"Current Rent Price:",""+
					((representedAsset.getOwner()==GameManager.assetKeeper)? 0 :representedAsset.getRentPrice())});
			if (representedAsset instanceof City) {
				int[] prices=((City) representedAsset).getPrices();
				AssetInformationModel.addRow(new String[]{"Base rent Price :",""+representedAsset.getRentPrice()});
				for (int i = 1; i < prices.length; i++) {
					AssetInformationModel.addRow(new String[]{"Rent Price with "+i+" houses:",""+prices[i]});

				}
				
			}
			else if(representedAsset instanceof UtilOrTranspoAsset) 
			{
				UtilOrTranspoAsset temp=(UtilOrTranspoAsset)representedAsset;
				UtilOrTranspoAssetGroup tempGroup=((UtilOrTranspoAssetGroup)temp.getGroup());
				AssetInformationModel.addRow(new String[]{"Rent for entire group:",""+tempGroup.getFullRental()});
				
			}
	}
	
	

	
	private static final long serialVersionUID = 1L;

}
