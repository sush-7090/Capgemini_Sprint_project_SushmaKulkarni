package Ecom.BITM.VTiger.Services_Contracts;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.HomePage;
import PomUtility.Services_Contract;

public class Navigation_toSerives extends VTiger_BaseClass {

	@Test(groups = { "Smoke" })
	public void verifyCreateServiceContractTest() throws Exception {

		HomePage home = new HomePage(driver);
		Services_Contract contract = new Services_Contract(driver);
		ExcelUtiility excel = new ExcelUtiility();

		String subject = excel.readDataFromExcel("Service", 1, 0);

		home.navigateToServiceContracts();
	}
}
