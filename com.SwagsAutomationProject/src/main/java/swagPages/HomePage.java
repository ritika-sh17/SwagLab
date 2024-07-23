package swagPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
    }

    By menuButton = By.xpath("//div[@id='menu_button_container']/div/div[3]/div");
    By productsLink = By.xpath("//a[@id='inventory_sidebar_link']");
    By cartLink = By.xpath("//a[contains(@class, 'shopping_cart_link')]");
    By checkoutLink = By.xpath("//div[@id='cart_contents_container']/div/div[2]/a[2]");

    public void openMenu() {
        WebElement menuBtn = wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        menuBtn.click();
    }

    public void goToProducts() {
    	openMenu();
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(productsLink));
        link.click();
    }

    public void goToCart() {
    	
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        link.click();
    }

    public void goToCheckout() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(checkoutLink));
        link.click();
    }

    public void logout() {
        openMenu();
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='logout_sidebar_link']")));
        link.click();
    }
    
}