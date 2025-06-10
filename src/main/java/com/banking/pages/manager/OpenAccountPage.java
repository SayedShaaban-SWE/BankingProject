package com.banking.pages.manager;

import com.banking.constants.AppConstants;
import com.banking.constants.XPaths;
import com.banking.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OpenAccountPage {
    private final WebDriver driver;
    private final Logger logger = LoggerUtil.getLogger();
    private final WebDriverWait wait;

    // By locators
    private final By customerDropdown = By.xpath(XPaths.CUSTOMER_DROPDOWN);
    private final By currencyDropdown = By.xpath(XPaths.CURRENCY_DROPDOWN);
    private final By submitBtn = By.xpath(XPaths.SUBMIT_BUTTON);

    private static String accountNumber;


    public OpenAccountPage(WebDriver driver) {
        LoggerUtil.logMethodEntry(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.EXPLICIT_WAIT_TIMEOUT));
        LoggerUtil.logMethodExit(null);
    }

    public boolean isOpenAccountPageDisplayed() {
        LoggerUtil.logMethodEntry();
        boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(customerDropdown)).isDisplayed();
        LoggerUtil.logMethodExit(result);
        return result;
    }

    public void selectAccountDetails(String customerName, String currency) {
        LoggerUtil.logMethodEntry(customerName, currency);
        logger.info("Opening account for: {}, currency: {}", customerName, currency);
        Select customerSelect = new Select(driver.findElement(customerDropdown));
        customerSelect.selectByVisibleText(customerName);
        Select currencySelect = new Select(driver.findElement(currencyDropdown));
        currencySelect.selectByVisibleText(currency);
        LoggerUtil.logMethodExit(null);
    }

    public void clickProcessButton() {
        LoggerUtil.logMethodEntry();
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        LoggerUtil.logMethodExit(null);
    }

    public boolean isNewAccountSuccessMsgDisplayed() {
        LoggerUtil.logMethodEntry();
        String alertText = driver.switchTo().alert().getText(); // Full alert text
        boolean result = driver.switchTo().alert().getText().contains("Account created successfully with account Number");
        String[] parts = alertText.split(":"); // Split into parts
        accountNumber = parts[1].trim(); // "The idxx"
        driver.switchTo().alert().accept();
        LoggerUtil.logMethodExit(result);
        return result;
    }

    public String getTheCreatedAccountNumber() {
        return accountNumber;
    }
}