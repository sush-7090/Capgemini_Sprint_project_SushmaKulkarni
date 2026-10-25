package Ecom.BITM.VTiger.ProjectMilestone;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.HomePage;
import PomUtility.ProjectMilestone;

public class Verifying_projectMileStone extends VTiger_BaseClass {
	@Test
	public void verifyCreatedProjectMilestoneTest() throws Exception {

		ExcelUtiility excel = new ExcelUtiility();

		// Read the same milestone name used in the creation test
		String milestoneName = excel.readDataFromExcel("CreateMilestone", 1, 0);

		HomePage home = new HomePage(driver);
		ProjectMilestone milestone = new ProjectMilestone(driver);

		// Navigate to Project Milestones
		home.navigateToProjectMilestone();

		// Search for the previously created milestone
		milestone.searchMilestone(milestoneName);

		// Verify the milestone exists
		Assert.assertTrue(milestone.isMilestonePresent(milestoneName),
				"Project milestone was not found: " + milestoneName);

		Reporter.log("Project milestone is present: " + milestoneName, true);
	}
}
