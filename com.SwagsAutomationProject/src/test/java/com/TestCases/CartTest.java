package com.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import swagPages.CartPage;
import swagPages.SwagLoginPage;

public class CartTest {

	 WebDriver driver;
	    SwagLoginPage loginPage;
	    CartPage cartPage;

	    @BeforeMethod
	    public void setup() {

	    	driver = new ChromeDriver();
	        //driver.manage().window().maximize();
	    	driver.get("https://www.saucedemo.com/v1/");
	        loginPage = new SwagLoginPage(driver);
	        cartPage = new CartPage(driver);
	        loginPage.login("standard_user", "secret_sauce");
	    }

	    @Test
	    public void testAddToCart() {
	        cartPage.addToCart();
	        String badgeCount = cartPage.getCartBadgeCount();
	        Assert.assertEquals(badgeCount, "1", "Cart badge count is incorrect after adding a product.");
	    }

	    @Test
	    public void testRemoveFromCart() {
	        cartPage.addToCart();
	        cartPage.removeFromCart();
	        boolean isBadgeDisplayed = driver.findElements(By.className("shopping_cart_badge")).size() > 0;
	        Assert.assertFalse(isBadgeDisplayed, "Cart badge should not be displayed after removing the product.");
	    }

	    @Test
	    public void testCartBadgeCount() {
	        cartPage.addToCart();
	        String badgeCount = cartPage.getCartBadgeCount();
	        Assert.assertEquals(badgeCount, "1", "Cart badge count is incorrect after adding a product.");

	        cartPage.removeFromCart();
	        boolean isBadgeDisplayed = driver.findElements(By.className("shopping_cart_badge")).size() > 0;
	        Assert.assertFalse(isBadgeDisplayed, "Cart badge should not be displayed after removing the product.");
	    }

	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
}
