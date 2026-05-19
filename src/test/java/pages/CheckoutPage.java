package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		driver.findElement(firstName).sendKeys(first);
		driver.findElement(lastName).sendKeys(last);
		driver.findElement(zip).sendKeys(postalcode);
		driver.findElement(continueBtn).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();;
		

	}
}
