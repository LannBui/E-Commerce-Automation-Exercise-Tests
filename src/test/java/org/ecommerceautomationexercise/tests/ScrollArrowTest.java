package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScrollArrowTest extends BaseTest {
    @Test (groups = {"system", "full"}, priority = 1)
    public void testScrollArrowTest() {
        driver.get("https://automationexercise.com");
        HomePage homePage = new HomePage(driver);

        homePage.scrollToBottom();
        Assert.assertTrue(homePage.isSubscriptionVisible(), "Subscription section not visible at the bottom");

        homePage.clickScrollUpArrow();
        Assert.assertTrue(homePage.isScrollUpTextVisible(), "Scroll up text is not visible");

    }
}
