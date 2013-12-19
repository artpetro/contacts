package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import view.NameCell;
import view.PhoneCell;

public class ContactsTableModel  extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int COLCOUNT = 3;
	
	private List<String[]> namesList;
	private List<String> phoneNumbersList;
	private List<String> emailsList;
	
	public ContactsTableModel(List<String[]> namesList, List<String> phoneNumbersList, List<String> emailsList) {
		
		this.namesList = namesList;
		this.phoneNumbersList = phoneNumbersList;
		this.emailsList = emailsList;
		
	}
	
	public Class<?> getColumnClass(int columnIndex) { 
	
		switch (columnIndex){
			case 0: return NameCell.class;
			case 1: case 2: return PhoneCell.class;
			default: return Object.class;
		}
	}
	
	@Override
	public int getColumnCount() {
		return COLCOUNT;
	}

	@Override
	public int getRowCount() {
		return (this.namesList.size() < 10) ? 10 : this.namesList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex){
			case 0: return this.namesList.get(rowIndex);
			case 1: return this.phoneNumbersList.get(rowIndex);
			case 2: return this.emailsList.get(rowIndex);
			default: return Object.class;
		
		}	
	}
	
	@Override
	public void setValueAt(Object val, int row, int column) {
		
		if (column == 0) {
			
			String[] str = (String[])val;
			this.namesList.get(row)[0] = str[0];
			this.namesList.get(row)[1] = str[1];
			
		}
		
		else if (column == 1) {
			this.phoneNumbersList.set(row, (String) val);
		}
			
		else if(column == 2) {
			this.emailsList.set(row, (String) val);
		}

		fireTableCellUpdated( row, column );
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
	
		return true;
	
	}
	
	public void reorder(int fromIndex, int toIndex) {
		
		if (fromIndex < toIndex) {
			toIndex--;
		}
		
		String[] name = this.namesList.get(fromIndex);
		String phones = this.phoneNumbersList.get(fromIndex);
		String emails = this.emailsList.get(fromIndex);
		
		this.namesList.remove(fromIndex);
		this.phoneNumbersList.remove(fromIndex);
		this.emailsList.remove(fromIndex);
		
		this.namesList.add(toIndex, name);
		this.phoneNumbersList.add(toIndex, phones);
		this.emailsList.add(toIndex, emails);
		
		this.fireTableDataChanged();
	}

}
