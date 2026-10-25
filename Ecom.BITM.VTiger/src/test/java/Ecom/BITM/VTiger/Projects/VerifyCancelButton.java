package Ecom.BITM.VTiger.Projects;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import PomUtility.HomePage;
import PomUtility.Projects;

public class VerifyCancelButton extends VTiger_BaseClass{

	@Test(groups = {"Functional", "Smoke"})
	public void verifyCancelProjectTest() {
	    HomePage home = new HomePage(driver);
	    Projects project = new Projects(driver);

	    home.navigateToProject();
	    project.clickCreateProject();

	    Assert.assertTrue(
	        project.isCreateProjectFormDisplayed(),
	        "Create Project form did not open"
	    );

	    project.enterProjectName("Cancel_Project_Test");
	    project.clickCancel();

	    Assert.assertTrue(
	        project.isProjectFormClosed(),
	        "Create Project form did not close after clicking Cancel"
	    );

	    Reporter.log("Cancel button closed the Create Project form successfully", true);
	}
}

