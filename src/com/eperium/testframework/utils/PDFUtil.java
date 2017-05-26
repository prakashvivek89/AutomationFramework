package com.eperium.testframework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfReader;

public class PDFUtil {

	/**
	 * @param args
	 */

	public void concatePDF(String site,String channel,String browser, String testcasename)
	{
		
		//SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		File pdfReportDir = new File("C:\\TestcasesReports"+File.separator+site+File.separator+dateformat.format(new Date())+File.separator+"pdf"+File.separator);
		List<String> existingPDFFileNames= new ArrayList<String>();
		
		
		for(String arrPDFFiles:pdfReportDir.list())
		{
			/*Adding fileNames in array to check if the file exists so that the code can be appended in the same report*/
			existingPDFFileNames.add(arrPDFFiles);
		}
		try
		{
			Document document = new Document();
			String pdfFileName =channel+"_"+testcasename+"_"+browser.substring(1)+"_Concatenated.pdf";
			//String pdfFileName = testcasename+"_"+browser.substring(1)+"_Concatenated.pdf";
			PdfCopy copy = new PdfCopy(document, new FileOutputStream(pdfReportDir+File.separator+pdfFileName));
			document.open();
			PdfReader reader;
			for(String existingPDFFile: existingPDFFileNames) {
			
				if(existingPDFFile.contains(browser))
				{
					reader = new PdfReader(existingPDFFile);
					// loop over the pages in that document
					for (int page = 0; page < reader.getNumberOfPages(); )
					{
						copy.addPage(copy.getImportedPage(reader, ++page));
					}
					copy.freeReader(reader);
					reader.close();
				}
			}
			document.close();
		}
		catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

}
