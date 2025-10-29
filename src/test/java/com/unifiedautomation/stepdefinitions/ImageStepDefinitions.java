package com.unifiedautomation.stepdefinitions;

import com.unifiedautomation.managers.DriverManager;
import com.unifiedautomation.utils.ImageCompareUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageStepDefinitions {
    private WebDriver driver = DriverManager.getDriver();
    private String screenshotPath = "target/screenshots/actual.png";
    private String baselinePath = "src/test/resources/baselines/example.png";
    private String diffPath = "target/screenshots/diff.png";

    @Given("I capture a screenshot")
    public void iCaptureAScreenshot() throws IOException {
        AShot aShot = new AShot();
        Screenshot screenshot = aShot.takeScreenshot(driver);
        BufferedImage image = screenshot.getImage();

        // Ensure directories exist
        Files.createDirectories(Paths.get("target/screenshots"));
        Files.createDirectories(Paths.get("src/test/resources/baselines"));

        ImageIO.write(image, "PNG", new File(screenshotPath));

        // Save baseline if absent
        ImageCompareUtil.saveBaselineIfAbsent(baselinePath, image);
    }

    @Then("the screenshot should match the baseline")
    public void theScreenshotShouldMatchTheBaseline() throws IOException {
        if (!Files.exists(Paths.get(baselinePath))) {
            System.out.println("Baseline not found. Scenario marked as pending. Approve baseline and re-run.");
            Assertions.fail("Baseline image not found. Please approve the saved baseline.");
        }

        boolean matches = ImageCompareUtil.compareImages(baselinePath, screenshotPath, diffPath);
        if (!matches) {
            System.out.println("Images differ. Diff saved at: " + diffPath);
        }
        Assertions.assertTrue(matches, "Screenshot does not match baseline. Check diff at: " + diffPath);
    }
}
