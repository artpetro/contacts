package model;

import java.util.ArrayList;
import java.util.LinkedList;
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
	
	private List<List<String>> namesList;
	private List<String> phoneNumbersList;
	private List<String> emailsList;
	
	private String title;


	public ContactsTableModel() {
		setData(null);	
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void setValueAt(Object val, int row, int column) {
		
		if (column == 0) {
			
			ArrayList<String> list = (ArrayList<String>)val;
			this.namesList.set(row, list);
		
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
		
		List<String> name = this.namesList.get(fromIndex);
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
	
	public void addRow(int index) {
		
		if (index == -1) {
			index = this.namesList.size();
		}
		
		List<String> names = new ArrayList<String>();
		names.add("");
		names.add("");
		
		this.namesList.add(index, names);
		this.phoneNumbersList.add(index, "");
		this.emailsList.add(index, "");
		
		this.fireTableRowsInserted(index, index);
		
	}
	
	public void removeRow(int index, int count) {
		
		if (index != -1 && this.getRowCount() - count >= 10) {
			
			this.namesList.remove(index);
			this.phoneNumbersList.remove(index);
			this.emailsList.remove(index);
			
			this.fireTableRowsDeleted(index, index);	
		}	
	}
	
	@SuppressWarnings("rawtypes")
	public List[] getData() {
		
		List[] data = new List[3];

		data[0] = this.namesList;
		data[1] = this.phoneNumbersList;
		data[2] = this.emailsList;
		
		return data;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setData(List[] data) {

		if (data == null) {
			this.namesList = new LinkedList<List<String>>();
			this.phoneNumbersList = new LinkedList<String>();
			this.emailsList = new LinkedList<String>();
			
			for (int i = 0; i < 10; i++) {
				ArrayList<String> names = new ArrayList<String>();
				names.add("");// + i + " Name");
				names.add("");// + i + " Position");
				this.namesList.add(names);
				this.phoneNumbersList.add("");// + i + " 12345\n22345\n32345\n42345");
				this.emailsList.add("");// + i + " 12345@dd.dd\n22345@dd.dd\n32345@dd.dd\n42345@dd.dd");
			}
		}
		else {
			
			this.namesList = data[0];
			this.phoneNumbersList = data[1];
			this.emailsList = data[2];
			
			this.fireTableDataChanged();
		}
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}