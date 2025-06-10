package com.banking.pages;

import com.banking.constants.AppConstants;
import com.banking.constants.XPaths;
import com.banking.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final Logger logger = LoggerUtil.getLogger();
    private final WebDriverWait wait;

    // By locators
    private final By bankManagerLoginBtn = By.xpath(XPaths.BANK_MANAGER_LOGIN_BTN);
    private final By customerLoginBtn = By.xpath(XPaths.CUSTOMER_LOGIN_BTN);
    private final By customerSelectLogin = By.xpath(XPaths.CUSTOMER_SELECT_LOGIN);
    private final By loginBtn = By.xpath(XPaths.LOGIN_BTN);



    public LoginPage(WebDriver driver) {
        LoggerUtil.logMethodEntry(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.EXPLICIT_WAIT_TIMEOUT));
        LoggerUtil.logMethodExit(null);
    }

    public void loginAsBankManager() {
        LoggerUtil.logMethodEntry();
        logger.info("Logging in as Bank Manager");
        wait.until(ExpectedConditions.elementToBeClickable(bankManagerLoginBtn)).click();
        LoggerUtil.logMethodExit(null);
    }

    public void loginAsCustomer(String customerName) {
        LoggerUtil.logMethodEntry(customerName);
        logger.info("Logging in as Customer with name: {}",customerName);
        wait.until(ExpectedConditions.elementToBeClickable(customerLoginBtn)).click();
        Select customerSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(customerSelectLogin)));
        customerSelect.selectByVisibleText(customerName);
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        LoggerUtil.logMethodExit(null);
    }
}