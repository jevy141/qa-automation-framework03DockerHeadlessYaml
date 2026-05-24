package pages;

import java.time.Duration;

import org.openqa.selenium.By;
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

    public void completeCheckout(String first, String last, String postalcode)
            throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Window size for Docker
        driver.manage().window().setSize(new Dimension(1920, 1080));

        // FIRST NAME
        WebElement firstNameField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));

        firstNameField.clear();
        firstNameField.sendKeys(first);

        // LAST NAME
        WebElement lastNameField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));

        lastNameField.clear();
        lastNameField.sendKeys(last);

        // ZIP CODE
        WebElement zipField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(zip));

        zipField.clear();
        zipField.sendKeys(postalcode);

        // VERIFY VALUES
        System.out.println("First Name Entered: "
                + firstNameField.getAttribute("value"));

        System.out.println("Last Name Entered: "
                + lastNameField.getAttribute("value"));

        System.out.println("Zip Entered: "
                + zipField.getAttribute("value"));

        // CONTINUE BUTTON
        WebElement continueButton =
                wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtn));

        // Scroll into view
        js.executeScript("arguments[0].scrollIntoView(true);", continueButton);

        Thread.sleep(1000);

        // JAVASCRIPT CLICK (BEST FOR DOCKER)
        js.executeScript("arguments[0].click();", continueButton);

        // DEBUG
        System.out.println("Current URL After Continue Click: "
                + driver.getCurrentUrl());

        // WAIT FOR STEP TWO
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        System.out.println("Moved to Checkout Step Two");

        // FINISH BUTTON
        WebElement finish =
                wait.until(ExpectedConditions.elementToBeClickable(finishBtn));

        js.executeScript("arguments[0].scrollIntoView(true);", finish);

        Thread.sleep(1000);

        js.executeScript("arguments[0].click();", finish);

        System.out.println("Checkout Completed Successfully");
    }
}