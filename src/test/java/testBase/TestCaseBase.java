package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestCaseBase 
{
	public static WebDriver drv;
	public Logger logger;
	public Properties pFile;
	//public FileInputStream fInput;
	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})
	@Parameters({"browser","os"})
	  public void setup(String br, String os) 
	  {
		try
		{
		  logger = LogManager.getLogger(this.getClass());
		  logger.info("Launching Browser");
		  FileInputStream fInput=new FileInputStream("D:\\MavenProject\\openCart\\src\\test\\resources\\config.properties");
		  pFile=new Properties();
		  pFile.load(fInput);
		  
		  if(pFile.getProperty("execution_env").equalsIgnoreCase("local"))
		  {
			  switch(br.toLowerCase())
			  {
			  	case "chrome": drv=new ChromeDriver();break;
			  	case "edge": drv=new EdgeDriver();break;
			  	case "firefox": drv=new FirefoxDriver();break;
			  	default: System.out.println("Invalid Browser Name Specifies...");return;
			  }
		  }
		  
		  if(pFile.getProperty("execution_env").equalsIgnoreCase("remote"))
		  {
			  DesiredCapabilities cap = new DesiredCapabilities();
			  
			  switch(os.toLowerCase())
			  {
			  	case "windows":cap.setPlatform(Platform.WIN11);break;
			  	case "mac":cap.setPlatform(Platform.MAC);break;
			  	case "unix":cap.setPlatform(Platform.UNIX);break;
			  	case "linux":cap.setPlatform(Platform.LINUX);break;
			  	default: System.out.println("Invalid Platform...");return;
			  }
			  
			  switch (br.toLowerCase())
			  {
			  	case "chrome": cap.setBrowserName("chrome");break;
			  	case "edge": cap.setBrowserName("MicrosoftEdge");break;
			  	case "firefox": cap.setBrowserName("firefox");break;
			  	default:System.out.println("Invalid Browser Name...");return;
			  }
			  
			  drv=new RemoteWebDriver(new URL("http://192.168.1.105:4444/wd/hub"),cap);
		  };
		  
		  
		  drv.manage().deleteAllCookies();
		  drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  logger.info("Opening Application");
		  String url=pFile.getProperty("appurl");
		  System.out.println(url);
		  drv.get(url);
		  drv.manage().window().maximize();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			return;
		}
		  
	  }

	  @AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
	  public void afterClass() throws IOException 
	  {
		  drv.quit();
		  //fInput.close();
	  }
	  
	  public String randomString(int no)
	  {
		  String rndStr=RandomStringUtils.randomAlphabetic(no);
		  return rndStr;
	  }
	  
	  public String randomNumber(int no)
	  {
		  String rndNo=RandomStringUtils.randomNumeric(no);
		  return rndNo;
	  }
	  
	  public String randomAlphaNumeric(int no)
	  {
		  String strAlphaNum=RandomStringUtils.randomAlphanumeric(no);
		  return strAlphaNum;
		  
	  }
	  
	  public String captureScreen(String tName) throws IOException
	  {
		  String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		  TakesScreenshot takeScreenshot= (TakesScreenshot) drv;
		  File sourceFile=takeScreenshot.getScreenshotAs(OutputType.FILE);
		  
		  String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tName+"-"+timeStamp+".png";
		  File targetFile = new File(targetFilePath);
		  
		  sourceFile.renameTo(targetFile);
		  
		  return(targetFilePath);
	  }
}
