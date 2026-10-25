package Ecom.BITM.VTiger.Projects;

import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.HomePage;
import PomUtility.Projects;

public class Verifying_Projectstatus extends VTiger_BaseClass{
	

	    @Test(groups = {"Functional", "System"})
	    public void verifyProjectStatusTest() throws Exception {

	        HomePage home = new HomePage(driver);
	        Projects project = new Projects(driver);
	        ExcelUtiility excel = new ExcelUtiility();

	        String projectName = excel.readDataFromExcel("Project", 1, 0);
	        home.navigateToProject();

	        project.searchProject(projectName);
	        project.clickProjectName(projectName);
	        project.clickEdit();

	        project.selectProjectStatus("In Progress");
	        project.clickSave();

	        Reporter.log( "Project status updated successfully",   true );
	    }
	}
	
