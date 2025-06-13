package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterComment(String comment) {
        driver.findElement(By.name("message")).sendKeys(comment);
    }

    public void placeOrder() {
        driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
    }

    public void enterPaymentDetails(String name, String cardNumber, String cvc, String month, String year) {
        driver.findElement(By.name("name_on_card")).sendKeys(name);
        driver.findElement(By.name("card_number")).sendKeys(cardNumber);
        driver.findElement(By.name("cvc")).sendKeys(cvc);
        driver.findElement(By.name("expiry_month")).sendKeys(month);
        driver.findElement(By.name("expiry_year")).sendKeys(year);
    }

    public void confirmOrder() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement confirmButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-qa='pay-button']")));
        // Scroll into view to avoid ad overlap
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", confirmButton);
        // Click via JavaScript to bypass overlapping iframe/ad
        js.executeScript("arguments[0].click();", confirmButton);
    }

    public boolean isOrderSuccessVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Congratulations! Your order has been confirmed!']")));
        return successMsg.isDisplayed();

    }

    public String getDeliveryAddressText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addressBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='address_delivery']")));
        return addressBox.getText().replace("\n", " ");
    }

    public String getBillingAddressText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addressBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='address_invoice']")));
        return addressBox.getText().replace("\n", " ");
    }

    public void downloadInvoice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement downloadBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Download Invoice']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", downloadBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", downloadBtn);
    }
    public boolean isInvoiceDownloaded (String downloadDir, String fileNamePrefix) {
        File dir = new File(downloadDir);
        File [] files = dir.listFiles((d, name) -> name.startsWith(fileNamePrefix) && name.endsWith(".txt"));
        return files != null && files.length > 0;
    }
}
