package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    private By signupLoginBtn = By.xpath("//a[contains(text(),' Signup / Login')]");

    private By contactUsBtn = By.xpath("//a[text()=' Contact us']");

    private By productsBtn = By.xpath("//a[text()=' Products']");

    private By cartBtn = By.xpath("//a[text()=' Cart']");

    // Constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    // Custom method to click on 'Signup/Login'
    public void goToSignup (){
        driver.findElement(signupLoginBtn).click();
    }

    public void goToContactUs (){
        driver.findElement(contactUsBtn).click();
    }

    public void goToProductPage (){
        driver.findElement(productsBtn).click();
    }

    public void goToCartPage () { driver.findElement(cartBtn).click(); }

    public boolean isHomeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Signup / Login']"))).isDisplayed();

    }
    public void scrollToSubscription() {
        WebElement footer = driver.findElement(By.id("footer"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);

    }

    public boolean isSubscriptionVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Subscription']")));
        return element.isDisplayed();
    }

    public void enterSubscriptionEmail (String email){
        WebElement input = driver.findElement(By.id("susbscribe_email"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('required');", input);

        input.clear();
        input.sendKeys(email);
        driver.findElement(By.id("subscribe")).click();

    }

    public boolean isSubscriptionSuccessVisible() {
        return driver.getPageSource().contains("You have been successfully subscribed!");
    }

    public boolean isEmailFormatErrorShown() {
        return driver.getPageSource().contains("Invalid email format");
    }

    public void scrollToRecommendedItems() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public boolean isRecommendedItemsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='recommended items']")));
        return section.isDisplayed();
    }

    public void addFirstRecommendedItemToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@id='recommended-item-carousel']//a[contains(text(),'Add to cart')])[1]")));
        addToCartBtn.click();
    }

    public void clickViewCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[text()='View Cart']/parent::a")));
        viewCart.click();
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public boolean isScrollUpTextVisible() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Full-Fledged practice website for Automation Engineers')]")));
        return header.isDisplayed();
    }

    public void clickScrollUpArrow() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement arrow = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("scrollUp"))); // Assuming ID is scrollUp, verify with devtools
        arrow.click();
    }





}
