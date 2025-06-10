package com.banking.listeners;

import com.banking.managers.DriverManager;
import com.banking.utils.LoggerUtil;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private static final Logger logger = LoggerUtil.getLogger();

    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtil.logMethodEntry(result);
        logger.error("Test failed: {}", result.getName());
        takeScreenshot(result.getName());
        LoggerUtil.logMethodExit(null);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] takeScreenshot(String testName) {
        LoggerUtil.logMethodEntry(testName);
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Files.write(Paths.get("target/screenshots/" + testName + "_" + timestamp + ".png"), screenshot);
            LoggerUtil.logMethodExit(screenshot);
            return screenshot;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
            LoggerUtil.logMethodExit(null);
            return new byte[0];
        }
    }
}