package org.ecommerceautomationexercise.tests;

import org.ecommerceautomationexercise.base.BaseTest;
import org.testng.annotations.Test;

public class DownloadInvoiceAfterPurchaseOrderTest extends BaseTest {
    @Test
    public void testDownloadInvoiceAfterPurchaseOrder () {
        driver.get("https://automationexercise.com/");

    }
}
