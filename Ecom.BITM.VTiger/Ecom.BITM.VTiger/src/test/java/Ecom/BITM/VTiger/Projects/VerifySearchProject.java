package Ecom.BITM.VTiger.Projects;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.HomePage;
import PomUtility.Projects;

public class VerifySearchProject extends VTiger_BaseClass{

	    @Test(groups = {"Functional", "Smoke"})
	    public void verifySearchProjectTest() throws Exception {

	        HomePage home = new HomePage(driver);
	        Projects project = new Projects(driver);
	        ExcelUtiility excel = new ExcelUtiility();

	        String projectName =
	                excel.readDataFromExcel("Project", 1, 0);

	        home.navigateToProject();

	        project.searchProject(projectName);

	        Assert.assertTrue(
	                project.isProjectDisplayed(projectName),
	                "Project was not found in search results"
	        );

	        Reporter.log(
	                "Project search is working successfully: " + projectName,
	                true
	        );
	    }
	}


