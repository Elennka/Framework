package PageObject.Pages;

import Data.GenerateFakeData;
import PageObject.Locators;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage {
    private WebDriver driver;

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение формы данными покупателя")
    public void fillCheckoutForm() {
        GenerateFakeData fakeData = new GenerateFakeData();
        driver.findElement(Locators.FIRST_NAME_FIELD).sendKeys(fakeData.firstName());
        driver.findElement(Locators.LAST_NAME_FIELD).sendKeys(fakeData.firstName());
        driver.findElement(Locators.POSTAL_CODE_FIELD).sendKeys(fakeData.firstName());
        driver.findElement(Locators.CONTINUE_BUTTON).click();
    }
}
