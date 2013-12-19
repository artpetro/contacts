package view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.DropMode;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import model.ContactsTableModel;

public class ContactsTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String[] colNames = {"Name", "Telefon", "Email"};

	public ContactsTable(ContactsTableModel model) {
		
		super();
		
		if (model == null) {
		
			List<String[]> namesList = new LinkedList<String[]>();
			List<String> phonesList = new LinkedList<String>();
			List<String> emailsList = new LinkedList<String>();
			
			for (int i = 0; i < 10; i++) {
				namesList.add(new String[2]);
				phonesList.add("");
				emailsList.add("");
			}
			
			model = new ContactsTableModel(namesList, phonesList, emailsList);
			
		}
		
		this.setModel(model);
		this.setDragEnabled(true);
		this.setDropMode(DropMode.INSERT_ROWS);
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
	
//		col = this.getColumnModel().getColumn(3);
//		col.setCellEditor(new ButtonsCell());
//		col.setCellRenderer(new ButtonsCell());
//		col.setHeaderValue("");
//		
//		int width = 120;
//		col.setMinWidth(width);
//      col.setMaxWidth(width);
//      col.setPreferredWidth(width);

}
