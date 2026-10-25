package Ecom.BITM.VTiger.FAQ;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.FAQ;

public class VerifyingBYQuestion extends VTiger_BaseClass {

	@Test(groups = { "Functional", "Integration" })
	public void verifySearchFAQTest() throws Exception {

		ExcelUtiility excel = new ExcelUtiility();
		String question = excel.readDataFromExcel("FAQ", 1, 0);

		FAQ faq = new FAQ(driver);

		faq.navigateToFAQ();
		faq.searchFAQByQuestion(question);

		Assert.assertTrue(faq.isFAQDisplayed(question), "FAQ was not found: " + question);

		Reporter.log("FAQ search by Question successful: " + question, true);
	}

}
