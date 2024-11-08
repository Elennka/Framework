package PageObject.Pages;

import PageObject.Locators;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step(value="Авторизация пользователя")
    public void login(String username, String password) {
        driver.findElement(Locators.USERNAME_FIELD).sendKeys(username);
        driver.findElement(Locators.PASSWORD_FIELD).sendKeys(password);
        driver.findElement(Locators.LOGIN_BUTTON).click();
    }
}
