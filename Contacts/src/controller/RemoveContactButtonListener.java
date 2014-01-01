package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ContactsTableModel;
import view.ContactsTable;
import view.MainView;

public class RemoveContactButtonListener implements ActionListener {

	private MainView mainView;
	
	public RemoveContactButtonListener(MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		ContactsTable table = this.mainView.getTable();
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		((ContactsTableModel) table.getModel()).removeRow(table.getSelectedRow(), table.getSelectedRowCount());	
	}
}
