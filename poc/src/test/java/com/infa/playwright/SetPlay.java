package com.infa.playwright;


import com.microsoft.playwright.Playwright;

public class SetPlay {
	private static ThreadLocal<Playwright> playwright = new ThreadLocal<Playwright>();
	
    private static ThreadLocal<Boolean> isPlayWrightInitialized = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };
    
    private static ThreadLocal<Throwable> exception = new ThreadLocal<Throwable>();
    
    public static Playwright getPlayWright() {
          if (playwright.get() == null) {
            if (isPlayWrightInitialized.get()) {
                try {
					throw new Exception(
					        "Unable to start the driver or Driver was killed unexpectedly");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            try {
            	setPlayWright(createPlaywright());
            } catch (Throwable e) {
                exception.set(e);
            }
        }
        return playwright.get();
    }
	
	  public static Playwright createPlaywright() {
		 Playwright page = null;
		 page = Playwright.create();
		 return page;
	  }
	  
	  
	    public static void setPlayWright(Playwright playwright) {
	    	SetPlay.playwright.set(playwright);
	         isPlayWrightInitialized.set(Boolean.TRUE);
	    }
	    
	    public static boolean hasPage() {
	        return (null != playwright.get());
	    }
	    
	    public static boolean removePlayWright() {
	        if (isPlayWrightInitialized.get()) {
	            if (null != playwright.get()) {
	            	playwright.get().close();
	            	isPlayWrightInitialized.set(Boolean.FALSE);
	            }
	        }
	        return isPlayWrightInitialized.get();
	    }



}
