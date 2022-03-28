package com.infa.pages;

import com.infa.utils.JSCommands;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DIPage extends BasePage{
	
	String newJob = "text=New...";
	String mappings = "#ui-id-4 > .infa-tabLabel";
	String mapping = "#new-asset-dialog-entity-type-mapping > .customNewNameColViewTextCell";
	String createBtn = "//span[text()='Create']";
	
	//String canvas = "//canvas[contains(@id,'canvas')]";
	
	String canvas = "#canvas1";
	
	String DMTtx = "//div[(@class='infa-template-palette-buttonLabel') and (text()='Data Masking')]";
	
	String arrangeAll = "//div[(@class='infaWSContents') and not(contains(@style,'none'))]//header[@class='infaPanel-header']//div[@class='infaPanel-toolbar']//button[@title='Arrange All']";
	
	JSCommands jsCommands ;
	

	DIPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
		page.locator(newJob).waitFor();
		jsCommands = new JSCommands();
	}
	
	public void createNewJob() {
		page.click(newJob);
		page.locator(mappings).waitFor();
	}
	
	public void createMappings() {
		page.click(mappings);
		page.click(mapping);
		page.waitForNavigation(() -> {
	        page.click(createBtn);
	      });
		page.locator(canvas).waitFor();

		try {
			Thread.sleep(10);
		}catch(Exception ex) {
			
		}
		page.click(DMTtx);
			String deleteLink = jsCommands.UnlinkNodes("Source", "Target");
			page.evaluate(deleteLink);
			Locator src = page.locator(DMTtx);
			Locator des = page.locator(canvas);
			//src.boundingBox();
			page.mouse().move(src.boundingBox().x , src.boundingBox().y );
			page.mouse().down();
			page.mouse().move(des.boundingBox().x , des.boundingBox().y );
			page.mouse().down();
			page.click(canvas);;
			
			String linkSrcDmt = jsCommands.LinkNodes("Source", "DataMasking");
			page.evaluate(linkSrcDmt);
			
			String linkTgtDmt = jsCommands.LinkNodes("DataMasking", "Target");
			page.evaluate(linkTgtDmt);
			
			page.click(arrangeAll);
			page.click(canvas);
			//page.pause();
		
		
		
	}

}
