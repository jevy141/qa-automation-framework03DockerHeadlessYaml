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

        // Bump to 45 seconds to account for Jenkins/Docker environment lag
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

        // 1. FIRST NAME
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        firstNameField.clear();
        firstNameField.sendKeys(first);

        // 2. LAST NAME
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        lastNameField.clear();
        lastNameField.sendKeys(last);

        // 3. ZIP CODE
        WebElement zipField = wait.until(ExpectedConditions.visibilityOfElementLocated(zip));
        zipField.clear();
        zipField.sendKeys(postalcode);

        // Debugging print statements (Safe to keep)
        System.out.println("First Name Entered: " + firstNameField.getAttribute("value"));
        System.out.println("Last Name Entered: " + lastNameField.getAttribute("value"));
        System.out.println("Zip Entered: " + zipField.getAttribute("value"));

        // 4. CONTINUE BUTTON
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        continueButton.click();

        // 5. VERIFY URL CHANGE
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        System.out.println("Moved To Checkout Step Two");

        // 6. FINISH BUTTON
        WebElement finish = wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        finish.click();

        System.out.println("Checkout Completed Successfully");
    }

}