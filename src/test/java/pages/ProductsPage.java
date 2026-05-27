package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverUtils;

public class ProductsPage {

	WebDriver driver;
	DriverUtils utils;
	
	By addToCart= By.id("add-to-cart-sauce-labs-backpack");
	//By cartIcon= By.xpath("//a[@data-test='shopping-cart-link']");
	By cartIcon = By.className("shopping_cart_link");

	
	public ProductsPage(WebDriver getDriver)
	{
		this.driver=getDriver;
		this.utils= new DriverUtils(this.driver);
		
		
	}
	
	public boolean isProductsPageDisplayed() {
	    return driver.getCurrentUrl().contains("inventory");
	}
	
	
	public void addProductToCart()
	{
		
		driver.findElement(addToCart).click();
	}
	public void goToCart()
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));

	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);

	    wait.until(ExpectedConditions.urlContains("cart"));
	}
}
