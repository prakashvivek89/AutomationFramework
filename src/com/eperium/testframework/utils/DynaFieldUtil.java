package com.eperium.testframework.utils;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DynaFieldUtil {
	static Workbook workbook; 
	static Workbook objRepWrkBok;
	static Sheet sheet;
	static Sheet dataSheet;
    static int nR=0;
    static int nC=0;
	public static String account;
	public static int addressIndex;
    
    /*
     * Function to get unique email id. 
     */
    
	public static String uniqueEmail(){
		String uniqID = UUID.randomUUID().toString();
		String tempID = uniqID.substring(0, 10);
		String emailID = tempID + "@" + "autest.com";
		return emailID;
	}
	
	/*
	 * Funtion to get sheet (initialize sheet from excel file) 
	 */
	
	
	public static Sheet getWorkSheet(int i) throws BiffException, IOException{
		
		workbook = Workbook.getWorkbook(new File(PropertyLoader.prop.getProperty("PostalCodeXLS")));
		sheet = workbook.getSheet(i);
		dataSheet = workbook.getSheet(i);
		//objRepWrkBok = Workbook.getWorkbook(new File("InputData\\selenium_utility.xls"));
		//objRepShet = objRepWrkBok.getSheet(0); 
		return sheet;
	}
   
	/*
	 * Function to get count of rows of excel sheet.
	 */ 
	public static int getRowCount() throws BiffException, IOException{
		nR= getWorkSheet(0).getRows();
        return nR;    	
	}
	
	/*
	 * Function to get count of column of excel sheet.
	 */
	
	public static int getColumnCount() throws BiffException, IOException{
		nC= getWorkSheet(0).getColumns();
        return nC; 
	}
	
	/*
	 * Funtion to get index of address of sheet
	 */
	
	public static int getAddrIndex() throws BiffException, IOException{
	    Random rn = new Random();
		addressIndex = rn.nextInt(getRowCount()) + 1;
		return addressIndex;
	}
	
	/*
	 * Function to get Postal code from excel sheet.
	 */
	
	public static String getPostalCode() throws BiffException, IOException{
		String pstCode = null;
		Cell PostalCodecellValue;
		addressIndex = getAddrIndex();
	//	System.out.println("-------------->AddressIndex for Postal Code" +AddressIndex);
		PostalCodecellValue = getWorkSheet(0).getCell(0, addressIndex);
		pstCode = PostalCodecellValue.getContents();
		return pstCode;
	}

	/*
	 * Function to get street from excel sheet.
	 */
	
	public static String getStreet() throws BiffException, IOException{
		String Street = null;
		Cell StreetcellValue;
		//AddressIndex = getAddrIndex();
		//System.out.println("-------------->AddressIndex for Street" +AddressIndex);
		StreetcellValue = getWorkSheet(0).getCell(1, addressIndex);
		Street = StreetcellValue.getContents();
		return Street;
	}
	/*
	 * Function to get UserName
	 */
	
	public static String getUserName() throws BiffException, IOException{
		String UserName = null;
		Cell UserNameCellValue = getWorkSheet(1).getCell(0, 1);
		UserName= UserNameCellValue.getContents();		
		return UserName;
	}
	/*
	 * Function to get Password
	 */
	
	public static String getPassword() throws BiffException, IOException{
		String Password = null;
		Cell PasswordCellValue = getWorkSheet(1).getCell(1, 2);
		Password= PasswordCellValue.getContents();		
		return Password;
	}	
	
}
