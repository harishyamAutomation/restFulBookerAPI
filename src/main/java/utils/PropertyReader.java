package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	
	public static String propertyReader(String filepath, String key) {
		
		String value = null;
		
		System.out.println("Current working directory -> PropertyReader: " + new File(".").getAbsolutePath());
		
		try {
			InputStream input = new FileInputStream(filepath);
			
			Properties prop = new Properties();
			
			prop.load(input);
			
			value = prop.getProperty(key);
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		}
		catch (IOException e) {
			// TODO: handle exception
		}
		
		return value;
		
	}
	
}
