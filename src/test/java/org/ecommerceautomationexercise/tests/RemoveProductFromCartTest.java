package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.CartPage;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.ProductsPage;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class RemoveProductFromCartTest extends BaseTest {

    @Test(groups = {"regression", "full"}, priority = 1)
    public void testRemoveProductFromCart() {
        driver.get("https://automationexercise.com/");

        HomePage home = new HomePage(driver);
        home.goToProductPage();

        ProductsPage products = new ProductsPage(driver);
        products.addFirstProductToCart();
        products.goToCart();

        CartPage cartPage = new CartPage(driver);

        cartPage.removeFirstProductFromCart();
        assertTrue(cartPage.isCartEmpty(), "Cart is not empty after product removal");
    }
}
