package GenericUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class webDriverUtility {
	WebDriver driver=null;
	public WebDriver launchBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome"))
			 driver = new ChromeDriver();
		
		else if(browser.equalsIgnoreCase("edge"))
			 driver = new EdgeDriver();
		
		else if(browser.equalsIgnoreCase("firefox"))
			 driver = new FirefoxDriver();
		else
			 driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		genericWait(driver);
		
		return driver;
	}
	public void genericWait(WebDriver d) {
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	public void moveToAnElement(WebDriver driver,WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
	}

}
