package org.ecommerceautomationexercise.tests;
import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlaceOrderRegisterDuringCheckoutTest extends BaseTest {
    @Test (groups = {"integration"})
    public void testPlaceOrderLoginBeforeCheckout() {
        driver.get("https://automationexercise.com");
        HomePage home = new HomePage(driver);
        home.goToSignup();

        LoginPage login = new LoginPage(driver);
        login.login("validusertest@example.com", "validpass");

//        assertTrue(login.isLoggedinUserAsDisplayed("Existing User"));

        home.goToProductPage();
        ProductsPage products = new ProductsPage(driver);
        products.addFirstProductToCart();
        products.goToCart();

        CartPage cart = new CartPage(driver);
        cart.clickProceedToCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterComment("Placing order as logged-in user");
        checkout.placeOrder();
        checkout.enterPaymentDetails("Lan Bui", "1111222233334444", "123", "12", "2020");
        checkout.confirmOrder();

        assertTrue(checkout.isOrderSuccessVisible());

//        login.deleteAccount();
//        assertTrue(login.isAccountDeletedDisplayed());
    }
}
