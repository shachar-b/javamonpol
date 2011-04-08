package ui.utils;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class IconCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		JLabel label = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);   
		if (value instanceof ImagePanel)   
		{   
			ImagePanel imageCopy =((ImagePanel)value).getCopy();
			imageCopy.setRetainAspectRatio(true);
			return imageCopy;  
		}
		return label;
	}   


}
