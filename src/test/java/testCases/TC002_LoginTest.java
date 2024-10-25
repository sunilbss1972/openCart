package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.TestCaseBase;

public class TC002_LoginTest extends TestCaseBase
{
	@Test(groups= {"Sanity","Master"})
	public void LoginTest()
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
		lp.setLoginEmail(pFile.getProperty("userName"));
		lp.setPassword(pFile.getProperty("password"));
		lp.clickLogin();
		
		logger.info("Validate Login Test....");
		MyAccount ma=new MyAccount(drv);
		Assert.assertEquals(ma.getLabel(), true);
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
