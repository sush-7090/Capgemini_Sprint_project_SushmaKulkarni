package Ecom.BITM.VTiger.Login;

import org.jspecify.annotations.Nullable;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Ecom.BITM.VTiger.BaseClass.VTiger_BaseClass;

public class Login_TestCase extends VTiger_BaseClass{
	
	@Test(groups = {"Smoke", "Functional"})
    public void validLoginTest() throws InterruptedException {

        // Verify Product Page is displayed
        Assert.assertTrue(driver.getCurrentUrl().contains("Home"),"Login failed - Product Page is not displayed");
	    @Nullable
	    String url = driver.getCurrentUrl();
	    Reporter.log(url);        
        Reporter.log("Valid Login Successful");
    }
	
	

}
