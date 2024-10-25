package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage 
{
    WebDriver drv;
	
	//Constructor
	public BasePage(WebDriver drv)
	{
		this.drv=drv;
		PageFactory.initElements(drv, this);
	}
	
}
