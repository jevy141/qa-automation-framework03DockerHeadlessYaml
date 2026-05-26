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

        wait.until(ExpectedConditions.elementToBeClickable(firstName)).sendKeys(first);
        wait.until(ExpectedConditions.elementToBeClickable(lastName)).sendKeys(last);
        wait.until(ExpectedConditions.elementToBeClickable(postalCode)).sendKeys(zip);

        WebElement continueElement = wait.until(
                ExpectedConditions.elementToBeClickable(continueBtn)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", continueElement);

        continueElement.click();

        // 🔥 IMPORTANT: wait for REAL page state, not DOM presence
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        WebElement finishElement = wait.until(
                ExpectedConditions.elementToBeClickable(finishBtn)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", finishElement);

        finishElement.click();

        // 🔥 FINAL RELIABLE CHECK (NOT URL)
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
    }

}