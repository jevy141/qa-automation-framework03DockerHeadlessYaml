package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil {
	

	public static String captureScreenShot(WebDriver driver , String testName)
	{
		
		System.out.println("Screenshot method called");
		System.out.println("Test name: " + testName);
		String path =System.getProperty("user.dir") + "/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
		
		
		
	
		try
		{
			File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(path));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return path;
	}
	
	
	
	
}

