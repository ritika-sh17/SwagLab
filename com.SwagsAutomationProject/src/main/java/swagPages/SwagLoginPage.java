package swagPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SwagLoginPage {

	WebDriver driver;

    public SwagLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//h3[@data-test='error']");
    By menuButton = By.id("react-burger-menu-btn");
    By logoutLink = By.id("logout_sidebar_link");

    public void setUsername(String strUsername) {
        driver.findElement(username).sendKeys(strUsername);
    }

    public void setPassword(String strPassword) {
        driver.findElement(password).sendKeys(strPassword);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String strUsername, String strPassword) {
        setUsername(strUsername);
        setPassword(strPassword);
        clickLogin();
    }
    
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
    public void logout() {
        driver.findElement(menuButton).click();
        driver.findElement(logoutLink).click();
    }
}
