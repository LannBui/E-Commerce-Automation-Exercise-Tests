package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void login(String email, String password){
        // Disable native validation
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('[data-qa=\"login-email\"]').removeAttribute('required');");
        js.executeScript("document.querySelector('[data-qa=\"login-password\"]').removeAttribute('required');");

        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
    }

    public boolean isLoggedinUserAsDisplayed(){
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        String xpath = "//a[contains(.,'Logged in as') and .//b[text()='" + usernameLoggedin + "']]";
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//        return element.isDisplayed();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement loggedInUser = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Logged in as') and .//b[string-length(text()) > 0]]")));
        return loggedInUser.isDisplayed();
    }
    public boolean isErrorMessageDisplayed (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Your email or password is incorrect!']")));
        return message.isDisplayed();
    }
    public void deleteAccount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Delete Account']")));
        deleteBtn.click();
        }
    public boolean isAccountDeletedDisplayed (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Account Deleted!']")));
        return message.isDisplayed();
    }
    public void logout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Logout']")));
        logoutBtn.click();
    }
    public boolean isLoginPageVisible (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Login to your account']")));
        return message.isDisplayed();
    }

}

