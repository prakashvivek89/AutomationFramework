package com.eperium.testframework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import com.unitedinternet.portal.selenium.utils.logging.LoggingUtils;

public class SuiteReportUtils implements ISuiteListener{
	private static final String START_DATE_TIME_FORMAT = "startDateTimeFormat";
	private static final String START_DATE_TIME = "startDateTime";
     static public WritableWorkbook workbook;
     static public int testCaseName= 0;
     static public int classMethod=1;
     static public int exception= 2;
     static public int customer= 3;
     static public int browserCol= 4;
     //static public int versionCol= 5;
//     static public int channelCol= 5;
     static public int countryCol= 5;
     static public int localeCol= 6;
     static public int time= 7;
     static public int rowNum = 0;
     static public String browser=null,version=null,testcasename=null;
     static public String site=null,environment=null;
     static public String channel=null;
     static public String country=null;
     static public String locale=null;
     static public String currFileName = null;
     static public String pdfFileName =null;
     static public String pdfTestCaseName =null;
     public WritableSheet sheet=null;
     public File reportDir=null;
     public ArrayList<String> arrFileNames =null,existingPDFFiles=null;
     public static File pdfReportDir=null;
     public String testCaseStartAt =null;
     static public String currDir=null;
     static public String pdfRequired=null;
     static public String startDate=null;
     static public String startDateTime=null;
     
     static public Document pdfdocument = null;
     static public boolean reportAttributesAdded=false;
    
