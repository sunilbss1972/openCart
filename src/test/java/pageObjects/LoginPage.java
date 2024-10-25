package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{

	public LoginPage(WebDriver drv) 
	{
		super(drv);
	}
	
	//locators
	@FindBy(xpath="//*[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//*[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//*[@value='Login']")
	WebElement btnLogin;
	
	//Action Methods
	
	public void setLoginEmail(String LoginEmail)
	{
		txtEmail.sendKeys(LoginEmail);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnLogin.click();
	}
	
	

}
