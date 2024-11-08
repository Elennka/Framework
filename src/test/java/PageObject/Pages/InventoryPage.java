package PageObject.Pages;

import PageObject.Locators;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Добавление товаров в корзину")
    public void addItemsToCart() {
        driver.findElement(Locators.ADD_TO_CART_BACKPACK).click();
        driver.findElement(Locators.ADD_TO_CART_TSHIRT).click();
        driver.findElement(Locators.ADD_TO_CART_ONESIE).click();
    }

    @Step ("Переход в корзину")
    public void goToCart() {
        driver.findElement(Locators.CART_ICON).click();
    }
}
