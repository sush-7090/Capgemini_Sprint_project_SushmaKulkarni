package Ecom.BITM.VTiger.Projects;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.HomePage;
import PomUtility.Projects;

public class Create_Project extends VTiger_BaseClass {

    @Test(groups = {"Functional", "Integration"})
    public void createProjectTest() throws Exception {

        // Create objects
        ExcelUtiility excel = new ExcelUtiility();
        HomePage home = new HomePage(driver);
        Projects project = new Projects(driver);

        // Read data from Excel
        String projectName =
                excel.readDataFromExcel("Project", 1, 0);

        String relatedTo =
                excel.readDataFromExcel("Project", 1, 1);

        // Navigate to Projects module
        home.navigateToProject();

        Reporter.log("Projects module opened successfully", true);

        // Click Create Project button
        project.clickCreateProject();

        Assert.assertTrue(
                project.isCreateProjectFormDisplayed(),
                "Create Project form did not open"
        );

        Reporter.log("Create Project form opened successfully", true);

        // Enter Project Name from Excel
        project.enterProjectName(projectName);

        Reporter.log("Entered Project Name: " + projectName, true);

        // Open Related To lookup popup
        String parentWindow = project.clickRelatedToLookup();

        Reporter.log("Related To lookup popup opened", true);

        // Select Organization from Excel
        project.selectOrganization(relatedTo);

        Reporter.log("Selected Organization: " + relatedTo, true);

        // Switch back to Create Project form
        project.returnToProjectForm(parentWindow);

        // Verify selected Organization
        //Assert.assertEquals(
//                project.getRelatedToValue().trim(),
//                relatedTo.trim(),
//                "Related To organization was not selected correctly"
//        );

       // Reporter.log("Related To verified successfully", true);

        // Save Project
        project.clickSave();

        Reporter.log("Project Save button clicked", true);

        // Verify that the creation form has closed
        Assert.assertFalse(
                project.isCreateProjectFormDisplayed(),
                "Project creation form is still displayed after saving"
        );

        Reporter.log("Project creation submitted successfully: "
                + projectName, true);
    }
}

