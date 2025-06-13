package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage {
    WebDriver driver;

    public ProductDetailPage (WebDriver driver){

        this.driver = driver;
    }

    public void setQuantity (String quantity) {
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys(quantity);
    }

    public void clickAddToCart () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.btn-default.cart")));
        addToCartBtn.click();
    }
    public void viewCart () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='View Cart']/parent::a")
        ));
        viewCart.click();
    }

    public boolean isReviewSectionVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement reviewTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Write Your Review']")));
        return reviewTitle.isDisplayed();
    }

    public void fillReviewForm(String name, String email, String review) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('name').removeAttribute('required');");
        js.executeScript("document.getElementById('email').removeAttribute('required');");
        js.executeScript("document.getElementById('review').removeAttribute('required');");
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("review")).sendKeys(review);
    }

    public void submitReview() {
        driver.findElement(By.id("button-review")).click();
    }

    public boolean isReviewSubmittedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Thank you for your review.')]")));
        return successMsg.isDisplayed();
    }

    public String getReviewSuccessMessageText() {
        try {
            return driver.findElement(By.xpath("//span[contains(text(),'Thank you')]")).getText();
        } catch (NoSuchElementException e) {
            return "No message found.";
        }
    }



}



