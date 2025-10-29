package com.unifiedautomation.factories;

import com.unifiedautomation.config.ConfigManager;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.script.Screen;

import java.net.URL;

public class DesktopDriverFactory {
    public static WebDriver createDesktopDriver() {
        String platform = ConfigManager.getString("desktop.platform", "windows");
        if ("windows".equalsIgnoreCase(platform)) {
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("app", ConfigManager.getString("desktop.app", "Root"));
                capabilities.setCapability("platformName", "Windows");
                capabilities.setCapability("deviceName", "WindowsPC");

                String winAppDriverUrl = ConfigManager.getString("desktop.winAppDriverUrl", "http://127.0.0.1:4723");
                return new WindowsDriver(new URL(winAppDriverUrl), capabilities);
            } catch (Exception e) {
                System.out.println("WinAppDriver not available. Falling back to SikuliX for image-based interactions.");
                return null; // SikuliX fallback
            }
        } else {
            // SikuliX for cross-platform
            return null; // Placeholder for SikuliX Screen
        }
    }

    public static Screen createSikuliScreen() {
        return new Screen(); // SikuliX Screen for image-based automation
    }
}
