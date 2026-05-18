package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.DriverUtils;

public class ProductsPage {

	WebDriver driver;
	DriverUtils utils;
	
	By addToCart= By.id("add-to-cart-sauce-labs-backpack");
	By cartIcon= By.xpath("//a[@data-test='shopping-cart-link']");
	
	public ProductsPage(WebDriver driver)
	{
		this.driver=driver;
		this.utils= new DriverUtils(driver);
		
		
	}
	
	
	
	public void addProductToCart()
	{
		
		driver.findElement(addToCart).click();
	}
	public void goToCart()
	{
		driver.findElement(cartIcon).click();
	}
}
