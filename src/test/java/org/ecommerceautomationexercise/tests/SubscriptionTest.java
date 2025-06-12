package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SubcriptionTest extends BaseTest {

    @Test(dataProvider = "subscriptionData", dataProviderClass = utils.CSVReaderUtil.class)

    public void testFooterSubscription(String email, String expectedResult, String expectedMessage) {
        driver.get("https://automationexercise.com/");

        HomePage home = new HomePage(driver);
        assertTrue(home.isHomeVisible(), "Home page not visible");

        home.scrollToSubscription();
        assertTrue(home.isSubscriptionTextVisible(), "Subscription text not visible");

        home.enterSubscriptionEmail(email);
        if (expectedResult.equalsIgnoreCase("success")) {
            assertTrue(home.isSubscriptionSuccessVisible(), "Subscription success not visible");
        } else if (expectedResult.equalsIgnoreCase("fail")) {
            assertFalse(home.isSubscriptionSuccessVisible(), "Subscription should not succeed with invalid email");
        } else if (expectedResult.equalsIgnoreCase("false")) {
            assertTrue(home.isEmailFormatErrorShown(), "Expected error message not shown");
        }
    }

}
