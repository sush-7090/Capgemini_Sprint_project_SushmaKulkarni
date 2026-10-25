package Ecom.BITM.VTiger.BaseClass;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import GenericUtility.FileUtility;
import GenericUtility.webDriverUtility;
import PomUtility.HomePage;
import PomUtility.LoginPage;

public class VTiger_BaseClass {

	public WebDriver driver = null;
	public webDriverUtility wu = new webDriverUtility();
	public FileUtility fi = new FileUtility();

	@BeforeSuite(alwaysRun = true)
	public void dbConnection() {
		Reporter.log("Before Suite", true);
		// db connection
	}

	@BeforeTest(alwaysRun = true)
	public void bt() {
		Reporter.log("Before Test", true);
		// not required
	}

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void navigateTOApp(@Optional("chrome") String browser) throws Exception {
		Reporter.log("Before Class", true);
		driver = wu.launchBrowser(browser);
		driver.get(fi.getDataFromPropertyFile("url"));
	}

	@BeforeMethod(alwaysRun = true)
	public void login() throws Exception {
		Reporter.log("Before Method", true);
		LoginPage lp = new LoginPage(driver);
		lp.login(fi.getDataFromPropertyFile("username"), fi.getDataFromPropertyFile("password"));
	}

	@AfterMethod(alwaysRun = true)
	public void logout() {
		if (driver != null && !driver.getWindowHandles().isEmpty()) {
			for (String window : driver.getWindowHandles()) {
				driver.switchTo().window(window);
				break;
			}

			HomePage home = new HomePage(driver);
			home.userLogout(driver);
		}
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		Reporter.log("After Class", true);
		driver.quit();
	}

	@AfterTest(alwaysRun = true)
	public void at() {
		Reporter.log("After Test", true);
	}

	@AfterSuite(alwaysRun = true)
	public void closeConnection() {
		Reporter.log("After Suite", true);
		// close db connection
	}
}
