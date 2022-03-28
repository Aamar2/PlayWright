package com.infa.playwright;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.*;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infa.api.TokenGenerator;
import com.infa.dataprovider.TestDataProvider;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.filter.log.LogDetail;

@Listeners(com.infa.reports.TestListener.class)
public class SampleTest extends TestNGSetup {
	
	String search = "input[name=\"search\"]";
	HashMap<String,String> datamap1 = new HashMap<String,String>();
	
	@Test
	public void TC_001() {
		test.log(Status.PASS, "PASS TEST");
		cdiutils.loginIICS("avallapreddy_rel", "infa@12345");
		cdiutils.openCDI();
		//cdiutils.openCDIMappings();
		
	}
	
	
	@Test
	public void TC_002() {
		test.log(Status.PASS, "SECOND TEST");
		cdiutils.loginIICS("avallapreddy_rel", "infa@12345");
		cdiutils.openCDI();
		//cdiutils.openCDIMappings();
		
	}
	
	@Test
	public void TC_003() {
		test.log(Status.PASS, "SECOND TEST");
		cdiutils.loginIICS("avallapreddy_rel", "infa@12345");
		cdiutils.openCDI();
		//cdiutils.openCDIMappings();
		
	}
	
	//@Test
	public void apitest() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+TokenGenerator.token);
		APIRequestContext cdprequest = playwright.request().newContext(new APIRequest.NewContextOptions()
	    	      .setIgnoreHTTPSErrors(true).setBaseURL("http://10.65.41.238:30600").setExtraHTTPHeaders(headers));
		APIResponse newRepo = cdprequest.get("/ccgf-privacy-admin/api/v1/lookupTypes/sensitivityStatuses");
	    	      
		JsonArray json = new Gson().fromJson(newRepo.text(), JsonArray.class);
	    
	    for(JsonElement item: json){
	    	JsonObject itemObj = item.getAsJsonObject();
	    	System.out.println(itemObj.get("code").getAsString());
	    }
	}
	
	//@Test
	public void apitest2() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+TokenGenerator.token);
		APIRequestContext cdprequest = playwright.request().newContext(new APIRequest.NewContextOptions()
	    	      .setIgnoreHTTPSErrors(true).setBaseURL("http://10.65.41.238:30600").setExtraHTTPHeaders(headers));
		APIResponse newRepo = cdprequest.get("/ccgf-privacy-admin/api/v1/dataCategories/export");
		try {
			FileOutputStream fi = new FileOutputStream(new File("c:\\aaa\\response.csv"));
			byte[] strToBytes = newRepo.text().getBytes();
			fi.write(strToBytes);
		}catch(Exception ex) {
			
		}
	          
		System.out.println(newRepo.text());
	    

	}
}
