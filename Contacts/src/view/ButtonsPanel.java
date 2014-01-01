package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.CloseButtonListener;
import controller.LoadButtonListener;
import controller.NewContactButtonListener;
import controller.PdfButtonListener;
import controller.RemoveContactButtonListener;
import controller.SaveButtonListener;



public class ButtonsPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField timeField;
	
	private JButton loadBtn;
	private JButton saveBtn;
	private JButton toPdfBtn;
	private JButton closeBtn;
	private JButton newBtn;
	private JButton removeBtn;
	
	
	public ButtonsPanel(MainView mainView) {
		
		this.setBorder(new TitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY ), "Aktionen"));
		
		this.loadBtn = new JButton("Laden");
		this.saveBtn = new JButton("Speichern");
		this.toPdfBtn = new JButton("Ausgeben");
		this.closeBtn = new JButton("Beenden");
		this.newBtn = new JButton("+");
		this.removeBtn = new JButton("-");
		
		this.saveBtn.addActionListener(new SaveButtonListener(mainView));
		this.loadBtn.addActionListener(new LoadButtonListener(mainView));
		this.closeBtn.addActionListener(new CloseButtonListener(mainView));
		this.toPdfBtn.addActionListener(new PdfButtonListener(mainView));
		this.newBtn.addActionListener(new NewContactButtonListener(mainView));
		this.removeBtn.addActionListener(new RemoveContactButtonListener(mainView));
		
		this.add(this.loadBtn);
		this.add(this.saveBtn);
		this.add(this.toPdfBtn);
		this.add(this.closeBtn);

		this.add(new JLabel("      "));
		this.add(this.newBtn);
		this.add(this.removeBtn);
	}
	
	public JTextField getTimeFeld() {
		return this.timeField;
	}
}