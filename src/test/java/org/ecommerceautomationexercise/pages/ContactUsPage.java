package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage {
    WebDriver driver;

    public ContactUsPage(WebDriver driver){
        this.driver = driver;
    }

     public boolean isGetInTouchVisible (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Get In Touch']")));
        return title.isDisplayed();
     }

    public void fillForm (String name, String email, String subject, String message, String filePath){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Disable native validation
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('input[name=\"email\"]').removeAttribute('required');");
        js.executeScript("document.querySelector('input[name=\"email\"]').removeAttribute('pattern');");


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("subject"))).sendKeys(subject);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).sendKeys(message);
        // Upload file
        WebElement uploadInput = driver.findElement(By.name("upload_file"));
        uploadInput.sendKeys(filePath);

    }

    public void submitForm (){
        driver.findElement(By.name("submit")).click();
        driver.switchTo().alert().accept(); //Handle the JavaScript alert
    }

    public boolean isSuccessMessageVisible (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Success! Your details have been submitted successfully.']")));
        return element.isDisplayed();
    }

    public void clickHomeBtn (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Home']"))).click();
    }

    public boolean isHomePageVisible (){
        return driver.findElement(By.xpath("//a[contains(text(),' Signup / Login')]")).isDisplayed();

    }

}
