package Ecom.BITM.VTiger.Projects;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ScreenShotUtility;
import PomUtility.HomePage;
import PomUtility.Projects;

public class VerificationThatProjectName extends VTiger_BaseClass {

	@Test(groups = { "Negative", "Functional" })
	public void verifyProjectNameRequiredTest() throws Exception {

		HomePage home = new HomePage(driver);
		Projects project = new Projects(driver);

		home.navigateToProject();
		project.clickCreateProject();

		Assert.assertTrue(project.isCreateProjectFormDisplayed(), "Create Project form did not open");

		// Leave Project Name empty and click Save
		project.clickSave();

		// Wait for the JavaScript alert
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		// Read the alert message
		String alertMessage = alert.getText();

		Reporter.log("Validation alert: " + alertMessage, true);

		// IMPORTANT: Accept alert before taking screenshot
		alert.accept();

		// Now capture the screenshot
		ScreenShotUtility.takeScreenshot(driver, "MentionProjectName");

		Reporter.log("Screenshot captured after handling validation alert", true);

		// Verify expected validation message
		Assert.assertEquals(alertMessage, "Project Name cannot be empty", "Unexpected validation message");

		Reporter.log("Project Name negative test passed successfully", true);
	}
}
