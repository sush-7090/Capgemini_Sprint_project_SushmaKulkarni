package Ecom.BITM.VTiger.Services_Contracts;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ScreenShotUtility;
import PomUtility.Services_Contract;

public class VerifyEmptySubject extends VTiger_BaseClass {

	@Test(groups = { "Negative", "Functional" })
	public void verifyEmptySubjectTest() throws IOException {

		Services_Contract contract = new Services_Contract(driver);

		contract.navigateToServiceContracts();
		contract.clickCreateContract();

		Assert.assertTrue(contract.isCreateContractFormDisplayed(), "Create Service Contract form did not open");

		// Leave the mandatory Subject field empty
		contract.clearSubject();

		// Click Save
		contract.clickSave();

		// Verify validation alert, if the application displays one
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		String alertMessage = alert.getText();

		Reporter.log("Validation message: " + alertMessage, true);

		alert.accept();
		
		ScreenShotUtility.takeScreenshot(driver, "EmptyProjectName");

		Assert.assertTrue(alertMessage.toLowerCase().contains("subject"),"Expected Subject validation message was not displayed");

		Reporter.log("Negative test passed: empty Subject was rejected", true);
	}
}
