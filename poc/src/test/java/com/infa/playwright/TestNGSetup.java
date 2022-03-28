package com.infa.playwright;


import java.lang.reflect.Method;
import java.net.URISyntaxException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.infa.reports.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.infa.api.TokenGenerator;
import com.infa.funcUtils.CDIFuncUtils;
import com.microsoft.playwright.*;

public class TestNGSetup {
	
	protected static ThreadLocal<Page> page = new ThreadLocal<>();
	
	public CDIFuncUtils cdiutils = new CDIFuncUtils();
	
	ExtentTest test;
	
	protected String methodName;
	
	 // Shared between all tests in this class.
	  static Playwright playwright;
	  static Browser browser;

	  // New instance for each test method.
	  static BrowserContext context;
	 
	  
	  
	  
	  @BeforeSuite
	  public void beforeSuite() {
		  test = ExtentTestManager.getTest();
		  //playwright = Playwright.create();
		 // browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
		//	        .setHeadless(false));
//		  context = browser.newContext();
		  //cdiutils = new CDIFuncUtils();
	  }
	  
	  @BeforeClass
	  public void beforeClass() {
		  //test.assignCategory("ApiTest");
		  
	  }
	  
	  @BeforeMethod(alwaysRun = true)
	  public void beforeMethod(Method m) {
		 this.methodName = m.getName();
		 //SetContext.getBrowserContext();
		 try {
			launchApp();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 if(TokenGenerator.token == null) {
			 //TokenGenerator.gettoken();
		 }

	  }
	  
	  @AfterMethod
	  public void closeContext() {
		  page.get().close();
	   
	  }
	  
	  @AfterSuite
	  static void closeBrowser() {
		//context.close();
	    //playwright.close();
		 // SetPlay.removePlayWright();
	  }
	  
	    public static void launchApp() throws URISyntaxException {
	    	page.set(SetPage.getPage());
	    	CDIFuncUtils.page = page.get();
	    	page.get().setDefaultTimeout(200000);
	    	//page.get().navigate("https://qa-ma.rel.infaqa.com/identity-service/home");
	    }
	    
	    public static Page getPage() {
	 	   return page.get();
	    }

}
