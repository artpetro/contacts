package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ContactsTableModel;
import view.ContactsTable;
import view.MainView;

public class NewContactButtonListener implements ActionListener {

	private MainView mainView;
	
	public NewContactButtonListener(MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		ContactsTable table = this.mainView.getTable();
		((ContactsTableModel) table.getModel()).addRow(table.getSelectedRow() + table.getSelectedRowCount());
		
	}
}
