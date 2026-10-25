package Ecom.BITM.VTiger.Services_Contracts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ScreenShotUtility;
import PomUtility.Services_Contract;

public class VerifyInvalidSearch extends VTiger_BaseClass {

	@Test(groups = { "Negative", "Functional" })
	public void verifyInvalidContractSearchTest() throws IOException {

		Services_Contract contract = new Services_Contract(driver);

		String invalidSubject = "NoSuchContract_987654";

		contract.navigateToServiceContracts();

		contract.searchContract(invalidSubject);

		ScreenShotUtility.takeScreenshot(driver, "invaildsearchproject");

		Assert.assertFalse(contract.isContractDisplayed(invalidSubject),"Unexpected contract was found for a non-existing Subject");

		Reporter.log("Negative search passed: no contract found for " + invalidSubject, true);
	}
}
