package Ecom.BITM.VTiger.ProjectMilestone;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.HomePage;
import PomUtility.ProjectMilestone;

public class OpeningProjectMileStone extends VTiger_BaseClass {

	@Test
	public void verifyProjectMilestoneDetailsTest() throws Exception {

		ExcelUtiility excel = new ExcelUtiility();

		String milestoneName = excel.readDataFromExcel("CreateMilestone", 1, 0);

		HomePage home = new HomePage(driver);
		ProjectMilestone milestone = new ProjectMilestone(driver);

		// Navigate to Project Milestones

		home.navigateToProjectMilestone();

		milestone.clickCreatedMilestone(milestoneName);

		//Assert.assertTrue(milestone.isMilestoneDetailsPageOpened(milestoneName),"Project Milestone details page did not open");
	}
}
