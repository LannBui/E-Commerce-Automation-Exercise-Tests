package org.ecommerceautomationexercise.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

    public void addFirstTwoProductsToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait until product cards are present
        List<WebElement> products = wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(".product-image-wrapper")));

        if (products.size() < 2) {
            throw new RuntimeException("Less than 2 products found!");
        }

        for (int i = 0; i < 2; i++) {
            WebElement product = products.get(i);

            // Scroll and hover
            js.executeScript("arguments[0].scrollIntoView(true);", product);
            actions.moveToElement(product).perform();

            // Get the 'Add to cart' button inside that product element
            WebElement addToCartBtn = product.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));

            // Use JavaScript to click to avoid ad overlay
            js.executeScript("arguments[0].click();", addToCartBtn);

            // If not the last item, click 'Continue Shopping'
            if (i < 1) {
                WebElement continueBtn = wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//button[text()='Continue Shopping']")));
                continueBtn.click();
            }
        }
    }
    public void addFirstProductToCart() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait for products to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".features_items")));

        // Locate first product container
        WebElement product = driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])[1]"));

        // Scroll to product and hover to make the button visible
        js.executeScript("arguments[0].scrollIntoView(true);", product);

        // Hover to reveal 'Add to cart'
        actions.moveToElement(product).perform();

        // Then click 'Add to cart' that becomes visible
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@class='product-image-wrapper'])[1]//a[contains(text(),'Add to cart')]")
        ));
        js.executeScript("arguments[0].click();", addToCartBtn);
    }
    public void goToCart () {
//        driver.findElement(By.xpath("//u[text()='View Cart']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='View Cart']/parent::a")
        ));
        viewCart.click();
    }
    public boolean areAllProductsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> productList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".features_items .product-image-wrapper")));
        return productList.size() > 0;

    }

}
