package com.swaglabs.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties properties;
	
	String path = System.getProperty("user.dir") + "/Configuration/config.properties";
	
//	Constructor
	public ReadConfig() {
		try {
		properties= new Properties();
		FileInputStream fis = new FileInputStream(path);
		properties.load(fis);
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public String getBaseUrl() {
		String value = properties.getProperty("baseUrl");
		if(value!=null)
			return value;
		else
			throw new RuntimeException("Url not specified in Config file");
	}
	
	public String getBrowser() {
		String value = properties.getProperty("browser");
		if(value!=null)
			return value;
		else
			throw new RuntimeException("Browser not specified in Config file");
	}
	
	public String getUserName() {
		String value = properties.getProperty("userName");
		if(value!=null) 
			return value;
		else
			throw new RuntimeException("UserName not specified in Config file");	
	}
	
	public String getPassword() {
		String value = properties.getProperty("password");
		if(value!=null) 
			return value;
		else
			throw new RuntimeException("Password not specified in Config file");	
	}
}
