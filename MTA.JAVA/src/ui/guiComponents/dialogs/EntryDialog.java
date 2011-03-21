package ui.guiComponents.dialogs;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import monopoly.GameManager;

public class EntryDialog extends JDialog {

	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JSlider jSlider = null;
	/**
	 * This method initializes 
	 * 
	 */
	public EntryDialog() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(418, 218));
        this.setContentPane(getJPanel());
        this.setTitle("Welcome to Monopoly");
			
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setText("Select number of players:");
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
			jPanel.add(jLabel, null);
			jPanel.add(getJSlider(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJSlider() {
		if (jSlider == null) {
			jSlider = new JSlider();
			jSlider.setMajorTickSpacing(1);
			jSlider.setValue(4);
			jSlider.setToolTipText("Blah blah");
			jSlider.setMinimum(GameManager.MIN_NUMBER_OF_PLAYERS);
			jSlider.setMaximum(GameManager.MAX_NUMBER_OF_PLAYERS);
		}
		return jSlider;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
