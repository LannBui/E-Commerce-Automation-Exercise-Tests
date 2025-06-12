package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.base.BaseTestNoIncognito;
import org.ecommerceautomationexercise.pages.CartPage;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.ProductsPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class AddProductsToCartTest extends BaseTest {

    @Test (groups = {"regression"})
    public void testAddTwoProductsToCart() {
        driver.get("https://automationexercise.com");

        HomePage home = new HomePage(driver);
        home.goToProductPage();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstTwoProductsToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        home.goToCartPage();

        assertTrue(cartPage.areTwoProductsInCart(), "Two products not found in cart");
        assertTrue(cartPage.verifyPriceQuantityTotal(), "Price * Quantity != Total");
    }

}
