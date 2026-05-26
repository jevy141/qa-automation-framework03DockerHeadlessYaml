package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By zip = By.id("postal-code");

    By continueBtn = By.id("continue");

    By finishBtn = By.id("finish");

    public void completeCheckout(String first, String last, String postalcode) {

    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

    	JavascriptExecutor js = (JavascriptExecutor) driver;

    	// FIRST NAME
    	WebElement firstNameField =
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));

    	js.executeScript("arguments[0].value='';", firstNameField);
    	firstNameField.sendKeys(first);

    	// LAST NAME
    	WebElement lastNameField =
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));

    	js.executeScript("arguments[0].value='';", lastNameField);
    	lastNameField.sendKeys(last);

    	// ZIP
    	WebElement zipField =
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(zip));

    	js.executeScript("arguments[0].value='';", zipField);
    	zipField.sendKeys(postalcode);

    	// WAIT UNTIL VALUES ARE ACTUALLY ENTERED
    	wait.until(driver ->
    	        firstNameField.getAttribute("value").equals(first));

    	wait.until(driver ->
    	        lastNameField.getAttribute("value").equals(last));

    	wait.until(driver ->
    	        zipField.getAttribute("value").equals(postalcode));

    	// DEBUG LOGS
    	System.out.println("First Name Entered: "
    	        + firstNameField.getAttribute("value"));

    	System.out.println("Last Name Entered: "
    	        + lastNameField.getAttribute("value"));

    	System.out.println("Zip Entered: "
    	        + zipField.getAttribute("value"));

    	// CONTINUE
    	WebElement continueButton =
    	        wait.until(ExpectedConditions.elementToBeClickable(continueBtn));

    	continueButton.click();

    	// VERIFY PAGE CHANGED
    	wait.until(ExpectedConditions.urlContains("checkout-step-two"));

    	System.out.println("Moved To Checkout Step Two");

    	// FINISH
    	WebElement finish =
    	        wait.until(ExpectedConditions.elementToBeClickable(finishBtn));

    	finish.click();

    	System.out.println("Checkout Completed Successfully");
    	

    	}


}