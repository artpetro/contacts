package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import model.ContactsTableModel;

public class HeaderPanelListener implements DocumentListener {
	
	private ContactsTableModel model;

	public HeaderPanelListener(ContactsTableModel model) {
	
		this.model = model;
	
	}
	
	
	public void changedUpdate(DocumentEvent e) {
	
		changed(e);
	
	}

	
	public void removeUpdate(DocumentEvent e) {
	
		changed(e);
	
	}
		  
	
	public void insertUpdate(DocumentEvent e) {
	
		changed(e);
	
	}

	
	private void changed(DocumentEvent e) {
		
		Document doc = e.getDocument();
		
		try {
		
			this.model.setTitle(doc.getText(0, doc.getLength()));
		
		} catch (BadLocationException e1) {
	
			e1.printStackTrace();
		
		}
	}
}
