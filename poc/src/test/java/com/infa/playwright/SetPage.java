package com.infa.playwright;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SetPage {
	private static  ThreadLocal<Page> page = new ThreadLocal<Page>();

	
    private static ThreadLocal<Boolean> isPageInitialized = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };
    
    private static ThreadLocal<Throwable> exception = new ThreadLocal<Throwable>();
    
    public static Page getPage() {
//          if (page.get() == null) {
//            if (isPageInitialized.get()) {
//                try {
//					throw new Exception(
//					        "Unable to start the driver or Driver was killed unexpectedly");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//            try {
//            	setPage(createPage());
//            } catch (Throwable e) {
//                exception.set(e);
//            }
//        }
    	setPage(createPage());
        return page.get();
    }
	
	 public static Page createPage() {
		 Page localpage = null;
		 Playwright playwright = Playwright.create();
		 Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
		        .setHeadless(false));
		 BrowserContext context = browser.newContext();
		 localpage = context.newPage();
		 return localpage;
	  }
	  
	  
	    public static void setPage(Page page) {
	         SetPage.page.set(page);
	         isPageInitialized.set(Boolean.TRUE);
	    }
	    
	    public static boolean hasPage() {
	        return (null != page.get());
	    }
	    
	    public static boolean removePage() {
	        if (isPageInitialized.get()) {
	            if (null != page.get()) {
	                page.get().close();
	                //playwright.close();
	                isPageInitialized.set(Boolean.FALSE);
	            }
	        }
	        return isPageInitialized.get();
	    }

}
