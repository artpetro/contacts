package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ContactsTable;
import view.JsonIO;
import view.MainView;

public class LoadButtonListener implements ActionListener {

	private MainView mainView;
	
	public LoadButtonListener(MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		ContactsTable table = (ContactsTable) this.mainView.getTable();
		
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		JsonIO.load(this.mainView.getDirPath(), this.mainView);
				
	}
}
