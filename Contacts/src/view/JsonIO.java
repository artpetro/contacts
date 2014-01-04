package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ContactsTableModel;

import com.google.gson.Gson;

public class JsonIO {
	
	public static boolean saveAs(MainView mainView) {

		FileSaveChooser chooser = new FileSaveChooser(mainView, "dat");

        int result = chooser.showSaveDialog(mainView);

        if (result == JFileChooser.APPROVE_OPTION) {

        	File file = chooser.getFile();
	        FileWriter fw;
	        
	        try {
	        	
	        	fw = new FileWriter(file.getAbsoluteFile());
	        	BufferedWriter bw = new BufferedWriter(fw);
	        	Gson json = new Gson();
 			 
	        	bw.write(json.toJson(((ContactsTableModel) mainView.getTable().getModel()).getData()));
	        	bw.close();
	        	
	        	String dirPfad = file.getParent().toString();
	            mainView.setDirPath(dirPfad);
	            mainView.setFileName(file.getName());
 			 
			} catch (IOException e) {
				e.printStackTrace();
			}

            chooser.setVisible(false);
            return true;
 
        }
        
        chooser.setVisible(false);
        return false;
    
	} 
	
	@SuppressWarnings("rawtypes")
	public static List[] load(String dirPfad, MainView mainView) {
		
		List[] data = null;

        JFileChooser chooser;
        if (dirPfad == null)
        	dirPfad = System.getProperty("user.home");
        File file = new File(dirPfad.trim());

        chooser = new JFileChooser(dirPfad);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        
        chooser.setDialogTitle("Laden...");
        FileFilter filter = new FileNameExtensionFilter("Dateien", "dat");
        chooser.setFileFilter(filter);
        chooser.setVisible(true);

        int result = chooser.showOpenDialog(mainView);

        if (result == JFileChooser.APPROVE_OPTION) {

            String filePfad = chooser.getSelectedFile().toString();
            file = new File(filePfad);
            dirPfad = chooser.getSelectedFile().getParent().toString();
            mainView.setDirPath(dirPfad);
            
            BufferedReader br = null;
            Gson gson = new Gson();
            
			try {

				br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
 
				data = gson.fromJson(br, List[].class);
 			 
			} catch (IOException e) {
				e.printStackTrace();
        	} finally {
			
        		try {
        			if (br != null)br.close();
        		} catch (IOException ex) {
        			ex.printStackTrace();
        		}
        	}

            chooser.setVisible(false);
            mainView.setFileName(file.getName());
            return data;
        }
        
        chooser.setVisible(false);
        return null;
    
	}
}
