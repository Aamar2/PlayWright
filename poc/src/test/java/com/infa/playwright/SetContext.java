package com.infa.playwright;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;


public class SetContext {
	private static ThreadLocal<BrowserContext> context = new ThreadLocal<BrowserContext>();
	
    private static ThreadLocal<Boolean> isContextInitialized = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };
    
    private static ThreadLocal<Throwable> exception = new ThreadLocal<Throwable>();
    
    public static BrowserContext getBrowserContext() {
          if (context.get() == null) {
            if (isContextInitialized.get()) {
                try {
					throw new Exception(
					        "Unable to start the driver or Driver was killed unexpectedly");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            try {
            	setBrowserContext(createBrowserContext());
            } catch (Throwable e) {
                exception.set(e);
            }
        }
        return context.get();
    }
	
	  public static BrowserContext createBrowserContext() {
		 //Playwright playwright = Playwright.create();
			 //playwright = SetPlay.getPlayWright(); 
		Browser browser = TestNGSetup.playwright.chromium().launch(new BrowserType.LaunchOptions()
				        .setHeadless(false));
		 return browser.newContext();

	  }
	  
	  
	    public static void setBrowserContext(BrowserContext context) {
	    	SetContext.context.set(context);
	    	isContextInitialized.set(Boolean.TRUE);
	    }
	    
	    public static boolean hasPage() {
	        return (null != context.get());
	    }
	    
	    public static boolean removeBrowserContext() {
	        if (isContextInitialized.get()) {
	            if (null != context.get()) {
	            	context.get().close();
	            	isContextInitialized.set(Boolean.FALSE);
	            }
	        }
	        return isContextInitialized.get();
	    }



}
