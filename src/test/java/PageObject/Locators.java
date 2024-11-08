package PageObject;

import org.openqa.selenium.By;

public class Locators {

    // Локаторы для страницы логина
    public static final By USERNAME_FIELD = By.id("user-name");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By ERROR_MESSAGE = By.className("error-message-container");

    // Локаторы для страницы товаров (inventory)
    public static final By ADD_TO_CART_BACKPACK = By.id("add-to-cart-sauce-labs-backpack");
    public static final By ADD_TO_CART_TSHIRT = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    public static final By ADD_TO_CART_ONESIE = By.id("add-to-cart-sauce-labs-onesie");
    public static final By CART_ICON = By.className("shopping_cart_link");
    public static final By INVENTORY_LIST = By.className("inventory_list");

    // Локаторы для корзины
    public static final By CHECKOUT_BUTTON = By.id("checkout");
    public static final By  CART_ITEM_COUNT =By.className("shopping_cart_badge");

    // Локаторы для страницы оформления заказа
    public static final By FIRST_NAME_FIELD = By.id("first-name");
    public static final By LAST_NAME_FIELD = By.id("last-name");
    public static final By POSTAL_CODE_FIELD = By.id("postal-code");
    public static final By CONTINUE_BUTTON = By.id("continue");

    // Локаторы для страницы обзора заказа
    public static final By TOTAL_PRICE = By.className("summary_total_label");
    public static final By FINISH_BUTTON = By.id("finish");

}
