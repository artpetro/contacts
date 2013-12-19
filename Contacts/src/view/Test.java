package view;

import java.awt.*;  
import java.util.EventObject;  
import javax.swing.*;  
import javax.swing.event.*;  
import javax.swing.table.*;  
   
public class Test  
{  
    public Test()  
    {  
        JTable table = getTable();  
        TableColumnModel colModel = table.getColumnModel();  
        TableColumn col = colModel.getColumn(1);  
        col.setCellRenderer(new CustomRenderer());  
        col.setCellEditor(new CustomEditor());  
        table.setRowHeight(50);  
        table.setPreferredScrollableViewportSize(table.getPreferredSize());  
        JFrame f = new JFrame();  
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        f.getContentPane().add(new JScrollPane(table));  
        f.setSize(400,400);  
        f.setLocation(200,200);  
        f.setVisible(true);  
    }  
   
    private JTable getTable()  
    {  
        String[] tournies = {  
            "tournament 1\ntournament 2\n", "tournament 1\n",  
            "tournament 1\ntounrament 2\ntournament 3\ntournament 4\n",  
            "tournament 1\ntounrament 2\ntournament 3\ntournament 4\n" +  
            "tournament 5\ntounrament 6\ntournament 7\ntournament 8\n" +  
            "tournament 9\ntounrament 10\ntournament 11\ntournament 12\n"  
        };  
        String[] colNames = { "city", "tournaments", "sea" };  
        Object[][] data = {  
            { "San Diego", tournies[0], "Pacific" },  
            { "Cape Cod",  tournies[1], "Atlantic" },  
            { "Sarasota",  tournies[2], "Gulf" },  
            { "Seattle",   tournies[3], "Pacific" }  
        };  
        DefaultTableModel model = new DefaultTableModel(data, colNames);  
        return new JTable(model);  
    }  
   
    public static void main(String[] args)  
    {  
        new Test();  
    }  
}  
   
class CustomRenderer implements TableCellRenderer  
{  
    JScrollPane scrollPane;  
    JTextArea textArea;  
   
    public CustomRenderer()  
    {  
        textArea = new JTextArea();  
        scrollPane = new JScrollPane(textArea);  
    }  
   
    public Component getTableCellRendererComponent(JTable table,  
                                                   Object value,  
                                                   boolean isSelected,  
                                                   boolean hasFocus,  
                                                   int row, int column)  
    {  
        textArea.setText((String)value);  
        return scrollPane;  
    }  
}  
   
class CustomEditor implements TableCellEditor  
{  
    JTextArea textArea;  
    JScrollPane scrollPane;  
   
    public CustomEditor()  
    {  
        textArea = new JTextArea();  
        scrollPane = new JScrollPane(textArea);  
    }  
   
    public Component getTableCellEditorComponent(JTable table,  
                                                 Object value,  
                                                 boolean isSelected,  
                                                 int row, int column)  
    {  
        textArea.setText((String)value);  
        return scrollPane;  
    }  
   
    public void addCellEditorListener(CellEditorListener l) { }  
    public void cancelCellEditing() { }  
    public Object getCellEditorValue()  
    {  
        return textArea.getText();  
    }  
    public boolean isCellEditable(EventObject anEvent)  
    {  
        return true;  
    }  
    public void removeCellEditorListener(CellEditorListener l) { }  
    public boolean shouldSelectCell(EventObject anEvent)  
    {  
        return true;  
    }  
    public boolean stopCellEditing()  
    {  
        return true;  
    }  
}  