package com.TestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import swagPages.ProductPage;
import swagPages.SwagLoginPage;

public class ProductTest {

	WebDriver driver;
	SwagLoginPage loginPage;
    ProductPage productPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/");
        loginPage = new SwagLoginPage(driver);
        productPage = new ProductPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testProductListing() {
        List<WebElement> products = productPage.getAllProducts();
        Assert.assertTrue(products.size() > 0, "No products are displayed on the product page.");
    }

    @Test
    public void testProductSorting() {
        productPage.sortProducts("hilo");
        List<WebElement> products = productPage.getAllProducts();
       
    }

    @Test
    public void testProductDetails() {
        String expectedProductTitle = productPage.getFirstProductTitle();
        productPage.clickFirstProduct();
        WebElement productTitle = driver.findElement(By.className("inventory_details_name"));
        Assert.assertEquals(productTitle.getText(), expectedProductTitle, "Product details page does not show the correct product.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
