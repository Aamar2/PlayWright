package com.infa.playwright;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infa.api.TokenGenerator;
import com.infa.dataprovider.TestDataProvider;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

@Listeners(com.infa.reports.TestListener.class)
public class Example extends TestNGSetup {
	
	String search = "input[name=\"search\"]";
	HashMap<String,String> datamap1 = new HashMap<String,String>();
	
	@Test
	public void TC_002() {
	
		
		cdiutils.loginIICS("avallapreddy_rel", "infa@12345");
		cdiutils.openCDI();
		cdiutils.openCDIMappings();
		
	}
}