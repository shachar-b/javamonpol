package ui.guiComponents.Squares;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import monopoly.GameManager;
import InnerChangeListner.InnerChangeEventListner;
import InnerChangeListner.InnerChangeEvet;
import assets.Asset;
import assets.City;
import assets.UtilOrTranspoAsset;
import assets.UtilOrTranspoAssetGroup;

public class AssetSquarePanel extends SquarePanel {
	

	private DefaultTableModel AssetInformationModel;
	JTable AssetInformation;
	Asset representedAsset;
	
	public AssetSquarePanel(Asset representedAsset)
	{
		this(representedAsset,true);
	}
	public AssetSquarePanel(Asset representedAsset,boolean enableHoverMode) {
		super(representedAsset);
		this.representedAsset=representedAsset;
		//to disallow editing
		
		AssetInformation=new JTable();
		
		groupLabel.setEnabled(true);
		groupLabel.setText(representedAsset.getGroup().getName()+":");
		
		titleLabel.setText(titleLabel.getText());
		UpdateTable();
		if(enableHoverMode)
		{
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					makeHover();
				}
			});
			
		}
		else
		{
			this.add(AssetInformation,BorderLayout.CENTER);
		}
		representedAsset.addInnerChangeEventListner(new InnerChangeEventListner() {
			
			@Override
			public void eventHappened(InnerChangeEvet innerChangeEvet) {
				UpdateTable();
				
			}

			
		});
		
		this.validate();
		this.repaint();
		
	}
	private void makeHover() {
		
		hoverDialog hoverInfo= new hoverDialog(this);
		hoverInfo.setVisible(true);
		
		
	}

	public void UpdateTable(){
		
		AssetInformationModel= new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		    }
		};
		AssetInformation.setModel(AssetInformationModel);
			AssetInformation.setCellSelectionEnabled(false);
			AssetInformation.setColumnSelectionAllowed(false);
			AssetInformationModel.addColumn("what");
			AssetInformationModel.addColumn("value");
			
			boolean hasOwner=representedAsset.getOwner()!=GameManager.assetKeeper;
			
			AssetInformationModel.addRow(new String[]{"Current Owner:",""+
					((hasOwner)? representedAsset.getOwner().getName():"Tresury" )});
			AssetInformationModel.addRow(new String[]{"Market Price:",""+representedAsset.getCost()});
			AssetInformationModel.addRow(new String[]{"Current Rent Price:",""+
					((hasOwner)? representedAsset.getRentPrice():0)});
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
				AssetInformationModel.addRow(new String[]{"Rent for single Asset:",""+temp.getBasicRent()});
				AssetInformationModel.addRow(new String[]{"Rent for entire group:",""+tempGroup.getFullRental()});
				
			}
			AssetInformation.setFont(new Font("Tahoma", Font.PLAIN, 8));
			
	}
	
	
	
	
	private static final long serialVersionUID = 1L;

}