     @Override
	public void onStart(ISuite arg0)
     {
		site = arg0.getXmlSuite().getParameter("site");
	 	channel = arg0.getXmlSuite().getParameter("channel");
	 	country = arg0.getXmlSuite().getParameter("country");
		browser = arg0.getXmlSuite().getParameter("selenium.browser");
		version = arg0.getXmlSuite().getParameter("selenium.browser.version");
		locale = arg0.getXmlSuite().getParameter("locale");
		testcasename = arg0.getXmlSuite().getParameter("testcasename");
		environment=arg0.getXmlSuite().getParameter("environment");
		pdfRequired = arg0.getXmlSuite().getParameter("PDFRequiredPerTestCase");
		 try {
		    	Properties urlProp = PropertyLoader.getUrlPropertiesFile(environment);
			    environment = urlProp.getProperty("environment");
		     } 
		 catch (Exception e1) {
			e1.printStackTrace();
		}
		currDir =  System.getProperty("user.dir")+File.separator+"TestcasesReports";
		String reports = "reports";
		startDate= getStartDate();	
		startDateTime= getStartDateTime();
		reportDir = new File(currDir+File.separator+site+File.separator+startDate+File.separator+reports);
		String fileName = site+"_"+"AutomationReport"+"_"+environment+"_"+startDateTime; 
		currFileName = reportDir+File.separator+fileName+".xls";
		System.out.println("Directory where report generated." +reportDir);
		if(!reportDir.exists()){
			reportDir.mkdirs();
		}
		pdfReportDir = new File(currDir+File.separator+site+File.separator+startDate+File.separator+"pdf"+File.separator);
		if(!pdfReportDir.exists()){
			pdfReportDir.mkdirs();
		}
		System.out.println("Directory where report generated." +reportDir);
		arrFileNames = new ArrayList<String>();
		for(String arrStrFileName:reportDir.list())
		  {
			/*Adding fileNames in array to check if the file exists so that the code can be appended in the same report*/
			arrFileNames.add(arrStrFileName);
		  }
		try {
			WritableFont wrapCell =  new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD, false);
			WritableCellFormat headerCellformat = new WritableCellFormat(wrapCell);
			headerCellformat.setWrap(true);
			headerCellformat.setBackground(Colour.GRAY_25);
			if(arrFileNames.isEmpty() && !arrFileNames.toString().contains(site+"_"+startDateTime)){
				workbook = Workbook.createWorkbook(new File(currFileName));	
				sheet = workbook.createSheet(site, 0);
				Label label1 = new Label(testCaseName,rowNum, "TestCaseName",headerCellformat);
				sheet.setColumnView(testCaseName, 50);
				sheet.addCell(label1);
				Label label2 = new Label(classMethod, rowNum,"Steps",headerCellformat);
				sheet.setColumnView(classMethod, 50);
				sheet.addCell(label2);
				Label label4 = new Label(exception,rowNum,"Test Result",headerCellformat);
				sheet.setColumnView(exception, 50);
				sheet.addCell(label4);
				Label label5 = new Label(customer,rowNum,"Webshop",headerCellformat);
				sheet.addCell(label5);
				Label label6 = new Label(browserCol,rowNum, "Browser",headerCellformat);
				sheet.addCell(label6);
//				Label label7 = new Label(versionCol,rowNum, "Version",headerCellformat);
//				sheet.addCell(label7);
				Label label8 = new Label(countryCol,rowNum, "Country",headerCellformat);
				sheet.setColumnView(countryCol, 15);
				sheet.addCell(label8);
				Label label9 = new Label(localeCol,rowNum, "Locale",headerCellformat);
				sheet.addCell(label9);
				Label label3 = new Label(time,rowNum,"Time(ms)",headerCellformat);
				sheet.addCell(label3);
				rowNum++;
			}
			else
			{
				int index=0;
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(String str: arrFileNames)
				{
					///// search using  : write in an excel from a particular row +java jxl
					if(str.contains(site+"_"+"AutomationReport"+"_"+environment+"_"+startDate+"_"))
					{
						index++;
					}
					
				}
				 Date dt = sdf.parse(sdf.format(new File(reportDir+File.separator+arrFileNames.get(index-1)).lastModified()));
				 Boolean writeInSameFile = getTimeDifference(new Date(),dt);
				 if(!writeInSameFile && new File(reportDir+File.separator+arrFileNames.get(index-1)).getName().contains(".xls")) // if false then create a new file
				 {
					 currFileName = reportDir+File.separator+fileName+".xls";
					 workbook = Workbook.createWorkbook(new File(currFileName));	
						sheet = workbook.createSheet(site, 0);
						Label label1 = new Label(testCaseName,rowNum, "TestCaseName",headerCellformat);
						sheet.setColumnView(testCaseName, 50);
						sheet.addCell(label1);
						Label label2 = new Label(classMethod, rowNum,"Steps",headerCellformat);
						sheet.setColumnView(classMethod, 50);
						sheet.addCell(label2);
						Label label4 = new Label(exception,rowNum,"Test Result",headerCellformat);
						sheet.setColumnView(exception, 50);
						sheet.addCell(label4);
						Label label5 = new Label(customer,rowNum,"Webshop",headerCellformat);
						sheet.addCell(label5);
						Label label6 = new Label(browserCol,rowNum, "Browser",headerCellformat);
						sheet.addCell(label6);
						Label label7 = new Label(countryCol,rowNum, "Country",headerCellformat);
						sheet.setColumnView(countryCol, 15);
						sheet.addCell(label7);
						Label label8 = new Label(localeCol,rowNum, "Locale",headerCellformat);
						sheet.addCell(label8);
						Label label3 = new Label(time,rowNum,"Time(ms)",headerCellformat);
						sheet.addCell(label3);
						
						rowNum++;
				 }
				 else if(new File(reportDir+File.separator+arrFileNames.get(index-1)).getName().contains(".xls"))
				 {
					 // this will make an entry of a channel whose entry is not present in the excel.... try something else 
					 Workbook currWorkbook = Workbook.getWorkbook(new File(reportDir+File.separator+arrFileNames.get(index-1))); 
					 workbook = Workbook.createWorkbook(new File(reportDir+File.separator+arrFileNames.get(index-1)),currWorkbook);
					 sheet = workbook.getSheet(0);
					 rowNum = sheet.getRows() ;
				 }
				
			}
			if(!pdfRequired.equalsIgnoreCase("true")){
				setPDFReportData(arg0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	}
     
     @Override
     public void onFinish(ISuite arg0)
     {
    	 try 
    	 {
    		 if(workbook != null)
    		 {
    			 workbook.write();
    			 workbook.close();
    		 }
    		 if(!pdfRequired.equalsIgnoreCase("true")){
    		    if(pdfdocument!=null && pdfdocument.isOpen())
    	     	 {
    			 pdfdocument.close();
    		      }
    		 if(new File(pdfReportDir+File.separator+pdfFileName).length() == 0)
    		 {
    			 new File(pdfReportDir+File.separator+pdfFileName).delete();	 // if the pdf report has no entry then delete the pdf file.
    		 }
    		
    		 }
	
    	 }
    	 catch (WriteException e)
    	 {
    		 e.printStackTrace();
    	 }
    	 catch (IOException e)
    	 {
    		 e.printStackTrace();
    	 }
     }
     
     /**
      * This method will read the system environment variables "startDateTime" and "dateTimeFormat" and return the date in required format.
      * If no date is found in environment, current date will be used.
      * @return String
      */
     
     private String getStartDate() {
    	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    	 String date = System.getenv(START_DATE_TIME);
       	 String format = System.getenv(START_DATE_TIME_FORMAT);
    	 String startDate = null;
    	 if(date != null && format != null){
    		  try{
    			 startDate = dateformat.format(new SimpleDateFormat(format).parse(date));
    		 }catch(ParseException x){
    			 //nothing to do
    			 System.out.println("Start date not found in system environment." );
    		 }
    	 }
    	 if(startDate == null){
    		 System.out.println("Falling back to current date.");
    		 startDate = dateformat.format(new Date());
    	 }
    	 return startDate;
     }
     
     /**
      * This method will read the system environment variables "startDateTime" and "dateTimeFormat" and return the date in required format.
      * If no date is found in environment, current date will be used.
      * @return String
      */
     
     private String getStartDateTime() {
    	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
    	 String date = System.getenv("startDateTime");
    	 String format = System.getenv("startDateTimeFormat");
    	 String startDateTime = null;
    	 if(date != null && format != null){
    		 try{
    			 startDateTime = dateformat.format(new SimpleDateFormat(format).parse(date));
    		 }catch(ParseException x){
    			 //nothing to do
    		 }
    	 }
    	 if(startDateTime == null){
    		 System.out.println("Start date not found in system environment. Falling back to current date.");
    		 startDateTime = LoggingUtils.timeStampForFileName();
    	 }
    	 return startDateTime;
     }
     
/**
 * if the time difference between the file and current time is more than five minutes, a new test case is running so create a new file for report 
 * */
public boolean getTimeDifference(Date d1, Date d2)
{
	boolean writeInSameFile = false ;
	
	Calendar cal1 = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();
	cal1.setTime(d1);
	cal2.setTime(d2);
	long milliSeconds1 = cal1.getTimeInMillis();
	long milliSeconds2 = cal2.getTimeInMillis();
	long diff = milliSeconds1 - milliSeconds2;
	long minutes = diff/(1000*60);
	System.out.println("difference in minutes is "+minutes);
	if(minutes >= 5)
	{
		writeInSameFile= false;
	}
	else
	{
		writeInSameFile= true;
	}
	
	
	return writeInSameFile;
}

/**
 * Generates a pdf for testCases
 * */
public void setPDFReportData(ISuite result)
{
	try	{
		pdfdocument= new Document();
		String datewithTime= LoggingUtils.timeStampForFileName();
		System.out.println("date format is : "+ datewithTime);
		String [] splitTimeDate=datewithTime.split("_");
		testCaseStartAt=splitTimeDate[0];
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
	    testCaseStartAt = sdf.format(date);
	    sdf = new SimpleDateFormat("dd/MM/yyyy");
	    testCaseStartAt = sdf.format(date).replace("/", "");
	    testCaseStartAt=testCaseStartAt+"_"+splitTimeDate[1].replace("-", "");
	    System.out.println("formatted date in dd/MM/yyyy : " + testCaseStartAt);
		pdfFileName =country+"_"+testcasename+"_"+browser.substring(1)+"_"+testCaseStartAt+".pdf";
		PdfWriter.getInstance(pdfdocument, new FileOutputStream(pdfReportDir+File.separator+pdfFileName));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (DocumentException e) {
		e.printStackTrace();
	}
	pdfdocument.open();
	}

public static Document getPDFDocument()
{
	return pdfdocument;
}
}

