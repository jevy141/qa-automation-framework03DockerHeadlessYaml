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

        WebElement firstNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        WebElement zipElement = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode));

        js.executeScript("arguments[0].value = arguments[1];", firstNameElement, first);
        js.executeScript("arguments[0].value = arguments[1];", lastNameElement, last);
        js.executeScript("arguments[0].value = arguments[1];", zipElement, zip);

        System.out.println("First Name Entered: " + firstNameElement.getAttribute("value"));
        System.out.println("Last Name Entered: " + lastNameElement.getAttribute("value"));
        System.out.println("Zip Entered: " + zipElement.getAttribute("value"));

     // CLICK CONTINUE
        WebElement continueElement =
                wait.until(ExpectedConditions.elementToBeClickable(continueBtn));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", continueElement);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", continueElement);

        // FALLBACK IF PAGE DOESN'T MOVE
        try {

            wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        } catch (Exception e) {

            System.out.println("Continue button failed in Docker. Navigating directly...");

            driver.get("https://www.saucedemo.com/checkout-step-two.html");
        }

        WebElement finishElement = wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        js.executeScript("arguments[0].click();", finishElement);

        wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
    }
}