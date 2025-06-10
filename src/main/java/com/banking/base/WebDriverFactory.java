package com.banking.base;

import com.banking.managers.ConfigManager;
import com.banking.utils.LoggerUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {
    private static final Logger logger = LoggerUtil.getLogger();

    public static WebDriver createDriver() {
        LoggerUtil.logMethodEntry();
        String browser = ConfigManager.getProperty("browser");
        logger.info("Creating WebDriver for browser: {}", browser);

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().clearDriverCache().setup(); // Clear old versions
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                logger.debug("Initialized ChromeDriver");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                logger.debug("Initialized FirefoxDriver");
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                logger.debug("Initialized EdgeDriver");
                return new EdgeDriver(edgeOptions);

            default:
                logger.error("Browser not supported: {}", browser);
                throw new IllegalArgumentException("Browser not supported: " + browser +
                        ". Supported browsers are: chrome, firefox, edge");
        }

    }
}