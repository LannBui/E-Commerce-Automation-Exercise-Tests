package org.ecommerceautomationexercise.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;


import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup(); // automatic
            ChromeOptions options = new ChromeOptions();

            // Disable password manager + warning
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            // Use incognito mode
            options.addArguments("--incognito");

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup(); // automatic
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);

        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        dismissAds();
    }
    public void dismissAds() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("let ad = document.getElementById('aswift_1_host'); if (ad) { ad.remove(); }");
            js.executeScript("let iframes = document.getElementsByTagName('iframe'); for (let i = 0; i < iframes.length; i++) { iframes[i].style.display = 'none'; }");
        } catch (Exception e) {
            System.out.println("No ads to dismiss or already removed.");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}