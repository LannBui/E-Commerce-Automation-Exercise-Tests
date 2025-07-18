package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.ProductDetailPage;
import org.ecommerceautomationexercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddReviewTest extends BaseTest {
    @Test (dataProvider = "reviewData", dataProviderClass = utils.CSVReaderUtil.class, groups = {"regression", "full"}, priority = 1)
    public void testAddReviewToProduct(String name, String email, String review, String expectedResult) {
        driver.get("https://automationexercise.com");
        HomePage homePage = new HomePage(driver);
        homePage.goToProductPage();

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "Product page not shown");
        Assert.assertTrue(productsPage.areAllProductsDisplayed(), "All products not shown");
        productsPage.clickFirstViewProduct();

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        Assert.assertTrue(productDetailPage.isReviewSectionVisible(), "Review section not visible");

        productDetailPage.fillReviewForm(name, email, review);
        productDetailPage.submitReview();

        String actualMessage = productDetailPage.getReviewSuccessMessageText();
        if (expectedResult.equalsIgnoreCase("pass")){
            Assert.assertTrue(productDetailPage.isReviewSubmittedSuccessfully(), "Expected successful message not shown");
        } else {
            Assert.assertFalse(productDetailPage.isReviewSubmittedSuccessfully(), "Unexpected success message shown: " + actualMessage);
        }
    }
}
