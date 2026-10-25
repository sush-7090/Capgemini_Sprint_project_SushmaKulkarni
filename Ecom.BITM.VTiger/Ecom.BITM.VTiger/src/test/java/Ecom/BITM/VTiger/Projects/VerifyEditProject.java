package Ecom.BITM.VTiger.Projects;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.HomePage;
import PomUtility.Projects;

public class VerifyEditProject extends VTiger_BaseClass {

	@Test(groups = { "Functional", "System" })
	public void verifyEditProjectTest() throws Exception {

		HomePage home = new HomePage(driver);
		Projects project = new Projects(driver);
		ExcelUtiility excel = new ExcelUtiility();

		String oldProjectName = excel.readDataFromExcel("Project", 1, 0);

		String updatedProjectName = oldProjectName + "_Updated";

		home.navigateToProject();

		// Search for the existing project
		project.searchProject(oldProjectName);

		// Click the project name in the search results
		project.clickProjectName(oldProjectName);

		// Click Edit only after the project details page opens
		project.clickEdit();

		// Update the project name
		project.updateProjectName(updatedProjectName);

		// Save changes
		project.clickSave();
		;

		Reporter.log("Project name updated successfully", true);
	}
}