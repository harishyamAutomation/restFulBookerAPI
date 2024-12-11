package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
	
	
	//Method to provide jsonObject of File
	public static JSONObject getJsonData() throws IOException, ParseException {
		
		//System.out.println("Current working directory -> JSONReader: " + new File(".").getAbsolutePath());
		
		File testData = new File("resources/TestData/testData.json"); //Fetch TestData file, File <- java.io
		
		String json = FileUtils.readFileToString(testData); //Converting file to String using FileUtils, FileUtils <- org.apache.commons.io
		
		Object obj = new JSONParser().parse(json); //Parsing that string into Object using JSONParser, JSONParser <- org.json.simple
		
		JSONObject jsonObject = (JSONObject) obj; //Converting that object into JSON Object, JSONObject <- org.json.simple
		
		return jsonObject;
		
	}
	
	public static JSONObject getJsonData(File file) throws IOException, ParseException {
		
		//System.out.println("Current working directory -> JSONReader: " + new File(".").getAbsolutePath());
		
		File testData = file;
		
		String json = FileUtils.readFileToString(testData); //Converting file to String using FileUtils, FileUtils <- org.apache.commons.io
		
		Object obj = new JSONParser().parse(json); //Parsing that string into Object using JSONParser, JSONParser <- org.json.simple
		
		JSONObject jsonObject = (JSONObject) obj; //Converting that object into JSON Object, JSONObject <- org.json.simple
		
		return jsonObject;
		
	}
	
		public static JSONObject getJsonData(String jsonData) throws IOException, ParseException {
		
		//System.out.println("Started Parsing jsonString to Object");	
		
		Object obj = new JSONParser().parse(jsonData); //Parsing that string into Object using JSONParser, JSONParser <- org.json.simple
		
		//System.out.println("Started Parsing Object to JSONObject");
		
		JSONObject jsonObject = (JSONObject) obj; //Converting that object into JSON Object, JSONObject <- org.json.simple
		
		//System.out.println("Successfully parsed to JSONObject");
		
		return jsonObject;
		
	}
		
	
	//Method to getTheData from JSON File
	
	public static String getTestData(String key) throws IOException, ParseException {
		
		return (String) getJsonData().get(key); //Fetching the desired key from the JSON
		
	}
	
	public static String getTestData(String json, String key) throws IOException, ParseException {
		
		JSONObject jsonObject = getJsonData(json);
		
		//System.out.println("Finding given key......"+jsonObject);
		
		if (jsonObject.containsKey(key)) {
			return (String) jsonObject.get(key); //Fetching the desired key from the JSON
	    } else {
	        throw new IllegalArgumentException("Key '" + key + "' not found in the JSON.");
	    }
		
	}
	
	public static JSONArray getJSONArray(String key) {
		JSONObject jsonObject = null;
		try {
			jsonObject = getJsonData();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jsonArray = (JSONArray) jsonObject.get(key);
		return jsonArray;
	}
	
	public static JSONArray getJSONArray(Object key) {
		JSONObject jsonObject = null;
		try {
			jsonObject = getJsonData();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jsonArray = (JSONArray) jsonObject.get(key);
		return jsonArray;
	}
	
	public static Object getJSONArrayData(String key, int index) {
		
		JSONArray jsonArray = getJSONArray(key);
		return jsonArray.get(index);
		
	}

	public static JSONArray getJSONArray(JSONObject jsonData) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
