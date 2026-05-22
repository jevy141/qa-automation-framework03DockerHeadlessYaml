package pages;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

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
	
	public CartPage(WebDriver getDriver)
	{
		this.driver=getDriver;
		this.utils = new DriverUtils(this.driver);
	}
	
	
	
	public void clickCheckout()
	
	{
		System.out.println(driver.getPageSource());
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement checkout=wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].click();", checkout);
		 
		 System.out.println(driver.getCurrentUrl());
		 wait.until(
			        ExpectedConditions.urlContains("checkout-step-one")
			    );
	}
}
