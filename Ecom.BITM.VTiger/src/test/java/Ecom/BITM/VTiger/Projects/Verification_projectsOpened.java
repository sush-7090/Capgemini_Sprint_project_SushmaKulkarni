package Ecom.BITM.VTiger.Projects;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import PomUtility.HomePage;

public class Verification_projectsOpened extends VTiger_BaseClass {

	@Test(groups = { "Functional", " Integration" })
	public void verifyProjectModuleOpenedTest() {

		HomePage home = new HomePage(driver);

		home.navigateToProject();

		Assert.assertTrue(driver.getCurrentUrl().contains("Project"), "Project module did not open successfully");

		Reporter.log("Project module opened successfully", true);
	}
}
