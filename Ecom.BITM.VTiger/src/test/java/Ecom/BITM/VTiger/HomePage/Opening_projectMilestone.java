package Ecom.BITM.VTiger.HomePage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import PomUtility.HomePage;

public class Opening_projectMilestone extends VTiger_BaseClass {

	@Test(groups = {"Smoke", "Functional"})
	public void verifyProjectMilestoneNavigation() {

		HomePage home = new HomePage(driver);

		home.navigateToProjectMilestone();
		Assert.assertTrue(driver.getCurrentUrl().contains("ProjectMilestone"),"Login failed - ProjectMilestone Page is not displayed");
	     String url=driver.getCurrentUrl();
	     System.out.println(url);
		Reporter.log("Project Milestone page is displayed");
	}
	
	@Test(groups = {"Smoke", "Functional"})
	public void OpeningOrganization() {

		HomePage home = new HomePage(driver);

		home.clickOrganization();
		Assert.assertTrue(driver.getCurrentUrl().contains("Accounts"),"Login failed - ProjectMilestone Page is not displayed");
		String url=driver.getCurrentUrl();
	     System.out.println(url);
		Reporter.log("Organization page is displayed");
}
	@Test(groups = {"Smoke", "Functional"})
	public void OpeningContacts() {

		HomePage home = new HomePage(driver);

		home.clickContacts();
		Assert.assertTrue(driver.getCurrentUrl().contains("Contacts"),"Login failed - ProjectMilestone Page is not displayed");
		String url=driver.getCurrentUrl();
	     System.out.println(url);
		Reporter.log("Contacts page is displayed");
	}
	
	@Test(groups = {"Smoke", "Functional"})
	public void OpeningEmail() {

		HomePage home = new HomePage(driver);

		home.clickEmail();
		Assert.assertTrue(driver.getCurrentUrl().contains("Emails"),"Login failed - ProjectMilestone Page is not displayed");
		String url=driver.getCurrentUrl();
	     System.out.println(url);
		Reporter.log("Email page is displayed");
	}
}
