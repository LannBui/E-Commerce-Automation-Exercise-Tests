package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class CartSubscription extends BaseTest {

    @Test (dataProvider = "subscriptionData", dataProviderClass = utils.CSVReaderUtil.class, groups = {"regression"})

    public void testSubscriptionCartPage(String email, String expectedResult, String expectedMessage) {
        driver.get("https://automationexercise.com");

        HomePage home = new HomePage(driver);
        home.goToCartPage();
        home.scrollToSubscription();
        home.enterSubscriptionEmail(email);
        if (expectedResult.equalsIgnoreCase("success")) {
            assertTrue(home.isSubscriptionSuccessVisible(), "Success message not visible");
        } else if (expectedResult.equalsIgnoreCase("fail")) {
            assertFalse(home.isSubscriptionSuccessVisible(), "Subsciption should fail for empty email");
        } else if (expectedResult.equalsIgnoreCase("false")) {
            assertTrue(home.isEmailFormatErrorShown(), "Invalid Email format should show error message");
        }

    }

}
