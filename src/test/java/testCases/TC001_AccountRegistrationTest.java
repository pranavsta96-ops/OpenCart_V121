package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException
	{
		logger.info("**** Starting TC001_AccountRegistrationTest");
		logger.debug("This is debug log message");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my Account...");
		hp.clickRegister();
		logger.info("Clicked on Register Link...");
		
		String password = randomAlphaNumeric();  //store because to set pwd and confirm pwd same 
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing Customer Details...");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString().toLowerCase()+ "@gmail.com"); //randomly generated the email 
		regpage.setTelephone(randomNumber());
		Thread.sleep(2000);
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("Validating Expected Message...");
		
		String confmsg =regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation Message Mismatch");
		
		logger.info("Test Pass");
		
		}
		catch(Exception e)
		{
			logger.error("Test Failed:"+ e.getMessage());
			Assert.fail("Test Failed"+e.getMessage());
		}
		
		finally
		{
			logger.info("****Finished TC001_AccountRegistrationTest***");
		}
		
		Thread.sleep(2000);
		
		
		
	}
}
