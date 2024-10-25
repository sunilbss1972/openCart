package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{

	public HomePage(WebDriver drv) 
	{
		super(drv);
	}
	
	//locators
		
		@FindBy(xpath="//span[normalize-space()='My Account']")
		WebElement accountLink;
		
		@FindBy(xpath="//a[normalize-space()='Register']")
		WebElement registerLink;
		
		@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
		WebElement loginLink;
		
		//Action Methods
		public void clickMyAccount()
		{
			accountLink.click();
		}
		
		public void clickRegister()
		{
			registerLink.click();
		}
		
		public void clickLogin()
		{
			loginLink.click();
		}

}
