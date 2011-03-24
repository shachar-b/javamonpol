/*
 * Created by JFormDesigner on Wed Mar 23 12:33:14 IST 2011
 */

package ui.guiComponents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ui.guiComponents.dialogs.EntryDialog;
import ui.guiComponents.dialogs.ExitDiaglog;

import com.jgoodies.forms.factories.Borders;

/**
 * @author Shachar
 */
public class MainWindow extends JFrame {
	public MainWindow() {
		initComponents();
	}

	private void menuItem1ActionPerformed(ActionEvent e) {
		JDialog aboutDialog = new ui.guiComponents.dialogs.aboutDialog(this);
		aboutDialog.setVisible(true);
	}

	private void menu1ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void menuItem1MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void NewGameActionPerformed(ActionEvent e) {
		JDialog newGameDialog = new EntryDialog(this);
		newGameDialog.setVisible(true);
	}

	private void ExitActionPerformed(ActionEvent e) {
		JDialog exitGameDialog = new ExitDiaglog(this);
		exitGameDialog.setVisible(true);
	}

	private void thisWindowClosing(WindowEvent e) {
		JDialog exitGameDialog = new ExitDiaglog(this);
		exitGameDialog.setVisible(true);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		FileMenu = new JMenu();
		NewGame = new JMenuItem();
		Exit = new JMenuItem();
		HelpMenu = new JMenu();
		About = new JMenuItem();
		dialogPane = new JPanel();
		contentPanel = new JPanel();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				thisWindowClosing(e);
			}
		});
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== menuBar1 ========
		{

			//======== FileMenu ========
			{
				FileMenu.setText("File");
				FileMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						menu1ActionPerformed(e);
					}
				});

				//---- NewGame ----
				NewGame.setText("New Game");
				NewGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewGameActionPerformed(e);
					}
				});
				FileMenu.add(NewGame);

				//---- Exit ----
				Exit.setText("Exit");
				Exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ExitActionPerformed(e);
						ExitActionPerformed(e);
					}
				});
				FileMenu.add(Exit);
			}
			menuBar1.add(FileMenu);

			//======== HelpMenu ========
			{
				HelpMenu.setText("Help");

				//---- About ----
				About.setText("About");
				About.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()|KeyEvent.SHIFT_MASK));
				About.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						menuItem1ActionPerformed(e);
					}
				});
				About.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						menuItem1MouseClicked(e);
					}
				});
				HelpMenu.add(About);
			}
			menuBar1.add(HelpMenu);
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
	private JMenu FileMenu;
	private JMenuItem NewGame;
	private JMenuItem Exit;
	private JMenu HelpMenu;
	private JMenuItem About;
	private JPanel dialogPane;
	private JPanel contentPanel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
