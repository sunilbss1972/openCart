package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage
{
	//Constructor
	public MyAccount(WebDriver drv) 
	{
		super(drv);
	}
	
	//Locator
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement lblAccount;
	
	@FindBy(xpath="(//*[text()='Logout'])[2]")
	WebElement linkLogout;
	
	//Action Methods
	public Boolean getLabel()
	{
		try
		{
			return lblAccount.isDisplayed();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public void clickLogout()
	{
		linkLogout.click();
	}
	
}
