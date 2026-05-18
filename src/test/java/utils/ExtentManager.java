package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;
	
	public static ExtentReports getInstance()
	{
		if(extent == null)
		{
			//Full flow TestNG → Listener → ExtentReports → ExtentSparkReporter → HTML file

			String path =System.getProperty("user.dir") + "/reports/extent-report.html";
			ExtentSparkReporter reporter= new ExtentSparkReporter(path);
			reporter.config().setReportName("Automation report");
			
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			
		}
		return extent;
	}
}
