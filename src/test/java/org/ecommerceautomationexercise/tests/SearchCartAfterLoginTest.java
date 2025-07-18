package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.CartPage;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.LoginPage;
import org.ecommerceautomationexercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchCartAfterLoginTest extends BaseTest {

    @Test (dataProvider = "searchCartLoginData", dataProviderClass = utils.CSVReaderUtil.class, groups = {"integration", "full"}, priority = 1)
    public void testSearchCartAfterLogin (String keyword, String email, String password) {
        driver.get("https://automationexercise.com");
        HomePage homePage = new HomePage(driver);
        homePage.goToProductPage();

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "All products pages is not visible");

        productsPage.searchProduct(keyword);
        Assert.assertTrue(productsPage.isSearchProductsTitleVisible(), "Search product header not shown");

        productsPage.addFirstProductToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageVisible(), "Cart Page not shown");
        Assert.assertTrue(cartPage.isProductListVisible(), "Products not visible in cart");

        homePage.goToSignup();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        homePage.goToCartPage();
        Assert.assertTrue(cartPage.isProductListVisible(), "Products not retained in cart after login");

    }
}
