package swagPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {

	WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    By productItems = By.className("inventory_item");
    By productSortContainer = By.className("product_sort_container");
    By firstProductTitle = By.xpath("(//div[@class='inventory_item_name'])[1]");

    public List<WebElement> getAllProducts() {
        return driver.findElements(productItems);
    }

    public void sortProducts(String sortingOption) {
        WebElement sortContainer = driver.findElement(productSortContainer);
        sortContainer.click();
        WebElement option = driver.findElement(By.xpath("//option[@value='" + sortingOption + "']"));
        option.click();
    }

    public void clickFirstProduct() {
        driver.findElement(firstProductTitle).click();
    }

    public String getFirstProductTitle() {
        return driver.findElement(firstProductTitle).getText();
    }
}
