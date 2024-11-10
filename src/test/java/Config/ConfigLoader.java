package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        loadProperties();
    }
    private static void loadProperties() {
        String environment = System.getProperty("env", "dev");  // По умолчанию dev
        String configFileName = String.format("%s.properties", environment);

        try (FileInputStream fis = new FileInputStream("src/test/resources/" + configFileName)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config file: " + configFileName);
        }
    }

    public static String getApiUrl() {
        return properties.getProperty("api.url");
    }

    public static String getApiUsername() {
        return properties.getProperty("api.username");
    }

    public static String getApiUserPassword() {
        return properties.getProperty("api.password");
    }

    public static String getDbUrl() { return properties.getProperty("db.connectionString");}

    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static String getHeadless() {
        return properties.getProperty("headless");
    }

    public static String getUiUrl() {
        return properties.getProperty("ui.url");
    }

    public static String getUiInventoryUrl() {
        return properties.getProperty("ui.inventory_url");
    }

    public static String getUiStandardUser() {
        return properties.getProperty("ui.standard_user");
    }

    public static String getUiStandardUserPassword() {
        return properties.getProperty("ui.standard_user_psw");
    }

    public static String getUiLockedUser() {
        return properties.getProperty("ui.locked_user");
    }

    public static String getUiLockedUserPassword() {
        return properties.getProperty("ui.locked_user_psw");
    }

    public static String getPerformanceGlitchUser() {
        return properties.getProperty("ui.performance_glitch_user");
    }

    public static String getPerformanceGlitchUserPassword() { return properties.getProperty("ui.performance_glitch_user_psw"); }

    public static String getTotalPrice() {
        return properties.getProperty("ui.total_price");
    }

    public static String getItemsAmount() {
        return properties.getProperty("ui.items_amount");
    }


}
