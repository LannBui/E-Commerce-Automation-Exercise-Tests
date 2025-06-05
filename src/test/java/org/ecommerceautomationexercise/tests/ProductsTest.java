package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.ProductsPage;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class ProductsTest extends BaseTest {

    @Test
    public void testProductDetailVisibility (){
        driver.get("https://automationexercise.com");

        HomePage home = new HomePage(driver);
        home.goToProductPage();

        ProductsPage productsPage = new ProductsPage(driver);
        assertTrue(productsPage.isAllProductsPageVisible(), "All products page not visible");
        assertTrue(productsPage.isProductListVisible(), "Product list not visible");
        productsPage.clickFirstViewProduct();
        assertTrue(productsPage.areAllDetailsVisible(), "Not all product details are visible");
    }

}
