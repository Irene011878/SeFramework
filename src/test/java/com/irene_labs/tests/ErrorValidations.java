package com.irene_labs.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.irene_labs.CartPage;
import com.irene_labs.CheckoutPage;
import com.irene_labs.ConfirmationPage;
import com.irene_labs.ProductCatalogue;
import com.irene_labs.TestComponents.BaseTest;
import com.irene_labs.TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/*public class ErrorValidations extends BaseTest {  //esto es LoginErrorValidations

    @Test(groups = {"Error Handling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws InterruptedException, IOException {


        String productName = "ZARA COAT 3";
        landingPage.loginAplication("emmac@gmail.com", "1234/Abc");
        //here
        Assert.assertEquals("Incorrect email  password.",landingPage.getErrorMessage());


    }

    @Test
        public void productErrorValiations() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue =
                landingPage.loginAplication("iremma8@love.com", "1234/Abcd");

        //List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);

    }
}*/

//cambios 16 dic
public class ErrorValidations extends BaseTest {

    @Test(groups = {"Error Handling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws InterruptedException, IOException {

        landingPage.loginApplication("emmac@gmail.com", "1234/Abc");

        Assert.assertEquals(
                landingPage.getErrorMessage(),
                "Incorrect email or password."
        );
    }

    @Test(groups = {"Error Handling"})
    public void productErrorValidations() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue =
                landingPage.loginApplication("iremma8@love.com", "1234/Abcd");

        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Assert.assertFalse(
                cartPage.VerifyProductDisplay("ZARA COAT 33")
        );
    }
}



