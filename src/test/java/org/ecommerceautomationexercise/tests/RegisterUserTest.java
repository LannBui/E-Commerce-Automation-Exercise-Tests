package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.HomePage;
import org.ecommerceautomationexercise.pages.SignupPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegisterUserTest extends BaseTest {

    @Test (dataProvider = "signupData", dataProviderClass = utils.CSVReaderUtil.class, groups = {"smoke", "sanity", "regression"})
    public void testRegisterUser(String name, String email, String password, String date
    , String month, String year, String fname, String lname, String company, String addr1
    ,String addr2, String country, String state, String city, String zip, String mobile
    , String expectedResult, String expectedMessage){

        driver.get("https://automationexercise.com/");
        HomePage home = new HomePage(driver);
        home.goToSignup();

        SignupPage signup = new SignupPage(driver);

        if (expectedResult.equalsIgnoreCase("pass") || email.equalsIgnoreCase("DYNAMIC")) {
            email = "user" + System.currentTimeMillis() + "@testmail.com";
        }
        signup.fillSignupForm(name, email);

        // Email already exists
        if (expectedResult.equalsIgnoreCase("fail") && expectedMessage.contains("exist")){
            assertTrue(signup.isEmailAlreadyUsedErrorDisplayed(),"Expected email error not shown");
            return;
        }
        //Missing required fields (name/email)
        if (expectedResult.equalsIgnoreCase("fail")){
            assertEquals(signup.isValidationErrorDisplayed(),"Expected signup error not displayed");
            return;
        }

        //Positive flow
        signup.fillAccountInfo(password, date, month, year);
        signup.fillAddressInfo(fname, lname, company, addr1, addr2, country, state, city, zip, mobile);
        signup.submitForm();
        String actualMessage = signup.getResultMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Expected: "+ expectedMessage +", but got: "+actualMessage);

        signup.accountCreated();
        if (expectedResult.equalsIgnoreCase("pass")){
            assertTrue(signup.isLoggedinUserAsDisplayed(name), "User not logged in as expected");

            signup.accountDeleted();
            assertTrue(signup.isAccountDeletedDisplayed(), "Account deleted message not displayed");
        }
    }
}
