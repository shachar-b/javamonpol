/*
 * Created by JFormDesigner on Wed Mar 23 12:33:14 IST 2011
 */

package ui.guiComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import players.Player;
import players.ComputerPlayer;

import ui.guiComponents.dialogs.EntryDialog;
import ui.guiComponents.dialogs.ExitDiaglog;

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
		//splitPane2.setTopComponent(new PlayerPanel(new ComputerPlayer()));
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
	
	public void addLineToConsole(String message)
	{
		textualConsole.append(message+"\n");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		FileMenu = new JMenu();
		NewGame = new JMenuItem();
		Exit = new JMenuItem();
		HelpMenu = new JMenu();
		About = new JMenuItem();
		splitPane1 = new JSplitPane();
		gameBoardUI1 = new GameBoardUI();
		splitPane2 = new JSplitPane();
		scrollPane1 = new JScrollPane();
		textualConsole = new JTextArea();

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
				NewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK|KeyEvent.SHIFT_MASK));
				NewGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewGameActionPerformed(e);
					}
				});
				FileMenu.add(NewGame);

				//---- Exit ----
				Exit.setText("Exit");
				Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK|KeyEvent.ALT_MASK));
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

		//======== splitPane1 ========
		{
			splitPane1.setResizeWeight(0.5);
			splitPane1.setLeftComponent(gameBoardUI1);

			//======== splitPane2 ========
			{
				splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);

				//======== scrollPane1 ========
				{

					//---- textualConsole ----
					textualConsole.setBackground(new Color(240, 240, 240));
					textualConsole.setEditable(false);
					textualConsole.setLineWrap(true);
					textualConsole.setText("Game information console:\n");
					scrollPane1.setViewportView(textualConsole);
				}
				splitPane2.setBottomComponent(scrollPane1);
			}
			splitPane1.setRightComponent(splitPane2);
		}
		contentPane.add(splitPane1, BorderLayout.CENTER);
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
	private JSplitPane splitPane1;
	private GameBoardUI gameBoardUI1;
	private JSplitPane splitPane2;
	private JScrollPane scrollPane1;
	private JTextArea textualConsole;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public void movePlayer(Player p) {
		gameBoardUI1.movePlayer(p, p.getLastKnownPosition(), p.getCurrentPosition());
		
	}
	public GameBoardUI getGameboard()
	{
		return gameBoardUI1;
	}
	
}
