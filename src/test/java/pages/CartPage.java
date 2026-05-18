package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverUtils;

public class CartPage {

	WebDriver driver;
	DriverUtils utils;
	By checkoutBtn = By.id("checkout");
	
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		this.utils = new DriverUtils(driver);
	}
	
	
	
	public void clickCheckout()
	
	{
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement checkout=wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
		 checkout.click();
	}
}
