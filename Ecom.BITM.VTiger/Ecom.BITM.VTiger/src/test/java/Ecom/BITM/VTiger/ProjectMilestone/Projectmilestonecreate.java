package Ecom.BITM.VTiger.ProjectMilestone;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.HomePage;
import PomUtility.ProjectMilestone;

public class Projectmilestonecreate  extends VTiger_BaseClass{
    @Test
    public void createProjectMilestoneTest() throws Exception {

        ExcelUtiility excel = new ExcelUtiility();

        String milestoneName =
                excel.readDataFromExcel("CreateMilestone", 1, 0);

        String projectName =
                excel.readDataFromExcel("CreateMilestone", 1, 1);

        String assignedUser =
                excel.readDataFromExcel("CreateMilestone", 1, 2);

        String milestoneDate =
                excel.readDataFromExcel("CreateMilestone", 1, 3);

        String milestoneDescription =
                excel.readDataFromExcel("CreateMilestone", 1, 4);

        HomePage home = new HomePage(driver);
        ProjectMilestone milestone = new ProjectMilestone(driver);

        // Navigate using the method already present in your HomePage
        home.navigateToProjectMilestone();

        // Open the creation form
        milestone.clickCreateMilestone();

        // Enter Excel data
        milestone.enterMilestoneName(milestoneName);
        milestone.selectRelatedProject(projectName);
        //milestone.selectAssignedUser(assignedUser);
        //milestone.enterMilestoneDate(milestoneDate);
        milestone.enterDescription(milestoneDescription);

        // Save the milestone
        milestone.clickSave();

        Reporter.log(
                "Save button clicked for milestone: " + milestoneName, true);

        Assert.assertTrue(
                driver.getPageSource().contains(milestoneName),
                "Milestone name was not found after saving");

        Reporter.log(
                "Milestone creation test completed", true);
    }
}
