package Tests.Ui;

import Config.ConfigLoader;
import PageObject.Locators;
import PageObject.Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthTests {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(ConfigLoader.getUiUrl());
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешная авторизация стандартного пользователя")
    public void testSuccessfulLogin() throws IOException {
        loginPage.login(ConfigLoader.getUiStandardUser(), ConfigLoader.getUiStandardUserPassword());
        assertTrue(driver.getCurrentUrl().contains(ConfigLoader.getUiInventoryUrl()));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Авторизация заблокированного пользователя")
    public void testBlockedUserLogin() throws IOException {
        loginPage.login(ConfigLoader.getUiLockedUser(), ConfigLoader.getUiLockedUserPassword());
        assertTrue(driver.findElement(Locators.ERROR_MESSAGE).isDisplayed());
    }


}
