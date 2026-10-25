package Ecom.BITM.VTiger.FAQ;

import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;
import GenericUtility.ExcelUtiility;
import PomUtility.FAQ;

public class Create_FAQ extends VTiger_BaseClass {

    @Test(groups = {"Functional"})
    public void verifyCreateFAQTest() throws Exception {

        ExcelUtiility excel = new ExcelUtiility();

        String question = excel.readDataFromExcel("FAQ", 1, 0);
        String answer = excel.readDataFromExcel("FAQ", 1, 1);

        FAQ faq = new FAQ(driver);

        faq.navigateToFAQ();
        faq.clickCreateFAQ();

        faq.enterQuestion(question);
        faq.enterAnswer(answer);
     

        faq.clickSave();

        Reporter.log("FAQ creation form submitted", true);
    }
}



