package Ecom.BITM.VTiger.Services_Contracts;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import PomUtility.Services_Contract;

public class VerifyCancelService extends VTiger_BaseClass {

	@Test(groups = { "Functional" })
	public void verifyCancelServiceContractTest() {

		Services_Contract contract = new Services_Contract(driver);

		contract.navigateToServiceContracts();
		contract.clickCreateContract();

		Assert.assertTrue(contract.isCreateContractFormDisplayed(), "Create Service Contract form did not open");

		contract.clickCancel();

		Assert.assertTrue(contract.isContractFormClosed(), "Create Service Contract form did not close after Cancel");

		Reporter.log("Cancel button closed the Service Contract form successfully", true);
	}
}
