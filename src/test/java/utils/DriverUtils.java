package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {
	
	WebDriver driver;
	
	public DriverUtils(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void waitForElement(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void click(By locator)
	{
		waitForElement(locator);
		driver.findElement(locator).click();
		
	}
	
	public void sendKeys(By locator, String value )
	{
		waitForElement(locator);
		driver.findElement(locator).sendKeys(value);
	}

}
