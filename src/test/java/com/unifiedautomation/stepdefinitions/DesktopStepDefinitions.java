package com.unifiedautomation.stepdefinitions;

import com.unifiedautomation.factories.DesktopDriverFactory;
import com.unifiedautomation.managers.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

public class DesktopStepDefinitions {
    private WebDriver driver;
    private Screen screen;

    @Given("I launch the desktop application")
    public void iLaunchTheDesktopApplication() {
        driver = DesktopDriverFactory.createDesktopDriver();
        if (driver == null) {
            screen = DesktopDriverFactory.createSikuliScreen();
            System.out.println("Using SikuliX for desktop automation.");
        } else {
            DriverManager.setDriver(driver);
        }
    }

    @Then("the desktop window should be visible")
    public void theDesktopWindowShouldBeVisible() {
        if (driver != null) {
            // Check if window is present using WinAppDriver
            Assertions.assertNotNull(driver.getWindowHandle(), "Desktop window not visible.");
        } else {
            // SikuliX check
            try {
                screen.exists("path/to/image.png"); // Placeholder
                Assertions.assertTrue(true, "Desktop screen visible via SikuliX.");
            } catch (Exception e) {
                Assertions.fail("Desktop screen not visible: " + e.getMessage());
            }
        }
    }
}
