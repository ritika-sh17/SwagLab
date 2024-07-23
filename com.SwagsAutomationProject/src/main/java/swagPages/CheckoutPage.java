package swagPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

	 WebDriver driver;
	    WebDriverWait wait;

	    public CheckoutPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	    }

	    By cartIcon = By.className("shopping_cart_link");
	    By checkoutButton = By.xpath("//div[@id='cart_contents_container']/div/div[2]/a[2]");
	    By firstNameField = By.id("first-name");
	    By lastNameField = By.id("last-name");
	    By postalCodeField = By.id("postal-code");
	    By continueButton = By.xpath("//div[@id='checkout_info_container']/div/form/div[2]/input");
	    By finishButton = By.xpath("//div[@id='checkout_summary_container']/div/div[2]/div[8]/a[2]");
	    By errorMessage = By.xpath("//div[@id='checkout_info_container']/div/form/h3");

	    public void goToCart() {
	        WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
	        icon.click();
	    }

	    public void clickCheckout() {
	        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
	        button.click();
	    }

	    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
	        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
	        firstNameInput.sendKeys(firstName);

	        WebElement lastNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
	        lastNameInput.sendKeys(lastName);

	        WebElement postalCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField));
	        postalCodeInput.sendKeys(postalCode);
	    }

	    public void clickContinue() {
	        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
	        button.click();
	    }

	    public void clickFinish() {
	        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
	        button.click();
	    }

	    public String getErrorMessage() {
	        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
	        return error.getText();
	    }

	    public boolean isErrorMessageDisplayed() {
	        wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
	        return driver.findElements(errorMessage).size() > 0;
	    }
}
