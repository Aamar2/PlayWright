package com.infa.dataprovider;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.DataProvider;

import com.infa.utils.ExcelReader;
import com.infa.utils.JsonTestDataReader;

public class TestDataProvider {
	
	static HashMap<String,HashMap<String,String>> dataMap;
	static {
		ExcelReader ex = new ExcelReader("C:\\1040\\TestData.xlsx","Resource");
		try {
			if(dataMap ==null) {
				dataMap = ex.readExcelData();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DataProvider(name = "testdata")
	public static Object[][] parseJsonData(Method m){
		
		Object[][] res = null;
		JsonTestDataReader jr = new JsonTestDataReader();
		ArrayList<HashMap<String, HashMap<String, String>>> tcItrList = new ArrayList<HashMap<String, HashMap<String, String>>>();
		tcItrList.add(jr.readJson_testData(m));
		res = new Object[1][1];
		res[0][0] = tcItrList.get(0);
		return res;
		
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	@DataProvider(name = "testdataforpoc")
	public static Object[][] parseExcelData(Method m){
		
		Object[][] res = null;
		ArrayList<HashMap<String, String>> tcItrList = new ArrayList<HashMap<String, String>>();
		if(dataMap.containsKey(m.getName())) {
			tcItrList.add(dataMap.get(m.getName()));
		}
		
		res = new Object[1][1];
		res[0][0] = tcItrList.get(0);
		return res;
		
	}

}
