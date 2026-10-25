package Ecom.BITM.VTiger.ProjectTask;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.Project_Task;
import PomUtility.Projects;

public class Create_Project_Task  extends VTiger_BaseClass {

	@Test(groups = {"Smoke"})
	public void verifyCreateProjectTaskTest() throws Exception {

	    Project_Task task = new Project_Task(driver);
	    ExcelUtiility excel = new ExcelUtiility();

	    String taskName =
	        excel.readDataFromExcel("task", 1, 0);

	    String relatedTo = excel.readDataFromExcel("task", 1, 1);

	    task.navigateToProjectTasks();
	    task.clickCreateTask();

	    Assert.assertTrue(
	        task.isCreateTaskFormDisplayed(),
	        "Create Project Task form did not open"
	    );

	    task.enterTaskName(taskName);

	    // Select the type of related record
	    //task.selectRelatedToType("Projects");

	    // Open lookup and select an existing project
	    String parentWindow = task.clickRelatedToLookup();

	    task.selectRelatedRecord(relatedTo);

	    task.returnToTaskForm(parentWindow);

	    // Save the task
	    task.clickSave();

	    Reporter.log(
	        "Project Task creation submitted: " + taskName,
	        true
	    );
	}
}
