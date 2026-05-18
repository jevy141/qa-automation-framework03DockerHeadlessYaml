package base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	
	@BeforeMethod
	public void setup()
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
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options= new ChromeOptions();
		
		
	    
		options.addArguments("--guest");
		options.addArguments("--headless=new");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--remote-allow-origins=*");
		//options.addArguments("--disable-notifications");
		//options.addArguments("--disable-infobars");
		//options.addArguments("--disable-save-password-bubble");
		
		HashMap<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    
		
		options.setExperimentalOption("prefs", prefs);
		
		
		
		
		driver= new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("timeout"))));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
}
