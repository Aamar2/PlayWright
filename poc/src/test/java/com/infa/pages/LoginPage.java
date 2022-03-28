package com.infa.pages;

import com.infa.playwright.TestNGSetup;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class LoginPage extends BasePage{
	
	String userName = "//input[@type='text']";
	String passWord = "#password > .infaField";
	String loginBtn = "//button[@class='infaButton infaButton-1']";

	public LoginPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
		TestNGSetup.getPage().navigate("https://qa-ma.rel.infaqa.com/identity-service/home");
		
	}
	
	public AdminPage loginIntoIICS(String username, String PassWord) {
		page.fill(userName, "avallapreddy_rel");
		page.fill(passWord, "infa@12345");
		page.pause();
		page.waitForNavigation(() -> {
	        page.press("//button[@class='infaButton infaButton-1']", "Enter");
		});
		page.waitForLoadState(LoadState.NETWORKIDLE);
		//page.waitfor
		
		return new AdminPage(page);
	}
	
}
