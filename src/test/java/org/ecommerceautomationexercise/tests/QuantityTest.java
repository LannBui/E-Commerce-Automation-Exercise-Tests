package org.ecommerceautomationexercise.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.CartPage;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.ProductDetailPage;
import org.ecommerceautomationexercise.pages.ProductsPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class QuantityTest extends BaseTest {
//    @Epic("E-Commerce")
//    @Feature("Cart")
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
        SoftAssert softAssert = new SoftAssert();


        CartPage cart = new CartPage(driver);
        if (shouldSucceed) {
            softAssert.assertEquals(cart.getQuantity(), Integer.parseInt(quantity), "Quantity mismatch in cart. ");
        } else {
            softAssert.assertNotEquals(cart.getQuantity(), Integer.parseInt(quantity), "Invalid quantity was accepted.");
        }

        softAssert.assertAll();


    }
}
