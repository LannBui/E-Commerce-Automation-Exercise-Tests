package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryPage {
    WebDriver driver;
    WebDriverWait wait;

    public CategoryPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCategorySidebarVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".left-sidebar"))).isDisplayed();
    }

    public void clickOnWomenCategory() {

        WebElement womenCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#Women']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", womenCategory);
        womenCategory.click();
    }

    public void clickOnWomenSubCategory(String subCategory) {
        WebElement womenSubCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + subCategory + "')]")));
        womenSubCategory.click();
    }

    public boolean isCategoryHeaderVisible(String expectedTest) {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".features_items h2")));
        return header.getText().contains(expectedTest);
    }

    public void clickOnMenCategory() {
        WebElement menCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#Men']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menCategory);
        menCategory.click();
    }

    public void clickOnMenSubCategory (String subCategory) {
        WebElement menSubCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + subCategory + "')]")));
        menSubCategory.click();
    }
}
