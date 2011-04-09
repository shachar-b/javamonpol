package ui.utils;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TransparentTable extends JTable {

	private static final long serialVersionUID = 1L;
	public TransparentTable() {
		super();
		resetModel();
		setOpaque(false);
	}

	public void resetModel() {
		setModel(new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		});

	}
//
//	@Override
//	public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
//	{
//		Component c = super.prepareRenderer( renderer, row, column);
//		// We want renderer component to be transparent
//		// so background image is visible
//		if( c instanceof JComponent )
//			((JComponent)c).setOpaque(false);
//		return c;
//	}


}
