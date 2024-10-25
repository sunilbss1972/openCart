package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage
{

	//constructor
 public RegisterPage(WebDriver drv) 
	{
		super(drv);
	}

//locators
@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstName;
@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLastName;
@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;
@FindBy(xpath="//input[@id='input-telephone']")
WebElement txtPhone;
@FindBy(xpath="//input[@id='input-password']")
WebElement txtPassword;
@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtConfirmPassword;
@FindBy(xpath="//input[@name='agree']")
WebElement chkBoxTerms;
@FindBy(xpath="//input[@value='Continue']")
WebElement btnContinue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement lblSuccess;

//Action Methods
public void setFirstName(String fName)
{
	txtFirstName.sendKeys(fName);
}

public void setLastName(String lName)
{
	txtLastName.sendKeys(lName);
}

public void setEmail(String email)
{
	txtEmail.sendKeys(email);
}

public void setPhone(String phone)
{
	txtPhone.sendKeys(phone);
}

public void setPassword(String pwd)
{
	txtPassword.sendKeys(pwd);
}

public void setConfirmPwd(String cPwd)
{
	txtConfirmPassword.sendKeys(cPwd);
}

public void checkTerms()
{
	chkBoxTerms.click();
}

public void clickContinue()
{
	btnContinue.click();
}

public String msgConfirmation()
{
	try
	{
		return lblSuccess.getText();
	}
	catch(Exception e)
	{
		return e.getMessage();
	}
}
	
}
