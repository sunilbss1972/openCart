package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.TestCaseBase;

public class ExtentReportsManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports exReports;
	public ExtentTest exTests;
	String repName;
	
	public void onTestStart(ITestResult result) 
	{
	    System.out.println("On Test Start");
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		System.out.println("On Test Success");
		exTests=exReports.createTest(result.getTestClass().getName());
		exTests.assignCategory(result.getMethod().getGroups());
		exTests.log(Status.PASS, "Test Case Passed: "+result.getName());
		
	}
	
	public void onTestFailure(ITestResult result) 
	{
		System.out.println("On Test Failure");
		exTests=exReports.createTest(result.getTestClass().getName());
		exTests.assignCategory(result.getMethod().getGroups());
		exTests.log(Status.FAIL, "Test Case Failed: "+result.getName());
		exTests.log(Status.FAIL, "Test Case Failed Cause: "+result.getThrowable());
		
		try
		{
			String imPath = new TestCaseBase().captureScreen(result.getName());
			exTests.addScreenCaptureFromPath(imPath);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		System.out.println("On Test Skipped");
		exTests=exReports.createTest(result.getTestClass().getName());
		exTests.assignCategory(result.getMethod().getGroups());
		exTests.log(Status.SKIP, "Test Case Skipped: "+result.getName());
		exTests.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onStart(ITestContext context) 
	{
		System.out.println("On Start");
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
	    sparkReporter.config().setDocumentTitle("Opencart Automation Testin");
	    sparkReporter.config().setReportName("Opencart Functional Testing");
	    sparkReporter.config().setTheme(Theme.DARK);
	    
	    exReports=new ExtentReports();
	    exReports.attachReporter(sparkReporter);
	    
	    exReports.setSystemInfo("Application", "Opencart");
	    exReports.setSystemInfo("Environment", "QA");
	    exReports.setSystemInfo("Module", "Admin");
	    exReports.setSystemInfo("Sub-Module", "Customer");
	    exReports.setSystemInfo("User Name", System.getProperty("User.Name"));
	    String os=context.getCurrentXmlTest().getParameter("os");
	    exReports.setSystemInfo("OS", os);
	    String br=context.getCurrentXmlTest().getParameter("browser");
	    exReports.setSystemInfo("Browser Name", br);
	    
	    List<String> incGroups=context.getCurrentXmlTest().getIncludedGroups();
	    if(!incGroups.isEmpty())
	    {
	    	exReports.setSystemInfo("Groups",incGroups.toString());
	    }
		
	}
	
	public void onFinish(ITestContext context) 
	{
		System.out.println("On Finish");
		exReports.flush();
		
		String reportPath=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(reportPath);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e2)
		{
			e2.printStackTrace();
		}
	}
}
