package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.CartPage;
import org.ecommerceautomationexercise.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddFromRecommendedTest extends BaseTest {
    @Test (groups = {"regression", "integration"})
    public void testAddRecommendedProductToCart() {
        driver.get("https://automationexercise.com");

        HomePage homePage = new HomePage(driver);
        homePage.scrollToRecommendedItems();
        Assert.assertTrue(homePage.isRecommendedItemsVisible(), "Recommended items section not visible");

        homePage.addFirstRecommendedItemToCart();
        homePage.clickViewCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartNotEmpty(), "Cart is empty. Recommended product was not added.");
    }
}
