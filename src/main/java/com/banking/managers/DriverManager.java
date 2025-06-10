package com.banking.managers;

import com.banking.base.WebDriverFactory;
import com.banking.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final Logger logger = LoggerUtil.getLogger();
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        LoggerUtil.logMethodEntry();
        if (driver.get() == null) {
            driver.set(WebDriverFactory.createDriver());
            logger.info("Created new WebDriver instance for browser: {}",
                    ConfigManager.getProperty("browser"));
        }
        LoggerUtil.logMethodExit(driver.get());
        return driver.get();
    }

    public static void quitDriver() {
        LoggerUtil.logMethodEntry();
        if (driver.get() != null) {
            try {
                driver.get().quit();
                logger.info("WebDriver quit");
            } finally {
                driver.remove();
            }
        }
        LoggerUtil.logMethodExit(null);
    }
}