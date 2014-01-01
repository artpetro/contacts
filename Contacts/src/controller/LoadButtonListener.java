package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.ContactsTableModel;
import view.ContactsTable;
import view.JsonIO;
import view.MainView;

public class LoadButtonListener implements ActionListener {

	private MainView mainView;
	
	public LoadButtonListener(MainView mainView) {
		this.mainView = mainView;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		List[] data = JsonIO.load(this.mainView.getDirPath(), this.mainView);
		ContactsTable table = (ContactsTable) this.mainView.getTable();
		
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		ContactsTableModel model = (ContactsTableModel) table.getModel();
		
		model.setData(data);
		
	}
}
