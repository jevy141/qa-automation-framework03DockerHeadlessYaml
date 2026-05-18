package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import utils.ExtentManager;
import utils.ScreenShotUtil;

public class TestListener implements ITestListener{
	
	//All tests write to the same report file
	ExtentReports extent= ExtentManager.getInstance();
	
	//Represents one test case entry in report. A single row/block in report UI
	//ExtentTest (Test 1),ExtentTest (Test 2),ExtentTest (Test 3)
	ExtentTest test;
	
	
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.pass("Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		
		System.out.println("🔥 Listener triggered");
		// Get test class object
		     Object currentClass=result.getInstance();
		     
		     // Cast to BaseTest ...Same driver instance...Same browser session
		  WebDriver driver   =((BaseTest)currentClass).getDriver();
		  
		  String testName= result.getMethod().getMethodName(); 
		
		String path = ScreenShotUtil.captureScreenShot(driver, testName);
		
		test.fail(result.getThrowable());
		test.addScreenCaptureFromPath(path);		       
	}
	
	@Override
	public void onFinish(org.testng.ITestContext context)
	{
		extent.flush();
	}

	
}
