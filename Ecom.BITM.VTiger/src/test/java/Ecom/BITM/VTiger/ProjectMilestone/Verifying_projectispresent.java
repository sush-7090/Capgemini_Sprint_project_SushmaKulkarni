package Ecom.BITM.VTiger.ProjectMilestone;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.HomePage;
import PomUtility.ProjectMilestone;

public class Verifying_projectispresent extends VTiger_BaseClass {
	@Test
	public void verifyCreatedProjectMilestoneTes1t() throws Exception {
		ExcelUtiility excel = new ExcelUtiility();

		// Read the same milestone name used in the creation test
		String milestoneName = excel.readDataFromExcel("CreateMilestone", 1, 0);

		HomePage home = new HomePage(driver);
		ProjectMilestone milestone = new ProjectMilestone(driver);

		// Navigate to Project Milestones
		home.navigateToProjectMilestone();
		Assert.assertTrue(milestone.isMilestonePresent(milestoneName),
				"Project Milestone was not created successfully");

		Reporter.log("Project Milestone created and verified successfully", true);

	}

}