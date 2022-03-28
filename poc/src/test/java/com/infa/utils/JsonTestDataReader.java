package com.infa.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonTestDataReader {
	
	// This class for reading the josn files for test data to test case.
	// which will return HashMap<String,String> as output
	//this method takes input as MethodName and the test data json file should create using methodname.json file
	
	@SuppressWarnings("unchecked")
	public HashMap<String,HashMap<String,String>> readJson_testData(Method m){
		
		HashMap<String,HashMap<String,String>> datamap = new HashMap<String,HashMap<String,String>>();
		String jsonPath = System.getProperty("user.dir")+"/src/test/resources/testdata/"+ m.getName() + ".json";
		
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(jsonPath));
			JSONObject jsonObject = (JSONObject) obj;
			//System.out.println(jsonObject.get("Source"));
			datamap.put("TestCaseDetails", (HashMap<String, String>) jsonObject.get("TestCaseDetails"));
			
			if(jsonObject.get("Source") !=null) {
				datamap.put("Source", (HashMap<String, String>) jsonObject.get("Source"));
			}
			
			if(jsonObject.get("Target") !=null) {
				datamap.put("Target", (HashMap<String, String>) jsonObject.get("Target"));
			}
			
			if(jsonObject.get("MaskingColumns") != null) {
				datamap.put("MaskingColumns", (HashMap<String, String>) jsonObject.get("MaskingColumns"));
			}
			
			if(jsonObject.get("ExtraColumnsToMap") != null) {
				datamap.put("ExtraColumnsToMap", (HashMap<String, String>) jsonObject.get("ExtraColumnsToMap"));
			}
			
			if(jsonObject.get("RunDetails") != null) {
				datamap.put("RunDetails", (HashMap<String, String>) jsonObject.get("RunDetails"));
			}
			
			if(jsonObject.get("RelationalDicConnection") != null) {
				datamap.put("RelationalDicConnection", (HashMap<String, String>) jsonObject.get("RelationalDicConnection"));
			}
			
			if(jsonObject.get("FlatFileDictionaryConnection") != null) {
				datamap.put("FlatFileDictionaryConnection", (HashMap<String, String>) jsonObject.get("FlatFileDictionaryConnection"));
			}
			
			
			
			if(jsonObject.get("ConnectionsTypes") != null) {
				datamap.put("ConnectionsTypes", (HashMap<String, String>) jsonObject.get("ConnectionsTypes"));
				HashMap<String, String> connections = new HashMap<String, String>();
				connections = datamap.get("ConnectionsTypes");
				for(String key: connections.keySet()) {
					String[] arrOfconns = connections.get(key).split("#");
					for(String conn:arrOfconns ) {
						if(jsonObject.get(conn) !=null) {
							datamap.put(conn, (HashMap<String, String>) jsonObject.get(conn));
						}
					}
				}
			}
			
			
			if((HashMap<String, String>) jsonObject.get("login")!=null) {
				datamap.put("login", (HashMap<String, String>) jsonObject.get("login"));
			}
			
			if(jsonObject.get("MaskingRules") != null) {
				JSONArray ja = (JSONArray) jsonObject.get("MaskingRules");
				HashMap<String,HashMap<String,String>> map = new HashMap<String,HashMap<String,String>>();
				
				for(int i=0;i<ja.size();i++) {
					map = (HashMap<String, HashMap<String, String>>) ja.get(i);
					for(String key: map.keySet()) {
						datamap.put(key, map.get(key));
					}
				}
			}
			
			datamap.putAll(readConnectionDetails());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
		return datamap;
		
	
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String,HashMap<String,String>> readConnectionDetails(){
		
		HashMap<String,HashMap<String,String>> datamap = new HashMap<String,HashMap<String,String>>();
		String jsonPath = System.getProperty("user.dir")+"/src/test/resources/testdata/connections.json";
		
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(jsonPath));
			JSONObject jsonObject = (JSONObject) obj;
			//System.out.println(jsonObject.get("Source"));
			
			
			
			if(jsonObject.get("ConnectionsTypes") != null) {
				datamap.put("ConnectionsTypes", (HashMap<String, String>) jsonObject.get("ConnectionsTypes"));
				HashMap<String, String> connections = new HashMap<String, String>();
				connections = datamap.get("ConnectionsTypes");
				for(String key: connections.keySet()) {
					String[] arrOfconns = connections.get(key).split("#");
					for(String conn:arrOfconns ) {
						if(jsonObject.get(conn) !=null) {
							datamap.put(conn, (HashMap<String, String>) jsonObject.get(conn));
						}
					}
				}
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
		return datamap;
		
	
	}

}
