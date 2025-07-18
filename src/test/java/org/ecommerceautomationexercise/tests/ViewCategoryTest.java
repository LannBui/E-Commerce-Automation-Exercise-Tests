package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.CategoryPage;
import org.ecommerceautomationexercise.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewCategoryTest extends BaseTest {
    @Test (groups = {"system", "full"}, priority = 1)
    public void testViewCategoryProducts() {
        driver.get("https://automationexercise.com/");
        HomePage homePage = new HomePage(driver);
        homePage.goToProductPage();

        CategoryPage categoryPage = new CategoryPage(driver);

        Assert.assertTrue(categoryPage.isCategorySidebarVisible(), "Category Sidebar not visible");

        categoryPage.clickOnWomenCategory();
        categoryPage.clickOnWomenSubCategory("Dress");
        Assert.assertTrue(categoryPage.isCategoryHeaderVisible("WOMEN - DRESS PRODUCTS"),"Women Dress category header not shown");
        categoryPage.clickOnMenCategory();
        categoryPage.clickOnMenSubCategory("Tshirts");
        Assert.assertTrue(categoryPage.isCategoryHeaderVisible("MEN - TSHIRTS PRODUCTS"), "Men T-Shirts category header not shown");
    }
}
