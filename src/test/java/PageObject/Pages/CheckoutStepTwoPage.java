package PageObject.Pages;

import PageObject.Locators;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage {
    private WebDriver driver;

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получение итоговой суммы")
    public String getTotalPrice() {
        return driver.findElement(Locators.TOTAL_PRICE).getText();
    }

    @Step("Завершение покупки")
    public void finishCheckout() {
        driver.findElement(Locators.FINISH_BUTTON).click();
    }
}
