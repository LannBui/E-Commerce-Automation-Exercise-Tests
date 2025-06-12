package org.ecommerceautomationexercise.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Dismiss or hide Google Ad iframes that interfere with clicks.
     */
    public void hideAdIframesIfPresent() {
        try {
            List<WebElement> iframes = driver.findElements(By.cssSelector(
                    "iframe[id^='aswift'], iframe[src*='doubleclick']"
            ));

            for (WebElement iframe : iframes) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].style.display='none';", iframe);
                System.out.println("Ad iframe hidden: " + iframe.getAttribute("id"));
            }

            driver.switchTo().defaultContent(); // Always return to main context
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            System.out.println("Error hiding ad iframe: " + e.getMessage());
        }
    }

    /**
     * Perform a safe JavaScript-based click on a WebElement, after dismissing ads.
     */
    public void safeClick(WebElement element) {
        hideAdIframesIfPresent();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            throw new RuntimeException("Failed to safely click element: " + e.getMessage());
        }
    }

    /**
     * Wait until the given element is visible.
     */
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait until the element is clickable.
     */
    public WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Scroll to a WebElement before interacting with it.
     */
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
