package com.eperium.testframework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfReader;
import com.unitedinternet.portal.selenium.utils.logging.LoggingUtils;

public class ConsolidatePDF {

	/**
	 * @param args
	 */

	public static void main(String ar[])
	{
		
		String site=ar[0];
		String channel=ar[1];
		Map<String,String> browsers=null;
		
		File  parentReportDir = new File(System.getProperty("user.dir")+File.separator+"TestcasesReports"+File.separator+site);
		
		List<File> pdfDirToConsolidate=getPdfDirToConsolidate(parentReportDir);
		
		
		for(File pdfDir:pdfDirToConsolidate)
		{
			browsers=getBrowsers(pdfDir);
			for(String browser:browsers.keySet())
			{	
				concatePDF(pdfDir,channel,browser);
			}
		}
		
	}	
		public static List<File> getPdfDirToConsolidate(File parentReportDir)
		{
			String [] consolidatedFile=null;
			List <File> pdfDirToConsolidate=new ArrayList<File>();
			
			for(File dateFolder:parentReportDir.listFiles())
			{
				for(File pdfReportDir:dateFolder.listFiles())
				{
					if(pdfReportDir.isDirectory() && pdfReportDir.getName().equalsIgnoreCase("pdf"))
					{
						consolidatedFile=pdfReportDir.list(new FilenameFilter() {
							
							@Override
							public boolean accept(File dir, String name) {
								// TODO Auto-generated method stub
								if(name.contains("_consolidated"))
								{
									return true;
								}
								else
								{
									return false;	
								}
								
							}
						});
						if(consolidatedFile.length==0)
						{
							pdfDirToConsolidate.add(pdfReportDir);
						}
						
					}
						
				}
			}
			return pdfDirToConsolidate;
		}
	
	public static void concatePDF(File reportsDir,String channel,String browser)
	{
		String datewithTime= LoggingUtils.timeStampForFileName();
		System.out.println("date format is : "+ datewithTime);
		String [] splitTimeDate=datewithTime.split("_");
		String testCaseStartAt=splitTimeDate[0];
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
	    testCaseStartAt = sdf.format(date);
	    sdf = new SimpleDateFormat("dd/MM/yyyy");
	    testCaseStartAt = sdf.format(date).replace("/", "");
	    testCaseStartAt=testCaseStartAt+"_"+splitTimeDate[1].replace("-", "");
	    System.out.println("formatted date in dd/MM/yyyy : " + testCaseStartAt);
		String pdfFileName =channel+"_"+browser+testCaseStartAt+"_consolidated.pdf";
		String filePath=reportsDir+File.separator+pdfFileName;
				
		try
		{
			Document document = new Document();
			FileOutputStream fileOutputStream= new  FileOutputStream(reportsDir+File.separator+pdfFileName);
			PdfCopy copy = new PdfCopy(document, fileOutputStream);
			document.open();
			PdfReader reader;
			for(File existingPDFFile: reportsDir.listFiles()) {
			
				if(existingPDFFile.getName().contains(browser) && (!(existingPDFFile.getName().contains("_consolidated"))))
				{
					reader = new PdfReader(existingPDFFile.getAbsolutePath());
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
			fileOutputStream.close();
		}
		catch (Exception e) {
			
			new File(filePath).delete();
		}
	}


	public static Map<String,String> getBrowsers(File reportsDir)
	{
		Map<String,String> browsers=new HashMap<String,String>();
		
		for(File existingPDFFile: reportsDir.listFiles()) {
			
			if(!(existingPDFFile.getName().contains("_consolidated")))
			{
				browsers.put(existingPDFFile.getName().split("_")[1],existingPDFFile.getName().split("_")[0]);
			}
			
		}
				
		return browsers;
	}

}

