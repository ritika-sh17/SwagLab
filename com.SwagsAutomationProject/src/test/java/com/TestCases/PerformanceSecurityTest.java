package com.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import swagPages.PerformanceSecurityPage;
import swagPages.SwagLoginPage;

public class PerformanceSecurityTest {

	WebDriver driver;
    SwagLoginPage loginPage;
    PerformanceSecurityPage perfSecPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
        loginPage = new SwagLoginPage(driver);
        perfSecPage = new PerformanceSecurityPage(driver);
        driver.get("https://www.saucedemo.com/v1/");
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testPageLoadTime() {
        String url = "https://www.saucedemo.com/v1/inventory.html";
        long loadTime = perfSecPage.getPageLoadTime(url);
        long acceptableLoadTime = 5000; // 5 seconds
        Assert.assertTrue(loadTime < acceptableLoadTime, "Page load time is too high: " + loadTime + " milliseconds");
    }

    @Test
    public void testSecureConnection() {
        Assert.assertTrue(perfSecPage.isConnectionSecure(), "The connection is not secure.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
