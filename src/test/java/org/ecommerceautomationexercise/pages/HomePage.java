package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By signupLoginBtn = By.xpath("//a[contains(text(),' Signup / Login')]");

    private By contactUsBtn = By.xpath("//a[text()=' Contact us']");

    private By productsBtn = By.xpath("//a[text()=' Products']");

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


}
