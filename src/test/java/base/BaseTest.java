package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class BaseTest {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public Properties prop;
	
	
	public WebDriver getDriver()
	{
		return driver.get();
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("chrome") String browser) throws IOException
	{
		try {
			prop = new Properties();
			FileInputStream fis= new FileInputStream("src/test/resources/config.properties");
			prop.load(fis);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		ChromeOptions options= new ChromeOptions();
		
		
	    
		options.addArguments("--guest");
		//options.addArguments("--headless=new");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--remote-allow-origins=*");

		if(browser.equalsIgnoreCase("chrome"))
		{
	
			driver.set(new ChromeDriver(options));
			
		}
		
		else if(browser.equalsIgnoreCase("edge"))
		{
	

	            driver.set(new EdgeDriver());

		}
		
		else if(browser.equalsIgnoreCase("firefox"))
		{

			driver.set(new FirefoxDriver());
		}
		
	
		
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();
		getDriver().get("https://www.saucedemo.com/");
		
		
		
		
		
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		getDriver().quit();
		driver.remove();
	}
	
}
