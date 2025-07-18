package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.Signature;

public class DownloadInvoiceTest extends BaseTest {

    @Test (groups = {"system", "full"}, priority = 1)
    public void testDownloadInvoiceAfterPurchaseOrder () {
        driver.get("https://automationexercise.com/");

        HomePage homePage = new HomePage(driver);
        homePage.goToSignup();

        // Sign up

        SignupPage signupPage = new SignupPage(driver);

        String name = "Lan";
        String email = "lan" + System.currentTimeMillis() + "@test.com";

        signupPage.fillSignupForm(name, email);
        signupPage.fillAccountInfo("123456", "10", "July", "2000");
        signupPage.fillAddressInfo("Lan", "Bui", "Fortna", "Street 1", "Apt 2", "India", "Haryana", "Hisar", "125001", "1234567890");
        signupPage.submitForm();

        Assert.assertTrue(signupPage.getResultMessage().contains("ACCOUNT CREATED!"));
        signupPage.accountCreated();
        Assert.assertTrue(signupPage.isLoggedinUserAsDisplayed(name));

        // Add to cart

        homePage.goToProductPage();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageVisible(), "Cart Page not visible");
        cartPage.clickProceedToCheckout();

        // Place order

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterComment("Test download invoice");
        checkoutPage.placeOrder();
        checkoutPage.enterPaymentDetails("Lan Bui", "1111222233334444", "123", "12", "2020");
        checkoutPage.confirmOrder();

        Assert.assertTrue(checkoutPage.isOrderSuccessVisible());

        // Download Invoice

        checkoutPage.downloadInvoice();
        Assert.assertTrue(checkoutPage.isInvoiceDownloaded(System.getProperty("user.home")+"/Downloads", "invoice"), "Invoice download failed");




    }
}
