package com.banking.stepdefinitions;

import com.banking.managers.DriverManager;
import com.banking.utils.LoggerUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {
    public static WebDriver driver;
    public static final Logger logger = LoggerUtil.getLogger();

    @Before
    public void setUp(Scenario scenario) {
        LoggerUtil.logMethodEntry();
        logger.info("Starting scenario: {}", scenario.getName());
        // Just call getDriver() to ensure initialization
        driver=DriverManager.getDriver();
        LoggerUtil.logMethodExit(null);
    }

    @After
    public void tearDown(Scenario scenario) {
        LoggerUtil.logMethodEntry();
        logger.info("Finished scenario: {} - Status: {}", scenario.getName(), scenario.getStatus());
        logger.info("Is scenario failed? {}", scenario.isFailed());
        if (scenario.isFailed()) {
            int retries = 2;
            String screenshotDir = "target/screenshots"; // Directory to save screenshots
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenshotName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
            String screenshotPath = screenshotDir + "/" + screenshotName;

            while (retries > 0) {
                try {
                    if (driver == null) {
                        logger.error("WebDriver is null, cannot capture screenshot");
                        break;
                    }
                    // Capture screenshot
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                    // Attach to Allure report
                    scenario.attach(screenshot, "image/png", scenario.getName());

                    // Save to file
                    Files.createDirectories(Paths.get(screenshotDir)); // Create directory if it doesn't exist
                    Files.write(Paths.get(screenshotPath), screenshot);

                    logger.info("Captured and saved screenshot for failed scenario: {} at {}",
                            scenario.getName(), screenshotPath);
                    break;
                } catch (Exception e) {
                    logger.error("Failed to capture screenshot (attempt {}): {}", retries, e.getMessage(), e);
                    retries--;
                    if (retries == 0) {
                        logger.error("Exhausted retries for screenshot capture");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        DriverManager.quitDriver();
        LoggerUtil.logMethodExit(null);
    }
}