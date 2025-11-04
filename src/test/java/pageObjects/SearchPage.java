package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement txtSearchBox;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement btnSearch;
	
	@FindBy(xpath="//h2[normalize-space()='Products meeting the search criteria']")
	WebElement criteriaText;
	
	@FindBy(xpath="//div[@class='caption']//a[contains(text(),'iPhone')]")
	WebElement btnIphone;
	
	@FindBy(xpath="//input[@id='input-quantity']")
	WebElement txtQuantity;
	
	@FindBy(xpath="//button[@id='button-cart']")
	WebElement btnAddtoCart;
	
	
	public void setSearchBox(String src)
	{
		txtSearchBox.sendKeys(src);
	}
	
	public void clickSearchButton()
	{
		btnSearch.click();
	}
	
	
	public boolean checkProductMeeting()
	{
		try
		{
			return criteriaText.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	public void clickIphone()
	{
		btnIphone.click();
	}
	
	public void setQuantity(String qty)
	{
		txtQuantity.clear();
		txtQuantity.sendKeys(qty);
	}
	
	public void clickAddToCart()
	{
		btnAddtoCart.click();
	}
	
	
	
	
	
}
