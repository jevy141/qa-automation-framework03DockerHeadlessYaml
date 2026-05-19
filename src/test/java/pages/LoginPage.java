package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.DriverUtils;

public class LoginPage {

      WebDriver driver;
      DriverUtils utils;
      
      By username=By.id("user-name");
      By password=By.id("password");
      By loginBtn=By.name("login-button");
      
      public LoginPage(WebDriver getDriver)
      {
    	  this.driver= getDriver;
    	  this.utils = new DriverUtils(this.driver);
      }

    

      public void login(String user, String pass)
      {
    	  utils.sendKeys(username, user);
    	  utils.sendKeys(password, pass);
    	  utils.click(loginBtn);
    	  
      }
}

