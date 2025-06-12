package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isCartPageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Shopping Cart']")));
        return cartTitle.isDisplayed();

    }
    public boolean areTwoProductsInCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> cartRows = driver.findElements(By.xpath("//tr[contains(@id,'product')]"));
        return cartRows.size() == 2;
    }

    public boolean verifyPriceQuantityTotal () {
        WebElement price1 = driver.findElement(By.xpath("(//td[@class='cart_price']/p)[1]"));
        WebElement quantity1 = driver.findElement(By.xpath("(//td[@class='cart_quantity']/button)[1]"));
        WebElement total1 = driver.findElement(By.xpath("(//td[@class='cart_total']/p)[1]"));

        int price = extractInt(price1.getText());
        int quantity = extractInt(quantity1.getText());
        int total = extractInt(total1.getText());

        return price*quantity == total;
    }

    private int extractInt(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }

    public int getQuantity () {
        String qty = driver.findElement(By.className("cart_quantity")).getText().trim();
        return Integer.parseInt(qty);
    }
    public void clickProceedToCheckout() {
        driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();
    }

    public void removeFirstProductFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.cart_quantity_delete")));
        deleteButton.click();
    }

    public boolean isCartEmpty() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(),'Cart is empty')]")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isCartNotEmpty() {
        List<WebElement> items = driver.findElements(By.xpath("//tr[contains(@id,'product-')]"));
        return !items.isEmpty();
    }

    public void goToCart () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='View Cart']/parent::a")
        ));
        viewCart.click();
    }

    public boolean isProductListVisible() {
        return driver.findElements(By.cssSelector(".cart_info")).size() > 0;
    }

    public void registerOrLoginToProceedOnCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement registerLogin = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='Register / Login']/parent::a")
        ));
        registerLogin.click();
    }


}
