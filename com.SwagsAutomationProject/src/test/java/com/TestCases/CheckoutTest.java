package com.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import swagPages.CartPage;
import swagPages.CheckoutPage;
import swagPages.SwagLoginPage;

public class CheckoutTest {

	WebDriver driver;
    SwagLoginPage loginPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setup() {
    	
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
    	driver.get("https://www.saucedemo.com/v1/");
        loginPage = new SwagLoginPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testCheckoutFlow() {
        cartPage.addToCart();
        cartPage.goToCart();
        checkoutPage.clickCheckout();
        checkoutPage.enterCheckoutInformation("John", "Doe", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        
        // Verify that the checkout was successful (depends on specific confirmation message or URL)
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete"), "Checkout was not completed successfully.");
    }

    @Test
    public void testCheckoutWithEmptyCart() {
        cartPage.goToCart();
        boolean isCheckoutButtonDisplayed = driver.findElements(By.id("checkout")).size() > 0;
        Assert.assertFalse(isCheckoutButtonDisplayed, "Checkout button should not be displayed with an empty cart.");
    }

    @Test
    public void testCheckoutInformationRequired() {
        cartPage.addToCart();
        cartPage.goToCart();
        checkoutPage.clickCheckout();
        checkoutPage.clickContinue();

        // Verify that an error message is displayed
        boolean errorMessageDisplayed = checkoutPage.isErrorMessageDisplayed();
        Assert.assertTrue(errorMessageDisplayed, "Error message should be displayed when required information is missing.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
