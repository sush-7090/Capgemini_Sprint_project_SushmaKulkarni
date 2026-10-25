package Ecom.BITM.VTiger.ProjectTask;

import org.openqa.selenium.Alert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.Project_Task;

public class Delectproject extends  VTiger_BaseClass {
	
	@Test(groups = { "Negative" })
	public void verifyEmptyTaskNameTest1() throws Exception {

		Project_Task task = new Project_Task(driver);
		ExcelUtiility excel = new ExcelUtiility();

		task.navigateToProjectTasks();
		task.checkbox();
		Alert alert = driver.switchTo().alert();

		Reporter.log("Delete confirmation: " + alert.getText(), true);

		alert.accept();
		Reporter.log("Project Task search passed: " , true);
	}
	}
