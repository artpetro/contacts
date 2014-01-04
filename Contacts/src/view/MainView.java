package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;



public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	private JPanel headerPanel;
	private JPanel tablePanel;
	
	private ButtonsPanel buttonsPanel;
	private ContactsTable table;
	
	private String dirPath;
	private String fileName = "neue Datei";
	
	private final String TITLE = "Kontakte V. 1.0 - RS Gastronomie GmbH & Co.KG, Herford";
	
	private final String dirProperty = "contactsDirPath";
	
	
	public MainView() {

		Preferences user = Preferences.userRoot();
		
		this.dirPath = user.get(this.dirProperty, System.getProperty("user.home"));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle(TITLE);
		
		this.setFileName(this.fileName);
		
		final Dimension d = this.getToolkit().getScreenSize();
//		this.setSize((int)Math.min(1800, d.getWidth()), (int)Math.min(800, d.getHeight()));
		this.setSize(1000, 750);
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2), (int) ((d.getHeight() - this.getHeight()) / 2));
//	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
		
//		// header panel
//		headerPanel = new HeaderPanel();
//		
//		// contacts table
//		table = new ContactsTable();
//		
//		// buttons panel
//		buttonsPanel = new ButtonsPanel(this);
		
//		// main panel
//		mainPanel = new JPanel(new BorderLayout());
//		mainPanel.setBorder(new TitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY ), "Kontakte"));
//		
//		JScrollPane tablePane = new JScrollPane(table);
//		tablePane.setPreferredSize(new Dimension(1000, 620));
//		
//		mainPanel.add(tablePane, BorderLayout.PAGE_START);
//		mainPanel.add(buttonsPanel);
		
		// main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		
		// header panel
		headerPanel = new HeaderPanel();
				
		// table
		tablePanel = new JPanel();
		tablePanel.setBorder(new TitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY ), "Kontakte"));
		
		table = new ContactsTable();
		
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setPreferredSize(new Dimension(1000, 620));
				
		tablePanel.add(tablePane, BorderLayout.PAGE_START);
		
		// buttons panel
		buttonsPanel = new ButtonsPanel(this);
		
		mainPanel.add(headerPanel);
		mainPanel.add(tablePanel);
		mainPanel.add(buttonsPanel);
		
		this.add(mainPanel);
		
		this.pack();
		this.setVisible(true);
		
	}
	
	public void setFileName(String name) {
		this.fileName = name;
	}
	
	public String getFileName() {
		return this.fileName;
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
		user.put(this.dirProperty, path);
		
	}
}
