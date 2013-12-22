package view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSaveChooser extends JFileChooser{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainView mainView;
	private String extension;
	private File file;
	
	public FileSaveChooser(MainView mainView, String ext) {
		
		super(mainView.getDirPath());
		this.mainView = mainView;
		this.extension = ext;
		FileFilter filter = new FileNameExtensionFilter(ext+"-Dateien", ext);
		this.setFileFilter(filter);
		
	}

	@Override
    public void approveSelection(){
		
		String filePfad = getSelectedFile().toString();
        String extension = "";

        int i = filePfad.lastIndexOf('.');
        int p = Math.max(filePfad.lastIndexOf('/'), filePfad.lastIndexOf('\\'));

        if (i > p) {
            extension = filePfad.substring(i+1);
        }
        if (!(extension.equals(this.extension))) {
        	filePfad += "." + this.extension;
        }
		
        file = new File(filePfad);
        mainView.setDirPath(getSelectedFile().getParent().toString());
        
        if(file.exists() && getDialogType() == SAVE_DIALOG){
            int result = JOptionPane.showConfirmDialog(this,"Wollen Sie die Datei überschreiben?","Die Datei existiert bereits",JOptionPane.YES_NO_CANCEL_OPTION);
            switch(result){
                case JOptionPane.YES_OPTION:
                    super.approveSelection();
                    return;
                case JOptionPane.NO_OPTION:
                    return;
                case JOptionPane.CLOSED_OPTION:
                    return;
                case JOptionPane.CANCEL_OPTION:
                    cancelSelection();
                    return;
            }
        }
    
        super.approveSelection();
	}
	
	public File getFile() {
		return this.file;
	}
}