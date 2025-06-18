package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.CartPage;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.ProductDetailPage;
import org.ecommerceautomationexercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class QuantityTest extends BaseTest {

    @Test (dataProvider = "quantityData", dataProviderClass = utils.CSVReaderUtil.class, groups = {"regression", "smoke"})
    public void testQuantityInCart (String quantity, boolean shouldSucceed) {
        driver.get("https://automationexercise.com");
        HomePage home = new HomePage(driver);
        home.goToProductPage();

        ProductsPage products = new ProductsPage(driver);
        products.clickFirstViewProduct();

        ProductDetailPage detail = new ProductDetailPage(driver);
        detail.setQuantity(quantity);
        detail.clickAddToCart();

        if (shouldSucceed) {
            detail.viewCart();
        }
        CartPage cart = new CartPage(driver);
        boolean redirected = detail.isRedirectToCart();
        if (shouldSucceed) {
            Assert.assertTrue(redirected, "Expected to be redirected to cart for quantity: " + quantity);
            Assert.assertEquals(cart.getQuantity(), Integer.parseInt(quantity), "Quantity mismatch for quantity: " + quantity);
        } else {
            // Negative case: user should NOT be redirected to cart
            Assert.assertFalse(redirected, "Invalid quantity '" + quantity + "' should not redirect to cart.");

            // Optionally: Verify cart is empty
            Assert.assertTrue(home.isStillOnProductPage(), "Expected to stay on product page with invalid quantity: " + quantity);
        }

    }
}
