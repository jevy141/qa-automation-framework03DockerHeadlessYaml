package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Dimension;
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


//webhook test purpose writing here to commit happen and webhook in github will update
//email test start ngrok then git commit file 
// again recheck for new Docker project
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
		
		
		
		String browserName = System.getProperty("browser");

		if(browserName == null) {
			browserName = "chrome";
		}
		
	
		
		
		if(browser.equalsIgnoreCase("chrome"))
		{

	
			
			ChromeOptions options = new ChromeOptions();

		    options.addArguments("--headless=new");
		    options.addArguments("--no-sandbox");
		    options.addArguments("--disable-dev-shm-usage");
		    options.addArguments("--disable-gpu");
		    options.addArguments("--window-size=1920,1080");
		    options.addArguments("--remote-allow-origins=*");

		    Map<String, Object> prefs = new HashMap<>();

		    prefs.put("credentials_enable_service", false);
		    prefs.put("profile.password_manager_enabled", false);

		    options.setExperimentalOption("prefs", prefs);

		    driver.set(new ChromeDriver(options));
		}
		
		
	
		
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//getDriver().manage().window().setSize(new Dimension(1920,1080));)
		getDriver().get("https://www.saucedemo.com/");
		
		
		
		
		 	
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		getDriver().quit();
		driver.remove();
	}
	
}
