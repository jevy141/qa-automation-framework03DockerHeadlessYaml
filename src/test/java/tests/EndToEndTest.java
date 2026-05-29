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
	
	public void testEcommerceFlow() throws InterruptedException
	{
		LoginPage loginpage= new LoginPage(getDriver());
		loginpage.login("standard_user", "secret_sauce");
		
		
		ProductsPage productspage= new ProductsPage(getDriver());
		productspage.addProductToCart();
		productspage.goToCart();
		
		CartPage cartpage= new CartPage(getDriver());
		cartpage.clickCheckout();
		
		CheckoutPage checkoutpage= new CheckoutPage(getDriver());
		checkoutpage.completeCheckout("Han", "Zhen", "400706");
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@data-test='complete-header']")));

		String actualtext = message.getText();
		//Assert.assertEquals(actualtext, "Wrong Text");
Assert.fail("Intentional Failure");// checking for retry max count 2  total attempt 3 including original
		//Assert.assertTrue(actualtext.contains("Thank you for your order!"));
		
		
		
	}
	
}
