package com.infa.funcUtils;

import com.infa.pages.AdminPage;
import com.infa.pages.DIPage;
import com.infa.pages.LoginPage;
import com.microsoft.playwright.Page;

public class CDIFuncUtils {
	
	public static Page page;
	
	public LoginPage login;
	
	public AdminPage adminpage;
	
	public DIPage dipage;
	
	
	public void loginIICS(String username, String password) {
		login = new LoginPage(this.page);
		adminpage = login.loginIntoIICS(username, password);
	}
	
	public void openCDI() {
		adminpage.openChikletOptions();
		dipage = adminpage.openCDI();
	}
	
	public void openCDIMappings() {
		dipage.createNewJob();
		dipage.createMappings();
	}

}
