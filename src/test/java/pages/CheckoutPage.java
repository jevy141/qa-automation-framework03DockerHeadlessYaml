package pages;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverUtils;

public class CheckoutPage {

	WebDriver driver;
	DriverUtils utils;
	
	By firstName= By.id("first-name");
	By lastName= By.id("last-name");
	By zip= By.id("postal-code");
	By continueBtn = By.id("continue");
	By finishBtn=By.xpath("//button[@data-test='finish']");
	
	public CheckoutPage(WebDriver getDriver)
	{
		this.driver=getDriver;
		this.utils = new DriverUtils(this.driver);
	}
	
	public void completeCheckout(String first , String last, String postalcode )
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(first);

	    wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(last);

	    wait.until(ExpectedConditions.visibilityOfElementLocated(zip)).sendKeys(postalcode);

	    WebElement continueButton =
	            wait.until(ExpectedConditions.elementToBeClickable(continueBtn));

	    continueButton.click();
	     
	    System.out.println(driver.getCurrentUrl());
 
	    wait.until(ExpectedConditions.urlContains("checkout-step-two"));

	    WebElement finish =
	            wait.until(ExpectedConditions.elementToBeClickable(finishBtn));

	    finish.click();
	}
}
