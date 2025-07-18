package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.BrandPage;
import org.ecommerceautomationexercise.pages.HomePage;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class ViewBrandProductsTest extends BaseTest {
    @Test (groups = {"system", "full"}, priority = 1)
    public void testViewAndSwitchBrandProducts() {
        driver.get("https://automationexercise.com/");
        HomePage homePage = new HomePage(driver);
        homePage.goToProductPage();

        BrandPage brandPage = new BrandPage(driver);
        assertTrue(brandPage.isBrandSidebarVisible(), "Brand sidebar is not visible");

        brandPage.clickOnAnyBrand();
        assertTrue(brandPage.isBrandPageVisible(), "Polo brand page is not visible");

//        brandPage.clickOnAnyBrand("H&M");
//        assertTrue(brandPage.isBrandPageVisible("H&M PRODUCTS"), "H&M brand page is not visible");
    }
}
