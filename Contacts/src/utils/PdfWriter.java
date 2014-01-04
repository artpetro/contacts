package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.ContactsTableModel;
import view.MainView;

import com.pdfjet.Box;
import com.pdfjet.Font;
import com.pdfjet.Letter;
import com.pdfjet.Line;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.TextLine;

public class PdfWriter {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void writePdf(MainView mainView, File file) throws FileNotFoundException, Exception {
		
		List[] data = ((ContactsTableModel) mainView.getTable().getModel()).getData();
	
		List<List<String>> names = data[0];
		List<String[]> phones = new LinkedList<String[]>();
		List<String[]> emails = new LinkedList<String[]>();
		
		int dataCount = names.size();
		
		String[] phonesArr;
		String[] emailsArr;
		
		for (int i = 0; i < dataCount; i++) {
			
			phonesArr = ((String) data[1].get(i)).split("\n");
			emailsArr = ((String) data[2].get(i)).split("\n");
			
			phones.add(phonesArr);
			emails.add(emailsArr);
		}
		
		
		FileOutputStream fos = new FileOutputStream(file);

        PDF pdf;
		
		pdf = new PDF(fos);
		
		int namePointer = 0;
		
		float headerHeight = 20.0f;
        
		
		while (namePointer < dataCount) {
			
	        Page page = new Page(pdf, Letter.PORTRAIT);
		
	        Box mainBox = new Box();
	        float mainBoxX = 30.0f;
	        float mainBoxY = 30.0f;
		    mainBox.setPosition(mainBoxX, mainBoxY);
		    
		    float boxWidht = 550.0f;
		    float maxBoxHeight = 700.0f;
		    
		    // data cells
	        float aviableHeight = maxBoxHeight - headerHeight;
	        float minCellHeight = 35.0f;
	        int tmpPointer = namePointer;
	        
	        while(aviableHeight > minCellHeight && tmpPointer < dataCount) {
	     
	        	float cellHeight = Math.max(phones.get(tmpPointer).length * 15, emails.get(tmpPointer).length * 15) + 5;
	        	aviableHeight -= Math.max(minCellHeight, cellHeight);
	        	tmpPointer++;
	        	
	        }
		    
	        float boxHeight = maxBoxHeight - aviableHeight;
		    mainBox.setSize(boxWidht, boxHeight);
		    mainBox.drawOn(page);
		    
		    // title
	        int titleSize = 12;
	        String title = mainView.getModel().getTitle();
	        
	        Font titleFont = new Font(pdf, "Helvetica-Bold");
	        titleFont.setSize(titleSize);
	        TextLine titleLine = new TextLine(titleFont);
	        titleLine.setPosition((boxWidht - (title.length() * titleSize/7))/2, 15);
		        
	        titleLine.setText(title);
	        titleLine.drawOn(page);
	        
	        // header
	        Line line = new Line(0, headerHeight, boxWidht, headerHeight);
	        line.placeIn(mainBox);
	    	line.drawOn(page);
	    	
	    	// column lines
	    	line = new Line(boxWidht/3, 0, boxWidht/3, boxHeight);
	    	line.placeIn(mainBox);
	    	line.drawOn(page);
	    	line = new Line(2 * boxWidht / 3, 0, 2 * boxWidht / 3, boxHeight);
	    	line.placeIn(mainBox);
	    	line.drawOn(page);
	    	
	    	// header title
	    	// name
	    	float y_pos = headerHeight - (headerHeight - titleSize)/2;
	        TextLine textLine = new TextLine(titleFont);
	        textLine.placeIn(mainBox);
	        textLine.setPosition((boxWidht/3 - 4 * titleSize)/2, y_pos);
	        textLine.setText("Name");
	        textLine.drawOn(page);
	        
	        // tel
	        textLine = new TextLine(titleFont);
	        textLine.placeIn(mainBox);
	        textLine.setPosition((boxWidht/3 - 4 * titleSize)/2 + boxWidht/3, y_pos);
	        textLine.setText("Telefon");
	        textLine.drawOn(page);
	    	
	        // email
	        textLine = new TextLine(titleFont);
	        textLine.placeIn(mainBox);
	        textLine.setPosition((boxWidht/3 - 4 * titleSize)/2 + 2*boxWidht/3, y_pos);
	        textLine.setText("Email");
	        textLine.drawOn(page);
	    	
	        Font mainFont = new Font(pdf, "Helvetica");
	        Font positionFont = new Font(pdf, "Helvetica");
	        float mainFontSize = 10.0f;
	        mainFont.setSize(mainFontSize);
	        positionFont.setSize(8);
	        
	        float lineOffcet = 5.0f;
	        float nameX = 2 * lineOffcet;
	        float phoneX = nameX + boxWidht/3;
	        float emailX = phoneX + boxWidht/3;
	        float cellY = 3 * lineOffcet + headerHeight;
	        float tmpCellY = cellY;
	        float positionY;
	        
	        // data row
	        while(tmpPointer - namePointer > 0) {

	        	// name cell
	        	textLine = new TextLine(mainFont);
	        	textLine.placeIn(mainBox);
	        	textLine.setPosition(nameX, cellY);
	        	textLine.setText(names.get(namePointer).get(0));
	        	textLine.drawOn(page);
	        	
	        	textLine = new TextLine(positionFont);
	        	textLine.placeIn(mainBox);
	        	positionY = cellY + mainFontSize + lineOffcet;
	        	textLine.setPosition(nameX, positionY);
	        	textLine.setText(names.get(namePointer).get(1));
	        	textLine.drawOn(page);
	        	
	        	// telefon cell
	        	String[] phone = phones.get(namePointer);
	        	
	        	for (String s : phone) {
	        		
	        		textLine = new TextLine(mainFont);
		        	textLine.placeIn(mainBox);
		        	textLine.setPosition(phoneX, tmpCellY);
		        	textLine.setText(s);
		        	textLine.drawOn(page);
		        	
		        	tmpCellY += mainFontSize + lineOffcet;
	        	
	        	}
	        	
	        	// email cell
	        	String[] email = emails.get(namePointer);
	        	
	        	for (String s : email) {
	        		
	        		textLine = new TextLine(mainFont);
		        	textLine.placeIn(mainBox);
		        	textLine.setPosition(emailX, cellY);
		        	textLine.setText(s);
		        	textLine.drawOn(page);
		        	
		        	cellY += mainFontSize + lineOffcet;
	        	
	        	}
	        	
	        	// closed line
	        	cellY = Math.max(cellY, tmpCellY);
	        	cellY = Math.max(cellY, positionY + mainFontSize + lineOffcet);
	        	
	        	line = new Line(0, cellY - mainFontSize, boxWidht, cellY - mainFontSize);
		        line.placeIn(mainBox);
		    	line.drawOn(page);
		    	
		    	cellY += lineOffcet;
		    	tmpCellY = cellY;
	        	
	        	namePointer++;
	        	
	        }
	        
	        // footer line
	        SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy, H:mm");
	        String date = dateFormat.format(new Date());
	    	String footer = mainView.getTitle() + " - erstellt am " + date;
	    	
	    	textLine = new TextLine(mainFont);
	    	textLine.setText(footer);
	    	textLine.setPosition((Letter.PORTRAIT[0] - (footer.length() * 5))/2, 60 + boxHeight);
	        textLine.drawOn(page);
	        
		}
        
    	pdf.flush();
        fos.close();
        
        Runtime rt = Runtime.getRuntime();
    	try{
    		rt.exec( "rundll32" +" " + "url.dll,FileProtocolHandler" + " " + file);
    	}catch (Exception e1){
    		e1.printStackTrace();
    	}
	}
}
