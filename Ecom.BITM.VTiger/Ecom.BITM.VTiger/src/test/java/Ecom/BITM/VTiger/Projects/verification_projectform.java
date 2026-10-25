package Ecom.BITM.VTiger.Projects;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import PomUtility.HomePage;
import PomUtility.Projects;

public class verification_projectform extends VTiger_BaseClass{

    @Test(groups = {"Smoke", "System"})
    public void verifyCreateProjectFormTest() {

        HomePage home = new HomePage(driver);
        Projects project = new Projects(driver);

        // Navigate to the Project module
        home.navigateToProject();

        // Click the Create Project button
        project.clickCreateProject();

        // Verify that the form is displayed
        Assert.assertTrue(
            project.isCreateProjectFormDisplayed(),
            "Create Project form did not open"
        );

        Reporter.log("Create Project form opened successfully", true);
    }
}



