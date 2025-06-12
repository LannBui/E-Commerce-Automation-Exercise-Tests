package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrandPage {
    WebDriver driver;
    WebDriverWait wait;

    public BrandPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isBrandSidebarVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Brands']"))).isDisplayed();
    }

    public void clickOnAnyBrand() {
//        WebElement brandElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'" + brandName + "')]")));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandElement);
//        brandElement.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500);");  // Scroll to make brands visible

        WebElement firstBrand = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("(//a[contains(@href, 'brand_products')])[1]")));
        firstBrand.click();
    }

    public boolean isBrandPageVisible () {
        WebElement brandTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Brand - Polo Products')]")));
        return brandTitle.isDisplayed();
    }

}
