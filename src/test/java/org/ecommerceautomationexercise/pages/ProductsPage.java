package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    WebDriver driver;

    public ProductsPage (WebDriver driver){
        this.driver = driver;
    }

    public boolean isAllProductsPageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));
        return productPage.isDisplayed();
    }

    public boolean isProductListVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement products = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("single-products")));
        return products.isDisplayed();
    }

    public void clickFirstViewProduct (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement viewProduct = driver.findElement(By.xpath("//a[@href='/product_details/1']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", viewProduct);
        js.executeScript("arguments[0].click();", viewProduct);
    }

    public boolean isProductDetailVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-details")));
        return productDetail.isDisplayed();
    }

    public boolean areAllDetailsVisible() {
        return driver.getPageSource().contains("Category")
                && driver.getPageSource().contains("Availability")
                && driver.getPageSource().contains("Condition")
                && driver.getPageSource().contains("Brand");
    }

    public void searchProduct (String keyword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")));
        input.clear();
        input.sendKeys(keyword);
        driver.findElement(By.id("submit_search")).click();
    }

    public List<WebElement> getSearchResultTitles() {
        return driver.findElements(By.cssSelector(".productinfo.text-center p"));
    }

    public boolean isSearchProductsTitleVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Searched Products']")));
        return title.isDisplayed();
    }
    public boolean areSearchedResultsVisible() {
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='productinfo text-center']"));
        return products.size() > 0;
    }

}
