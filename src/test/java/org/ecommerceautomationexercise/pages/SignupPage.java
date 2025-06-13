package org.ecommerceautomationexercise.pages;

import org.ecommerceautomationexercise.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.NoSuchElementException;

public class SignupPage {
    WebDriver driver;
    ElementUtil util;

    public SignupPage(WebDriver driver){

        this.driver = driver;
        this.util = new ElementUtil(driver); // Initialize helper
    }

    public void fillSignupForm (String name, String email){
        // Disable native validation
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('[data-qa=\"signup-name\"]').removeAttribute('required');");
        js.executeScript("document.querySelector('[data-qa=\"signup-email\"]').removeAttribute('required');");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='signup-name']"))).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='signup-email']"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-qa='signup-button']"))).click();
    }

    public void fillAccountInfo (String password, String day, String month, String year){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
        new Select(driver.findElement(By.id("days"))).selectByVisibleText(day);
        new Select(driver.findElement(By.id("months"))).selectByVisibleText(month);
        new Select(driver.findElement(By.id("years"))).selectByVisibleText(year);

        WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
        js.executeScript("arguments[0].click();", newsletterCheckbox);
        WebElement optinCheckbox = driver.findElement(By.id("optin"));
        js.executeScript("arguments[0].click();", optinCheckbox);
    }

    public void fillAddressInfo (String fname, String lname, String company, String addr1, String addr2, String country,
                                 String state, String city, String zipcode, String mobile){
        driver.findElement(By.id("first_name")).sendKeys(fname);
        driver.findElement(By.id("last_name")).sendKeys(lname);
        driver.findElement(By.id("company")).sendKeys(company);
        driver.findElement(By.id("address1")).sendKeys(addr1);
        driver.findElement(By.id("address2")).sendKeys(addr2);
        new Select(driver.findElement(By.id("country"))).selectByVisibleText(country);
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("zipcode")).sendKeys(zipcode);
        driver.findElement(By.id("mobile_number")).sendKeys(mobile);
    }
    public void submitForm() {
        WebElement createAccountBtn = driver.findElement(By.xpath("//button[@data-qa='create-account']"));
        util.scrollIntoView(createAccountBtn);
        util.click(createAccountBtn); // Uses safe click with JS fallback
    }

    public void accountCreated (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-qa='continue-button']")));
        continueBtn.click();

    }

    public boolean isLoggedinUserAsDisplayed(String usernameLoggedin){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = "//a[contains(.,'Logged in as') and .//b[text()='" + usernameLoggedin + "']]";
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return element.isDisplayed();
    }

    public void accountDeleted (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Delete Account']")));
        deleteBtn.click();
    }

    public boolean isAccountDeletedDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Account Deleted!']")));
        return message.isDisplayed();
    }

    public String getResultMessage (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//b[contains(text(),'Account Created')]")));
        return message.getText();
    }

    public boolean isEmailAlreadyUsedErrorDisplayed() {
        try {
            return driver.findElement(By.xpath("//p[contains(text(),'Email Address already exist!')]")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isValidationErrorDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Your email or password is incorrect!']")));
        return message.isDisplayed();
    }

}
