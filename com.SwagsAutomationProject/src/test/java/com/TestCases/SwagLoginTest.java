package com.TestCases;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import swagPages.CartPage;
import swagPages.HomePage;
import swagPages.SwagLoginPage;

public class SwagLoginTest {

	   WebDriver driver;
	   SwagLoginPage loginPage;
	   HomePage homepage;
	   
	    @BeforeMethod
	    public void setup() {
	        driver = new ChromeDriver();
	        //driver.manage().window().maximize();
	        driver.get("https://www.saucedemo.com/v1/");
	        loginPage = new SwagLoginPage(driver);
	        homepage = new HomePage(driver);

	    }

	    @Test
	    public void testValidLogin() {
	        loginPage.login("standard_user", "secret_sauce");
	        String expectedUrl = "https://www.saucedemo.com/v1/inventory.html";
	        String actualUrl = driver.getCurrentUrl();
	        Assert.assertEquals(actualUrl, expectedUrl, "Login failed with valid credentials.");
	    }

	    @Test
	    public void testInvalidLogin() {
	        loginPage.login("invalid_user", "invalid_password");
	        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
	        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid login.");
	    }
	    
	    @Test
	    public void testEmptyCredentials() {
	        loginPage.login("", "");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));

	        String expectedMessage = "Epic sadface: Username is required";
	        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage, "Error message is incorrect for empty credentials.");
	    }
	    
	    @Test
	    public void testLockedOutUser() {
	        loginPage.login("locked_out_user", "secret_sauce");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
	        String expectedMessage = "Epic sadface: Sorry, this user has been locked out.";
	        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage, "Error message is incorrect for locked out user.");
	    }
	    
	    @Test
	    public void testHomepageNavigation() {
	        loginPage.login("standard_user", "secret_sauce");
	        
	        homepage.goToProducts();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/v1/inventory.html"));
	        String productsUrl = "https://www.saucedemo.com/v1/inventory.html";
	        Assert.assertEquals(driver.getCurrentUrl(), productsUrl, "Failed to navigate to Products page.");

	        
	        CartPage cartPage = new CartPage(driver);
	        cartPage.addToCart();
	        homepage.goToCart();	        
	        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/v1/cart.html"));
	        String cartUrl = "https://www.saucedemo.com/v1/cart.html";
	        Assert.assertEquals(driver.getCurrentUrl(), cartUrl, "Failed to navigate to Cart page.");

	        homepage.goToCheckout();
	        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/v1/checkout-step-one.html"));
	        String checkoutUrl = "https://www.saucedemo.com/v1/checkout-step-one.html";
	        Assert.assertEquals(driver.getCurrentUrl(), checkoutUrl, "Failed to navigate to Checkout page.");
	    }

	    @Test
	    public void testLogout() {
	        loginPage.login("standard_user", "secret_sauce");
	        homepage.logout();
	        String loginUrl = "https://www.saucedemo.com/v1/index.html";
	        Assert.assertEquals(driver.getCurrentUrl(), loginUrl, "Logout was unsuccessful.");
	    }

	    

	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
}
