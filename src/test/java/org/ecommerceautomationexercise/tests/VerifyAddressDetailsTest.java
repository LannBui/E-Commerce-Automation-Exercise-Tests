package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyAddressDetailsTest extends BaseTest {
    @Test (groups = {"integration", "full"}, priority = 1)
    public void testVerifyAddressDetailsInCheckoutPage() {
        driver.get("https://automationexercise.com");

        HomePage homePage = new HomePage(driver);
        homePage.goToSignup();
        SignupPage signupPage = new SignupPage(driver);
        String name = "Lan";
        String email = "lan" + System.currentTimeMillis() + "@test.com";
        signupPage.fillSignupForm(name, email);
        signupPage.fillAccountInfo("123456", "10", "July", "2000");
        signupPage.fillAddressInfo("Lan", "Bui", "Fortna", "Street 1", "Apt 2", "India", "Haryana", "Hisar", "125001", "1234567890");
        signupPage.submitForm();

        Assert.assertTrue(signupPage.getResultMessage().contains("ACCOUNT CREATED!"));
        signupPage.accountCreated();
//        System.out.println(driver.getPageSource());

        Assert.assertTrue(signupPage.isLoggedinUserAsDisplayed("Lan"));

        // Add product to cart
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.goToCart();

        // Proceed Checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.clickProceedToCheckout();

        // Verify address detail
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        String deliveryAddress = checkoutPage.getDeliveryAddressText();
        String billingAddress = checkoutPage.getBillingAddressText();

        //Address info filled earlier

        String expectedLine = "Lan Bui Fortna Street 1 Apt 2 Hisar Haryana 125001 India";

//        String actualDeliveryAddress = checkoutPage.getDeliveryAddressText();
//        System.out.println("=== Delivery Address ===" + actualDeliveryAddress);
//
//        String actualBillingAddress = checkoutPage.getBillingAddressText();
//        System.out.println("=== Billing Address ===" + actualBillingAddress);

        Assert.assertTrue(deliveryAddress.contains(expectedLine), "Delivery address does not match registration details.");
        Assert.assertTrue(billingAddress.contains(expectedLine), "Billing address does not match registration details.");

    }

}
