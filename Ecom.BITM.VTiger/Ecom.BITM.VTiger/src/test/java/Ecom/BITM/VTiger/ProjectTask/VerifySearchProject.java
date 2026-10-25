package Ecom.BITM.VTiger.ProjectTask;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.Project_Task;

public class VerifySearchProject extends VTiger_BaseClass {

	@Test(groups = { "Functional" })
	public void verifySearchProjectTaskTest() throws Exception {

		Project_Task task = new Project_Task(driver);
		ExcelUtiility excel = new ExcelUtiility();

		String taskName = excel.readDataFromExcel("task", 1, 0);

		task.navigateToProjectTasks();
		task.searchProjectTask(taskName);

		Assert.assertTrue(task.isTaskDisplayed(taskName), "Project Task was not found: " + taskName);

		Reporter.log("Project Task search passed: " + taskName, true);
	}
}