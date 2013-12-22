package view;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class NameCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel = new JPanel();
	private JTextField name = new JTextField(20);
	private JTextField position = new JTextField(27);
	
	public NameCell() {
		
		Font font = new Font("Default", Font.PLAIN, 9);
		
		position.setFont(font);
		panel.add(name);
		panel.add(position);
		
	}

	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
		      boolean isSelected, boolean hasFocus, int row, int column) {
		
	    updateData(value, isSelected, table);
	    
	    return this.panel;
	
	}

	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
		      boolean isSelected, int row, int column) {

		updateData(value, true, table);
		
	    return this.panel;

	}
	
	
	@Override
	public Object getCellEditorValue() {
//		System.out.println("namecell:get editor value");
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add(this.name.getText());
		list.add(this.position.getText());
		
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	private void updateData(Object value, boolean isSelected, JTable table) {
		
		ArrayList<String> list = (ArrayList<String>) value;
	    
		if (list.size() == 2) {
	    	
    		this.name.setText(list.get(0));
    		this.position.setText(list.get(1));
    
    	}
//		System.out.println("namecell:update data");

		
		if (isSelected) {
			panel.setBackground(table.getSelectionBackground());
		}else{
			panel.setBackground(table.getBackground());
		}	
	}	
}
