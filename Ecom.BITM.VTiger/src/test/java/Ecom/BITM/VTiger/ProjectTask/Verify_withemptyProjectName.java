package Ecom.BITM.VTiger.ProjectTask;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import GenericUtility.ScreenShotUtility;
import PomUtility.Project_Task;

public class Verify_withemptyProjectName extends VTiger_BaseClass {

	@Test(groups = { "Negative" })
	public void verifyEmptyTaskNameTest() throws Exception {

		Project_Task task = new Project_Task(driver);
		ExcelUtiility excel = new ExcelUtiility();

		task.navigateToProjectTasks();
		task.clickCreateTask();

		Assert.assertTrue(task.isCreateTaskFormDisplayed(), "Create Project Task form did not open");

		// Leave Task Name empty
		task.clearTaskName();

		// Related To is mandatory, so select an existing project
		// using your existing lookup methods before saving.
		// task.selectRelatedToType("Projects");

		String parentWindow = task.clickRelatedToLookup();
		String relatedTo = excel.readDataFromExcel("task", 1, 1);
		task.selectRelatedRecord(relatedTo);

		task.returnToTaskForm(parentWindow);
		task.clickSave();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		String alertMessage = alert.getText();

		Reporter.log("Validation message: " + alertMessage, true);

		alert.accept();
		ScreenShotUtility.takeScreenshot(driver, "EmptyTaskName");

		Assert.assertTrue(alertMessage.toLowerCase().contains("task"),"Expected Task Name validation message was not displayed");

		Reporter.log("Negative test passed: empty Task Name was rejected", true);
	}
}
