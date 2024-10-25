package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.TestCaseBase;
import utilities.DataProviders;

public class Login_DDT extends TestCaseBase
{
	@Test(dataProvider="loginData", dataProviderClass=DataProviders.class,groups="DataDriven")
	public void verify_login(String userName, String pwd, String lStatus)
	{
	try
	{
		
	logger.info("Starting Login Test....");
	HomePage hp = new HomePage(drv);
	logger.info("Click My Account in Home Page....");
	hp.clickMyAccount();
	logger.info("Click Login link under MyAccount....");
	hp.clickLogin();
	
	LoginPage lp = new LoginPage(drv);
	logger.info("Enter Login details and submit....");
	lp.setLoginEmail(userName);
	lp.setPassword(pwd);
	lp.clickLogin();
	
	logger.info("Validate Login Test....");
	MyAccount ma=new MyAccount(drv);
	Boolean lblStatus=ma.getLabel();
	
	if(lStatus.equalsIgnoreCase("Valid"))
	{
		if(lblStatus==true) 
		{
			ma.clickLogout();
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
			
	}
	
	if(lStatus.equalsIgnoreCase("Invalid"))
	{
		if(lblStatus==true) 
		{
			ma.clickLogout();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
	}
	
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		logger.info("Login Test Failed");
		logger.error(e.getMessage());
		Assert.fail();
	}
	}

}
