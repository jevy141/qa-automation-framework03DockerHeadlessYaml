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
	
	public void completeCheckout(String first, String last, String postalcode) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    Actions actions = new Actions(driver);

	    // First Name
	    WebElement firstNameField =
	            wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));

	    firstNameField.clear();
	    firstNameField.sendKeys(first);

	    // Last Name
	    WebElement lastNameField =
	            wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));

	    lastNameField.clear();
	    lastNameField.sendKeys(last);

	    // Postal Code
	    WebElement zipField =
	            wait.until(ExpectedConditions.visibilityOfElementLocated(zip));

	    zipField.clear();
	    zipField.sendKeys(postalcode);

	    // Debug Logs for Docker/Jenkins
	    System.out.println("Docker Log - First Name: ["
	            + firstNameField.getAttribute("value") + "]");

	    System.out.println("Docker Log - Last Name: ["
	            + lastNameField.getAttribute("value") + "]");

	    System.out.println("Docker Log - Zip Code: ["
	            + zipField.getAttribute("value") + "]");

	    // Continue Button
	    WebElement continueButton =
	            wait.until(ExpectedConditions.elementToBeClickable(continueBtn));

	    try {

	        actions.moveToElement(continueButton).click().perform();

	    } catch (Exception e) {

	        System.out.println("Actions click failed. Using JS Click.");

	        ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].click();", continueButton);
	    }

	    // Verify Step Two Loaded
	    wait.until(ExpectedConditions.urlContains("checkout-step-two"));

	    System.out.println("Reached Checkout Step Two");

	    // Finish Button
	    WebElement finish =
	            wait.until(ExpectedConditions.elementToBeClickable(finishBtn));

	    try {

	        actions.moveToElement(finish).click().perform();

	    } catch (Exception e) {

	        System.out.println("Actions click failed on Finish. Using JS Click.");

	        ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].click();", finish);
	    }

	    System.out.println("Checkout Completed Successfully");
	}
}
