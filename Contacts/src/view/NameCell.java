package view;

import java.awt.Component;

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
	private JTextField position = new JTextField(20);
	
	public NameCell() {
		
		panel.add(name);
		panel.add(position);
		
	}

	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
		      boolean isSelected, boolean hasFocus, int row, int column) {
		
	    updateData((String[])value, isSelected, table);
	    
	    return this.panel;
	
	}

	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
		      boolean isSelected, int row, int column) {

		updateData((String[])value, true, table);
		
	    return this.panel;

	}
	
	
	private void updateData(String[] value, boolean isSelected, JTable table) {
		
		if (value != null) {
	    	
    		this.name.setText(value[0]);
    		this.position.setText(value[1]);
    
    	}
		
		if (isSelected) {
			panel.setBackground(table.getSelectionBackground());
		}else{
			panel.setBackground(table.getBackground());
		}	
	}
	

	@Override
	public Object getCellEditorValue() {
		
		String[] value = new String[2];
		
		value[0] = this.name.getText();
		value[1] = this.position.getText();
		
		return value;
	}
}
