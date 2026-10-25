package Ecom.BITM.VTiger.Services_Contracts;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.Services_Contract;

public class Create_ServiceContracts extends VTiger_BaseClass {

	@Test(groups = { "Smoke", "Functional" })
	public void verifyCreateServiceContractTest() throws Exception {

		Services_Contract contract = new Services_Contract(driver);
		ExcelUtiility excel = new ExcelUtiility();

		String subject = excel.readDataFromExcel("Service", 1, 0);

		contract.navigateToServiceContracts();

		contract.clickCreateContract();

		Assert.assertTrue(contract.isCreateContractFormDisplayed(), "Create Service Contract form did not open");

		contract.enterSubject(subject);
		contract.clickSave();

		String actualSubject = contract.getSavedSubject();

		Assert.assertEquals(actualSubject, subject, "Service Contract was not saved correctly");

		Reporter.log("Service Contract created successfully: " + actualSubject, true);
	}
}
