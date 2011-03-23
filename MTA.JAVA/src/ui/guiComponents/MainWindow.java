/*
 * Created by JFormDesigner on Wed Mar 23 12:33:14 IST 2011
 */

package ui.guiComponents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Shachar
 */
public class MainWindow extends JFrame {
	public MainWindow() {
		initComponents();
	}

	private void menu1ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menuItem2 = new JMenuItem();
		menuItem3 = new JMenuItem();
		menu2 = new JMenu();
		menuItem1 = new JMenuItem();
		dialogPane = new JPanel();
		contentPanel = new JPanel();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("File");
				menu1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						menu1ActionPerformed(e);
					}
				});

				//---- menuItem2 ----
				menuItem2.setText("New Game");
				menu1.add(menuItem2);

				//---- menuItem3 ----
				menuItem3.setText("Exit");
				menu1.add(menuItem3);
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText("Help");

				//---- menuItem1 ----
				menuItem1.setText("About");
				menu2.add(menuItem1);
			}
			menuBar1.add(menu2);
		}
		setJMenuBar(menuBar1);

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new FlowLayout());
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenu menu2;
	private JMenuItem menuItem1;
	private JPanel dialogPane;
	private JPanel contentPanel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
