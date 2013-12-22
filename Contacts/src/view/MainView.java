package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	private ButtonsPanel buttonsPanel;
	private ContactsTable table;
	
	private String dirPath;
	
	
	public MainView() {

		Preferences user = Preferences.userRoot();
		
		this.dirPath = user.get("timePlanerDirPath", System.getProperty("user.home"));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setFileNameInTitle("neue Datei");
		
		final Dimension d = this.getToolkit().getScreenSize();
//		this.setSize((int)Math.min(1800, d.getWidth()), (int)Math.min(800, d.getHeight()));
		this.setSize(1000, 750);
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2), (int) ((d.getHeight() - this.getHeight()) / 2));
//	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
		
		table = new ContactsTable();
		
		// buttons panel
		buttonsPanel = new ButtonsPanel(this);
		
		// main panel
		mainPanel = new JPanel(new BorderLayout());
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setPreferredSize(new Dimension(1000, 620));
		mainPanel.add(tablePane, BorderLayout.PAGE_START);
		mainPanel.add(buttonsPanel);
		
		this.add(mainPanel);
		this.setVisible(true);
		
	}
	
	public void setFileNameInTitle(String name) {

		
		this.setTitle(String.format("Kontaktdaten - %s - V. 1.0.betha", name));
	}
	
	public ContactsTable getTable() {
		return this.table;
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
