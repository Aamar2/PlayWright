package com.infa.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class AdminPage extends BasePage {
	
	String settingsLnk = "li:nth-child(5) > .d-shell__nav__links__link";
	
	String optionsBtn = ".shell__header__switcher > .aicon";
	
	String diChiklet = "//span[text()='Data Integration']";
	
	AdminPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
		page.locator(settingsLnk).waitFor();
	}
	
	public void openChikletOptions() {
		page.click(optionsBtn);
		
	}
	
	public DIPage openCDI() {
		page.waitForNavigation(() -> {
	        page.click(diChiklet);
	      });
		
		page.waitForLoadState(LoadState.NETWORKIDLE);
		return new DIPage(page);
	}
	
	

}
