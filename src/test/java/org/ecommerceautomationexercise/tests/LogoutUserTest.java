package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.LoginPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;


public class LogoutUserTest extends BaseTest {
    @Test(groups = {"full"}, priority = 1)
    public void testLogoutUser(){
        driver.get("https://automationexercise.com/");
        HomePage home = new HomePage(driver);
        home.goToSignup();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("lan984@example.com","pass123"); //use existing account
        assertTrue(loginPage.isLoggedinUserAsDisplayed(), "User not logged in");

        loginPage.logout();
        assertTrue(loginPage.isLoginPageVisible(),"Login page not visible after logout");
    }
}
