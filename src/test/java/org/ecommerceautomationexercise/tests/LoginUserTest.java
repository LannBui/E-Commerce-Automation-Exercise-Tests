package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.LoginPage;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class LoginUserTest extends BaseTest {

    @Test (dataProvider = "loginData", dataProviderClass = utils.CSVReaderUtil.class)
    public void testLoginUser(String name, String email, String password, String expectedResult, String expectedMessage){
        driver.get("https://automationexercise.com/");
        HomePage home = new HomePage(driver);
        home.goToSignup();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email,password);

        if (expectedResult.equalsIgnoreCase("success")){
            assertTrue(loginPage.isLoggedinUserAsDisplayed(name), "User not logged in as expected");
            loginPage.accountDeleted();
            assertTrue(loginPage.isAccountDeletedDisplayed(),"Account deleted message not displayed");
        }else {
            assertTrue(loginPage.isErrorMessageDisplayed(),"Expected login error not displayed");
        }

    }
}
