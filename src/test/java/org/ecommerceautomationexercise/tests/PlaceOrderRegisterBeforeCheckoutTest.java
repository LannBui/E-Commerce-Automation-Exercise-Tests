package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlaceOrderRegisterBeforeCheckoutTest extends BaseTest {
    @Test (groups = {"integration", "full"}, priority = 1)
    public void testPlaceOrderRegisterDuringCheckout() {
        driver.get("https://automationexercise.com");
        HomePage home = new HomePage(driver);
        home.goToProductPage();

        ProductsPage products = new ProductsPage(driver);
        products.addFirstProductToCart();
        products.goToCart();

        CartPage cart = new CartPage(driver);
        cart.clickProceedToCheckout();
        cart.registerOrLoginToProceedOnCheckout();

        SignupPage signup = new SignupPage(driver);
        signup.fillSignupForm("Lan", "lan" + System.currentTimeMillis() + "@test.com");
        signup.fillAccountInfo("test123", "1", "January", "2000");
        signup.fillAddressInfo("Lan", "Bui", "Company", "Address1", "Address2", "Canada", "ON", "Toronto", "123456", "1234567890");
        signup.submitForm();
        signup.accountCreated();

        assertTrue(signup.isLoggedinUserAsDisplayed("Lan"));

        home.goToCartPage();
        cart.clickProceedToCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterComment("Placing order after registering");
        checkout.placeOrder();
        checkout.enterPaymentDetails("Lan Bui", "1111222233334444", "123", "12", "2020");
        checkout.confirmOrder();

        assertTrue(checkout.isOrderSuccessVisible());

//        signup.accountDeleted();
//        assertTrue(signup.isAccountDeletedDisplayed());
    }
}
