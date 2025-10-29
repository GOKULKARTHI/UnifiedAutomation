package com.unifiedautomation.stepdefinitions;

import com.unifiedautomation.config.ConfigManager;
import com.unifiedautomation.managers.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class WebStepDefinitions {
    private WebDriver driver = DriverManager.getDriver();

    @Given("I open the application")
    public void iOpenTheApplication() {
        ConfigManager config = ConfigManager.getInstance();
        String baseUrl = config.getString("baseUrl");
        driver.get(baseUrl);
    }

    @Then("the title should be {string}")
    public void theTitleShouldBe(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }
}
