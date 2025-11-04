package testCases;

import java.util.Properties;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchTest extends BaseClass {

	
	@Test(groups ="Master")
	public void searchBox() throws InterruptedException
	{
		logger.info("***Starting Test TC004_SearchTest***");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.SetEmail(p.getProperty("email"));
		lp.SetPassword(p.getProperty("password"));
		lp.clickLogin();
		
		SearchPage sp = new SearchPage(driver);
		sp.setSearchBox(p.getProperty("search"));
		sp.clickSearchButton();
		//sp.checkProductMeeting();
		sp.clickIphone();
		Thread.sleep(2000);
		sp.setQuantity("3");
		sp.clickAddToCart();
		
		
	}
	
}
