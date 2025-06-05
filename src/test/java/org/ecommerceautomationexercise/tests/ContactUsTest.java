package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.ecommerceautomationexercise.pages.ContactUsPage;
import org.ecommerceautomationexercise.pages.HomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ContactUsTest extends BaseTest {

    @Test (dataProvider = "contactFormData", dataProviderClass = utils.CSVReaderUtil.class)
    public void testContactForm (String name, String email, String subject, String message, String filePath, String
                                 expectedResult, String expectedMessage){
        driver.get("https://automationexercise.com/");

        HomePage home = new HomePage(driver);
        home.goToContactUs();

        ContactUsPage contact = new ContactUsPage(driver);
        assertTrue(contact.isGetInTouchVisible(), "'GET IN TOUCH' is not visible");

        contact.fillForm(name, email, subject, message, filePath);

        if (expectedResult.equalsIgnoreCase("fail")) {
            assertTrue(contact.isGetInTouchVisible(),"User should stay in the Contact Page if providing invalid Email");
        }
        if (expectedResult.equalsIgnoreCase("success")){
            assertTrue(contact.isSuccessMessageVisible(), "Success message not visible for valid input");
            contact.clickHomeBtn();
            assertTrue(contact.isHomePageVisible(), "Home page not visible after return");
        }
    }
}
