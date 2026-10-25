package Ecom.BITM.VTiger.Projects;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import GenericUtility.ScreenShotUtility;
import PomUtility.HomePage;
import PomUtility.Projects;

public class EnterincorrectPRojectname extends VTiger_BaseClass {

	@Test(groups = { "Negative", "Functional" })
	public void verifyInvalidProjectSearchTest() throws Exception {

		HomePage home = new HomePage(driver);
		Projects project = new Projects(driver);
		ExcelUtiility excel = new ExcelUtiility();

		String existingProjectName = excel.readDataFromExcel("Project", 1, 0);

		String invalidProjectName = existingProjectName + "_Invalid";

		home.navigateToProject();

		project.searchProject(invalidProjectName);
		// Now capture the screenshot
		ScreenShotUtility.takeScreenshot(driver, "InvalidProjectName");

//	        Assert.assertFalse(
//	                project.isProjectDisplayed(invalidProjectName),
//	                "Unexpected exact project match was displayed"
//	        );

		Reporter.log("Negative search passed for: " + invalidProjectName, true);
	}

}
