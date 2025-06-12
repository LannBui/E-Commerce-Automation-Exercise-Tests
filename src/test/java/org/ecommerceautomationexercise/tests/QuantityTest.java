package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.CartPage;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.ProductDetailPage;
import org.ecommerceautomationexercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QuantityTest extends BaseTest {
    @Test (dataProvider = "quantityData", dataProviderClass = utils.CSVReaderUtil.class, groups = {"regression"})
    public void testQuantityInCart (String quantity, boolean shouldSucceed) {
        driver.get("https://automationexercise.com");
        HomePage home = new HomePage(driver);
        home.goToProductPage();

        ProductsPage products = new ProductsPage(driver);
        products.clickFirstViewProduct();

        ProductDetailPage detail = new ProductDetailPage(driver);
        detail.setQuantity(quantity);
        detail.clickAddToCart();
        detail.viewCart();

        CartPage cart = new CartPage(driver);
        if (shouldSucceed) {
            Assert.assertEquals(cart.getQuantity(), Integer.parseInt(quantity), "Quantity mismatch in cart. ");
        } else {
            Assert.assertNotEquals(cart.getQuantity(), Integer.parseInt(quantity), "Invalid quantity was accepted.");
        }


    }
}
