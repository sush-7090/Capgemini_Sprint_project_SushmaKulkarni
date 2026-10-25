package Ecom.BITM.VTiger.FAQ;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import PomUtility.FAQ;

public class Navigate_FAQ extends VTiger_BaseClass {

	@Test(groups = { "Smoke" })
	public void verifyFAQModuleTest() {

		FAQ faq = new FAQ(driver);

		faq.navigateToFAQ();

		Assert.assertTrue(faq.isFAQPageDisplayed(), "FAQ module did not open successfully");

		Reporter.log("FAQ module opened successfully", true);
	}
}
