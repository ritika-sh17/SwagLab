package swagPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	 WebDriver driver;
	    WebDriverWait wait;

	    public CartPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	    }

	    By addToCartButton = By.xpath("//div[@id='inventory_container']/div/div[1]/div[3]/button");
	    By removeFromCartButton = By.xpath("//div[@id='inventory_container']/div/div[1]/div[3]/button");
	    By cartBadge = By.className("shopping_cart_badge");
	    By cartIcon = By.className("shopping_cart_link");

	    public void addToCart() {
	        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
	        button.click();
	    }

	    public void removeFromCart() {
	        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(removeFromCartButton));
	        button.click();
	    }

	    public String getCartBadgeCount() {
	        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
	        return badge.getText();
	    }

	    public void goToCart() {
	        WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
	        icon.click();
	    }
	    
}
