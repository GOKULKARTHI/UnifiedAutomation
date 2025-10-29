package com.unifiedautomation.stepdefinitions;

import com.unifiedautomation.factories.MobileDriverFactory;
import com.unifiedautomation.managers.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class MobileStepDefinitions {
    private AppiumDriver driver;

    @Given("I launch the mobile application")
    public void iLaunchTheMobileApplication() {
        driver = MobileDriverFactory.createMobileDriver();
        if (driver == null) {
            System.out.println("Mobile device or Appium server not available. Skipping test.");
            Assertions.fail("Mobile driver not initialized.");
        } else {
            DriverManager.setDriver(driver);
        }
    }

    @Then("the mobile screen should be visible")
    public void theMobileScreenShouldBeVisible() {
        Assertions.assertNotNull(driver.getPageSource(), "Mobile screen not visible.");
    }
}
