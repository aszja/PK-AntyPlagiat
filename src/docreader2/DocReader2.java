package docreader2;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DocReader2 {
public static void main(String[] args) {
File file = null;
WordExtractor extractor = null ;
try {

   file = new File("c:\\new1.doc");
   FileInputStream fis=new FileInputStream(file.getAbsolutePath());
   HWPFDocument document=new HWPFDocument(fis); 
   extractor = new WordExtractor(document);
   String [] fileData = extractor.getParagraphText();
   
   for(int i=0;i<fileData.length;i++){
     if(fileData[i] != null)
       System.out.println(fileData[i]);
   }
}
catch(Exception ex){
    Logger.getLogger(HSSFWorkbook.class.getName()).log(Level.SEVERE, null, ex);
} 
  }
}