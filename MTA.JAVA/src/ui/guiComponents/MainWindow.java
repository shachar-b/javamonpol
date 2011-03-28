/*
 * Created by JFormDesigner on Wed Mar 23 12:33:14 IST 2011
 */

package ui.guiComponents;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import monopoly.GameManager;
import ui.guiComponents.Squares.AssetSquarePanel;
import ui.guiComponents.Squares.SquarePanel;
import ui.guiComponents.dialogs.EntryDialog;
import ui.guiComponents.dialogs.ExitDiaglog;
import ui.utils.Utils;
import assets.City;
import assets.Country;

/**
 * @author Shachar
 */
public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainWindow() {
		initComponents();
		int[] rentPrices = {1,2,3,4};
		SquarePanel blah = new AssetSquarePanel(new City(new Country("ABC"), "CITY", 100, 90, rentPrices));
		this.getContentPane().add(blah);
		blah.addPlayer(Utils.getImageIcon(GameManager.IMAGES_FOLDER+"playerIcons/Car.gif"));
	}

	private void menuItem1ActionPerformed(ActionEvent e) {
		JDialog aboutDialog = new ui.guiComponents.dialogs.aboutDialog(this);
		aboutDialog.setVisible(true);
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
				HelpMenu.add(About);
			}
			menuBar1.add(HelpMenu);
		}
		setJMenuBar(menuBar1);

		//======== contentPanel ========
		{
			contentPanel.setLayout(new FlowLayout());
		}
		contentPane.add(contentPanel, BorderLayout.NORTH);
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
	private JPanel contentPanel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
