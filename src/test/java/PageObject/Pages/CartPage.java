package PageObject.Pages;

import PageObject.Locators;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход на страницу ввода данных покупателя")
    public void checkout() {

        driver.findElement(Locators.CHECKOUT_BUTTON).click();
    }
    public int getCartItemCount() {
        driver.findElement(Locators.CART_ITEM_COUNT);
        return Integer.parseInt(driver.findElement(Locators.CART_ITEM_COUNT).getText());
    }
}
