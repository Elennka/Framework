package Tests.Ui;

import Config.ConfigLoader;
import PageObject.Locators;
import PageObject.Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E2eTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Запуск в headless режиме
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(ConfigLoader.getUiUrl());
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @ParameterizedTest
    @MethodSource("Data.TestDataProviderForUITests#provideTestData")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("E2E тест для разных пользователей")
    public void E2ETestFromLoginToCheckout(String username, String password, String expectedSum, int expectedItemCount) throws IOException {

        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setDescription("Тест для пользователя: " + username)
        );

        loginPage.login(username, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.INVENTORY_LIST));
        inventoryPage.addItemsToCart();
        inventoryPage.goToCart();
        assertEquals(expectedItemCount, cartPage.getCartItemCount(), "Количество товаров в корзине не совпадает с ожидаемым");
        cartPage.checkout();
        checkoutStepOnePage.fillCheckoutForm();
        assertEquals(expectedSum,checkoutStepTwoPage.getTotalPrice());
        checkoutStepTwoPage.finishCheckout();
    }
}

