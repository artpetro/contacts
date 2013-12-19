package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.prefs.Preferences;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	
//	private ButtonsPanel buttonsPanel;
	
	private String dirPath;
	
	
	public MainView() {

		Preferences user = Preferences.userRoot();
		
		this.dirPath = user.get("timePlanerDirPath", System.getProperty("user.home"));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final Dimension d = this.getToolkit().getScreenSize();
//		this.setSize((int)Math.min(1800, d.getWidth()), (int)Math.min(800, d.getHeight()));
		this.setSize(1000, 800);
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2), (int) ((d.getHeight() - this.getHeight()) / 2));
//	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
		
//		// buttons panel
//		this.buttonsPanel = new ButtonsPanel(this);
//		this.daysPanel.add(buttonsPanel);
		
		// main panel
		this.mainPanel = new JPanel(new BorderLayout());
		this.mainPanel.add(new JScrollPane(null));
		
		this.add(this.mainPanel);
		this.setVisible(true);
		
	}
	
	public String getDirPath() {
		return this.dirPath;
	}
	
	public void setDirPath(String path) {
		this.dirPath = path;

		Preferences user = Preferences.userRoot();
		user.put("contactsDirPath", path);
		
	}
}
