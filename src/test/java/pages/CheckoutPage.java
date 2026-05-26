package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueBtn = By.id("continue");
    By finishBtn = By.id("finish");
    By successMsg = By.xpath("//h2[@data-test='complete-header']");

    public void completeCheckout(String first, String last, String zip) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement firstNameElement = wait.until(ExpectedConditions.elementToBeClickable(firstName));
        firstNameElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        firstNameElement.sendKeys(Keys.DELETE);
        firstNameElement.sendKeys(first);

        WebElement lastNameElement = wait.until(ExpectedConditions.elementToBeClickable(lastName));
        lastNameElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        lastNameElement.sendKeys(Keys.DELETE);
        lastNameElement.sendKeys(last);

        WebElement zipElement = wait.until(ExpectedConditions.elementToBeClickable(postalCode));
        zipElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        zipElement.sendKeys(Keys.DELETE);
        zipElement.sendKeys(zip);

        System.out.println("First Name Entered: " + firstNameElement.getAttribute("value"));
        System.out.println("Last Name Entered: " + lastNameElement.getAttribute("value"));
        System.out.println("Zip Entered: " + zipElement.getAttribute("value"));

        WebElement continueElement = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", continueElement);
        js.executeScript("arguments[0].click();", continueElement);

        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        WebElement finishElement = wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", finishElement);
        js.executeScript("arguments[0].click();", finishElement);

        wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
    }
}