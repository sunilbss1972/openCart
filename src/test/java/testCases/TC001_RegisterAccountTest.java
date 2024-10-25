package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.TestCaseBase;

public class TC001_RegisterAccountTest extends TestCaseBase
{
  
  @Test(groups= {"Regression","Master"})
  public void RegisterTest()
  {
	  try
	  {
		  logger.info("Starting TC001 Test....	");
		  
	  HomePage hp = new HomePage(drv);
	  Thread.sleep(1000);
	  hp.clickMyAccount();
	  Thread.sleep(1000);
	  hp.clickRegister();
	  RegisterPage reg=new RegisterPage(drv);
	  
	  logger.info("Filling Registration Details....");
	  reg.setFirstName(randomString(6).toUpperCase());
	  reg.setLastName(randomString(6).toUpperCase());
	  reg.setEmail(randomAlphaNumeric(8)+"@gmail.com");
	  reg.setPhone(randomNumber(10));
	  String strPassword=randomString(6)+"@"+randomNumber(4);
	  reg.setPassword(strPassword);
	  reg.setConfirmPwd(strPassword);
	  reg.checkTerms();
	  reg.clickContinue();
	  
	  String strMessage=reg.msgConfirmation();
	  logger.info("Validating Test Case....");
	  
	  Assert.assertEquals(strMessage, "Your Account Has Been Created!");
	  }
	  catch(Exception e)
	  {
		  logger.error(e.getMessage());
		  System.out.println(e.getMessage());
		  Assert.fail();
	  }
	  
	  logger.info("Finished Test Case....");
  }
  
}
