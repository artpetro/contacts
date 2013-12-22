package view;

import javax.swing.DropMode;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;


import model.ContactsTableModel;

public class ContactsTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String[] colNames = {"Name", "Telefon", "Email"};

	public ContactsTable() {
		
		super();
		
		this.setModel(new ContactsTableModel());
		this.setDragEnabled(true);
		this.setDropMode(DropMode.INSERT_ROWS);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setTransferHandler(new TableRowTransferHandler(this)); 
		this.setRowHeight(60);
		
		TableColumn col = this.getColumnModel().getColumn(0);
		col.setCellEditor(new NameCell());
		col.setCellRenderer(new NameCell());
		col.setHeaderValue(colNames[0]);
		
		for (int i = 1; i < 3; i++) {
			col = this.getColumnModel().getColumn(i);
			col.setCellEditor(new PhoneCell());
			col.setCellRenderer(new PhoneCell());
			col.setHeaderValue(colNames[i]);
		}
	}
}
