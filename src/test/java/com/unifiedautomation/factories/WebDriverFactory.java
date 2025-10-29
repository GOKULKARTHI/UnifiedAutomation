package com.unifiedautomation.factories;

import com.unifiedautomation.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    public static WebDriver createDriver() {
        ConfigManager config = ConfigManager.getInstance();
        String browser = config.getString("browser");

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (config.getBoolean("headless")) {
                options.addArguments("--headless");
            }
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(config.getInt("implicitWait")));
            driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(config.getInt("pageLoadTimeout")));
            return driver;
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
