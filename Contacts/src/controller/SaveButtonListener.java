package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.JsonIO;
import view.MainView;

public class SaveButtonListener implements ActionListener {

	private MainView mainView;
	
	public SaveButtonListener(MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			mainView.getTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		JsonIO.saveAs(this.mainView);
		
	}
}
