package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.ProductsPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.WeakHashMap;

import static org.testng.Assert.*;

public class SearchProductTest extends BaseTest {

    @Test(dataProvider = "searchKeywords", dataProviderClass = utils.CSVReaderUtil.class)
    public void testSearchFunctionality (String keyword, boolean expectedToFind){
        driver.get("https://automationexercise.com/");

        HomePage home = new HomePage(driver);
        home.goToProductPage();

        ProductsPage productsPage = new ProductsPage(driver);

        assertTrue(productsPage.isAllProductsPageVisible(), "All products page not visible");

        productsPage.searchProduct(keyword);
        assertTrue(productsPage.isSearchProductsTitleVisible(), "Searched Products title not visible");

//        boolean actual = productsPage.areSearchedResultsVisible();
//        assertEquals(actual, expectedToFind, "Missmatch in search result visibility for keyword: " + keyword);

        List<WebElement> resultTitles = productsPage.getSearchResultTitles();

        if (expectedToFind){
            assertFalse(resultTitles.isEmpty(), "Expected products to be found, but none were displayed");
            for (WebElement title : resultTitles) {
                assertTrue(
                        title.getText().toLowerCase().contains(keyword.toLowerCase()),
                        "Product title does not contain the keyword: " + keyword + " | Actual: " + title.getText()
                );
            }
        } else {
            assertTrue(resultTitles.isEmpty(), "Expected no products, but some were displayed");
        }
    }
}
