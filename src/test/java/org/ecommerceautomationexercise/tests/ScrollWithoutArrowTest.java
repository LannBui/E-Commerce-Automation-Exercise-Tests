package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScrollWithoutArrowTest extends BaseTest {

    @Test (groups = {"system", "full"}, priority = 1)
    public void testScrollUpWithoutArrow() {
        driver.get("https://automationexercise.com");
        HomePage homePage = new HomePage(driver);
        homePage.scrollToBottom();
        Assert.assertTrue(homePage.isSubscriptionVisible(), "Subscription section not visible at bottom");

        homePage.scrollToTop();
        Assert.assertTrue(homePage.isScrollUpTextVisible(), "Scroll up text is not visible after manual scroll ");

    }
}
