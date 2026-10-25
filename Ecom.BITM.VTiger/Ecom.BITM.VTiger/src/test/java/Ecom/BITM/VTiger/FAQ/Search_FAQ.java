package Ecom.BITM.VTiger.FAQ;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import GenericUtility.ScreenShotUtility;
import PomUtility.FAQ;

public class Search_FAQ extends VTiger_BaseClass {

	@Test(groups = { "Functional" })
	public void verifySearchFAQTest() throws Exception {

		ExcelUtiility excel = new ExcelUtiility();
		String question = excel.readDataFromExcel("FAQ", 1, 0);

		FAQ faq = new FAQ(driver);

		faq.navigateToFAQ();
		faq.searchFAQ(question);

		ScreenShotUtility.takeScreenshot(driver, "FAQNotExist");

		Assert.assertTrue(faq.isFAQDisplayed(question), "FAQ was not found: " + question);

		Reporter.log("FAQ search successful: " + question, true);
	}
}
