package com.infa.api;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequest.NewContextOptions;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class TokenGenerator {
	
	public static String token;
	
	public static String sessionId;
	
	public static Playwright playwright;
	public static APIRequestContext iicsrequest;
	public static APIRequestContext cdprequest;
	
	
	public static void createPlaywright() {
		    playwright = Playwright.create();
		    iicsrequest = playwright.request().newContext(new APIRequest.NewContextOptions()
		    	      .setIgnoreHTTPSErrors(true).setBaseURL("https://ids.ics.dev"));
		   
	}
	
	public static void getSessionId(String userName,String passWord) {
		Map<String, String> data = new HashMap<String, String>();
	    data.put("username", "slittle112452@informatica.com");
	    data.put("password", "infa@1234");
	    
	    APIResponse newIssue = iicsrequest.post("/identity-service/api/v1/Login",
	    	      RequestOptions.create().setData(data));
	    JsonObject json = new Gson().fromJson(newIssue.text(), JsonObject .class);
	    
	    sessionId = json.get("sessionId").getAsString();
	    	      
		
	}
	
	public static void gettoken() {
		Map<String, String> headers = new HashMap<>();
		
		createPlaywright();
		if(sessionId == null) {
			getSessionId("", "");
		}
	    headers.put("Cookie", "USER_SESSION="+ sessionId +"; XSRF_TOKEN="+ sessionId +";");
	    headers.put("XSRF_TOKEN", sessionId);
	    cdprequest = playwright.request().newContext(new APIRequest.NewContextOptions()
	    	      .setIgnoreHTTPSErrors(true).setBaseURL("http://10.65.41.238:30120").setExtraHTTPHeaders(headers));	    	      
	    APIResponse newRepo = cdprequest.post("/ccgf-authorization/api/v1/authorization/token",
	    	      RequestOptions.create());
	    JsonObject json = new Gson().fromJson(newRepo.text(), JsonObject .class);
	    
	    token=json.get("token").getAsString();
	}
	
	public static void main(String[] args) {
		if(token == null) {
			gettoken();
		}
		System.out.println(token);

	}

}
