package view;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class PhoneCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel = new JPanel();
	private JTextArea textArea = new JTextArea(3, 20);
	
	public PhoneCell() {
		
		panel.add(new JScrollPane(textArea));
		
	}

	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
		      boolean isSelected, boolean hasFocus, int row, int column) {
		
	    updateData((String)value, isSelected, table);
	    
	    return this.panel;
	
	}

	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
		      boolean isSelected, int row, int column) {

		updateData((String)value, true, table);
		
	    return this.panel;

	}
	
	
	private void updateData(String value, boolean isSelected, JTable table) {
		
		if (value != null) {
			
			String str = String.format("%s\n", value);
			
			this.textArea.setText(str);
			
    	}
		
		if (isSelected) {
			panel.setBackground(table.getSelectionBackground());
		}else{
			panel.setBackground(table.getBackground());
		}	
	}
	

	@Override
	public Object getCellEditorValue() {
		return this.textArea.getText();
	}
}
