package com.unifiedautomation.factories;

import com.unifiedautomation.config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class MobileDriverFactory {
    public static AppiumDriver createMobileDriver() {
        String platform = ConfigManager.getString("mobile.platform", "android");
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            if ("android".equalsIgnoreCase(platform)) {
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("deviceName", ConfigManager.getString("mobile.deviceName", "emulator-5554"));
                capabilities.setCapability("automationName", "UiAutomator2");
                capabilities.setCapability("app", ConfigManager.getString("mobile.app", "/path/to/app.apk"));
                capabilities.setCapability("appPackage", ConfigManager.getString("mobile.appPackage"));
                capabilities.setCapability("appActivity", ConfigManager.getString("mobile.appActivity"));

                String appiumUrl = ConfigManager.getString("mobile.appiumUrl", "http://127.0.0.1:4723/wd/hub");
                return new AndroidDriver(new URL(appiumUrl), capabilities);
            } else if ("ios".equalsIgnoreCase(platform)) {
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("deviceName", ConfigManager.getString("mobile.deviceName", "iPhone Simulator"));
                capabilities.setCapability("automationName", "XCUITest");
                capabilities.setCapability("app", ConfigManager.getString("mobile.app", "/path/to/app.app"));

                String appiumUrl = ConfigManager.getString("mobile.appiumUrl", "http://127.0.0.1:4723/wd/hub");
                return new IOSDriver(new URL(appiumUrl), capabilities);
            } else {
                throw new IllegalArgumentException("Unsupported mobile platform: " + platform);
            }
        } catch (Exception e) {
            System.out.println("Appium server not running or device not available. Error: " + e.getMessage());
            return null;
        }
    }
}
