package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.PageLoadStrategy;




//webhook test purpose writing here to commit happen and webhook in github will update
//email test start ngrok then git commit file 
// again recheck for new Docker project
public class BaseTest {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Properties prop;

    public WebDriver getDriver() {
        return driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser");

        if (browserName == null || browserName.isEmpty()) {
            browserName = browser;
        }

        System.out.println("Running on Browser: " + browserName);

        if (browserName.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-background-networking");
            options.addArguments("--disable-sync");
            options.addArguments("--metrics-recording-only");
            options.addArguments("--disable-default-apps");
            options.addArguments("--no-first-run");
            options.addArguments("--disable-features=Translate,BackForwardCache");
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            driver.set(new ChromeDriver(options));
        }
        
        

        // ❗ IMPORTANT: DO NOT USE implicit wait in CI frameworks
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        getDriver().manage().window().setSize(new Dimension(1920, 1080));

        String env = System.getProperty("env", "qa");

        System.out.println("Running on Environment: " + env);

        String appUrl = prop.getProperty(env + ".url");

        System.out.println("Application URL: " + appUrl);

        try {
            getDriver().get(appUrl);
        } catch (Exception e) {
            System.out.println("Page load timeout/hang. Retrying URL...");
            getDriver().navigate().refresh();
            getDriver().get(appUrl);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        try {
            if (getDriver() != null) {
                getDriver().quit();
            }
        } catch (Exception e) {
            System.out.println("Driver quit failed: " + e.getMessage());
        } finally {
            driver.remove();
        }
    }
}