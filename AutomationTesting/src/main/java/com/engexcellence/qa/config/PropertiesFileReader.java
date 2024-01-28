package com.engexcellence.qa.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
	
	public FileInputStream stream;
	public Properties propertyfile = new Properties();
	String configFile;
	
	public PropertiesFileReader(String filename)  {
		try {
		this.configFile=filename;
		stream=new FileInputStream(configFile);
		propertyfile.load(stream);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPropertyfileValue(String filename) {
		return propertyfile.getProperty(filename).toString();
	}

}
