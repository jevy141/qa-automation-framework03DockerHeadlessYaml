package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

@Listeners(listeners.TestListener.class)
public class EndToEndTest extends BaseTest{

	
	@Test
	public void testEcommerceFlow()
	{
		LoginPage loginpage= new LoginPage(driver);
		loginpage.login("standard_user", "secret_sauce");
		
		
		ProductsPage productspage= new ProductsPage(driver);
		productspage.addProductToCart();
		productspage.goToCart();
		
		CartPage cartpage= new CartPage(driver);
		cartpage.clickCheckout();
		
		CheckoutPage checkoutpage= new CheckoutPage(driver);
		checkoutpage.completeCheckout("Han", "Zhen", "400706");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@data-test='complete-header']")));

		String actualtext = message.getText();
		Assert.assertEquals(actualtext, "Wrong Text");
		//Assert.assertTrue(actualtext.contains("Thank you for your order!"));
		
		
		
	}
	
}
