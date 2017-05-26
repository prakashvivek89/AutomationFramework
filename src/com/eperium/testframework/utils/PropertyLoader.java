package com.eperium.testframework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	
	static 
	{
	    	loadPropertiesResources();
	    	loadXMLResources();
	    	loadURLResources();
	    	loadLocalizationResources();
	    	loadDataResources();
	    	loadSKUResources();
	}
	static Properties prop;
	static Properties xmlRepository;
	static Properties urlRepository;
	static Properties SKURepository;
	static Properties dataRepository;
	static Properties localizationRepository;
	static Properties propertyFile;
	
	
	public static Properties getUrlRepository() {
		return urlRepository;
	}
	
	public static Properties getDataRepository(){
		return dataRepository;
	}
	public static Properties getSKURepository(){
		return SKURepository;
	}
	public static Properties getLocalizationRepository() {
		return localizationRepository;
	}
	
	private static void loadPropertiesResources() {
	    prop = new Properties();
	    InputStream in = PropertyLoader.class.getResourceAsStream("/resources/PropertyResources.properties");
	    try {
	        prop.load(in);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	  }
	
	public static Properties getUrlPropertiesFile(String urlFileKey) throws Exception{
	    
		String urlFile = PropertyLoader.getUrlRepository().getProperty(urlFileKey);
		if(urlFile != null){
			propertyFile = new Properties();
			InputStream in = PropertyLoader.class.getResourceAsStream(urlFile);
			try {
				propertyFile.load(in);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
	    }
		
	    return propertyFile;
	    }else{
	    	throw new Exception("URL Property not Found");
	    }
	  }
	
	public static Properties getDatapropertiesFile(String dataFileKey) throws Exception{
		
		String dataFile = PropertyLoader.getDataRepository().getProperty(dataFileKey);
		if(dataFile != null){
			propertyFile = new Properties();
			InputStream in = PropertyLoader.class.getResourceAsStream(dataFile);
			try {
	        propertyFile.load(in);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	    return propertyFile;
	    }else{
	    	throw new Exception("Data Property not Found");
	    }
	}
	
	public static Properties getSKUpropertiesFile(String dataFileKey) throws Exception{
		
		String dataFile = PropertyLoader.getSKURepository().getProperty(dataFileKey);
		if(dataFile != null){
			propertyFile = new Properties();
			InputStream in = PropertyLoader.class.getResourceAsStream(dataFile);
			try {
			
	    
	        propertyFile.load(in);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	    return propertyFile;
	    }else{
	    	throw new Exception("SKU Property not Found");
	    }
	}
	
	public static Properties getLocalizationPropertiesFile(String localeFileKey) throws Exception{
	    
		String localizationFile = PropertyLoader.getLocalizationRepository().getProperty(
				localeFileKey);
		if(localizationFile != null){
			propertyFile = new Properties();
			InputStream in = PropertyLoader.class.getResourceAsStream(localizationFile);
			try {
			
	    
	        propertyFile.load(in);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	    return propertyFile;
	    }else{
	    	throw new Exception("Localization Property not Found");
	    }
	  }
	
	private static void loadURLResources() {
	    urlRepository = new Properties();
	    InputStream in = PropertyLoader.class.getResourceAsStream(prop.getProperty("URLRepository"));
	    try {
	        urlRepository.load(in);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	  }
	
	private static void loadSKUResources() {
	    SKURepository = new Properties();
	    InputStream in = PropertyLoader.class.getResourceAsStream(prop.getProperty("SKURepository"));
	    try {
	        SKURepository.load(in);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	  }
	
	private static void loadDataResources() {
	    dataRepository = new Properties();
	    InputStream in = PropertyLoader.class.getResourceAsStream(prop.getProperty("DataRepository"));
	    try {
	    	dataRepository.load(in);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	  }
	
	private static void loadLocalizationResources() {
		localizationRepository = new Properties();
	    InputStream in = PropertyLoader.class.getResourceAsStream(prop.getProperty("LocalizationRepository"));
	    try {
	    	localizationRepository.load(in);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	  }
	
	private static void loadXMLResources() {
	    xmlRepository = new Properties();
	    InputStream in = PropertyLoader.class.getResourceAsStream(prop.getProperty("XMLRepository"));
	    try {
	        xmlRepository.load(in);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	  }
	
	public static Properties getProp(){
		return prop;
	}
	
	public static Properties getXMLResources(){
		return xmlRepository;
	}

}
