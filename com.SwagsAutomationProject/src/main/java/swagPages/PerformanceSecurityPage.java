package swagPages;

import org.openqa.selenium.WebDriver;

public class PerformanceSecurityPage {

	 WebDriver driver;

	    public PerformanceSecurityPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public long getPageLoadTime(String url) {
	        long start = System.currentTimeMillis();
	        driver.get(url);
	        long finish = System.currentTimeMillis();
	        return finish - start;
	    }

	    public boolean isConnectionSecure() {
	        return driver.getCurrentUrl().startsWith("https://");
	    }
}
