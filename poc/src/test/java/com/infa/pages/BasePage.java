package com.infa.pages;

import com.microsoft.playwright.Page;

public class BasePage {
	
	protected Page page;
	

	BasePage(Page page){
		this.page =page;
		//System.out.println("From BasePage");
	}

}
