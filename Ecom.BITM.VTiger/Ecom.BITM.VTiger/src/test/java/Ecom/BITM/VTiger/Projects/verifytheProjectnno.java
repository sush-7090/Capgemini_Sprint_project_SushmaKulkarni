package Ecom.BITM.VTiger.Projects;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.HomePage;
import PomUtility.Projects;

public class verifytheProjectnno extends VTiger_BaseClass{
    @Test(groups = {"Functional", "Integration"})
    public void verifyProjectNumberGeneratedTest() throws Exception {

        ExcelUtiility excel = new ExcelUtiility();
        HomePage home = new HomePage(driver);
        Projects project = new Projects(driver);

        String projectName =
                excel.readDataFromExcel("Project", 1, 0);

        String relatedTo =
                excel.readDataFromExcel("Project", 1, 1);


        home.navigateToProject();
        project.clickCreateProject();

        Assert.assertTrue(
            project.isCreateProjectFormDisplayed(),
            "Create Project form did not open"
        );

        project.enterProjectName(projectName);

        String parentWindow = project.clickRelatedToLookup();
        project.selectOrganization(relatedTo);
        project.returnToProjectForm(parentWindow);

        // Fetch Project No before saving
        String projectNo = project.getProjectNumber();

        // Save the project
        project.clickSave();

        Reporter.log(
            "Project created successfully with Project No: " + projectNo,
            true
        );
    }
}



