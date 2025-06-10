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

public class HomePage {
    private final WebDriver driver;
    private final Logger logger = LoggerUtil.getLogger();
    private final WebDriverWait wait;

    // Manager By locators
    private final By addCustomerTab = By.xpath(XPaths.ADD_CUSTOMER_TAB);
    private final By openAccountTab = By.xpath(XPaths.OPEN_ACCOUNT_TAB);
    private final By customersTab = By.xpath(XPaths.CUSTOMERS_TAB);
    // Customer By locators
    private final By depositTab = By.xpath(XPaths.DEPOSIT_TAB);
    private final By withdrawalTab = By.xpath(XPaths.WITHDRAWAL_TAB);
    private final By transactionsTab = By.xpath(XPaths.TRANSACTIONS_TAB);
    private final By accountsList = By.xpath(XPaths.ACCOUNTS_LIST);
    private final By accountNumberDisplayed = By.xpath(XPaths.ACCOUNT_NUMBER_DISPLAYED);

    public HomePage(WebDriver driver) {
        LoggerUtil.logMethodEntry(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.EXPLICIT_WAIT_TIMEOUT));
        LoggerUtil.logMethodExit(null);
    }

    //Manager Tabs
    public void clickAddCustomerTab() {
        LoggerUtil.logMethodEntry();
        logger.info("Clicking on add customer tab");
        wait.until(ExpectedConditions.elementToBeClickable(addCustomerTab)).click();
        LoggerUtil.logMethodExit(null);
    }

    public void clickOpenAccountTab() {
        LoggerUtil.logMethodEntry();
        logger.info("Clicking on open account tab");
        wait.until(ExpectedConditions.elementToBeClickable(openAccountTab)).click();
        LoggerUtil.logMethodExit(null);
    }

    public void clickCustomersTab() {
        LoggerUtil.logMethodEntry();
        logger.info("Clicking on customers tab");
        wait.until(ExpectedConditions.elementToBeClickable(customersTab)).click();
        LoggerUtil.logMethodExit(null);
    }

    //Customer Tabs & Details
    public void clickDepositTab() {
        LoggerUtil.logMethodEntry();
        logger.info("Clicking on deposit tab");
        wait.until(ExpectedConditions.elementToBeClickable(depositTab)).click();
        LoggerUtil.logMethodExit(null);
    }

    public void clickWithdrawalTab() {
        LoggerUtil.logMethodEntry();
        logger.info("Clicking on withdrawal tab");
        wait.until(ExpectedConditions.elementToBeClickable(withdrawalTab)).click();
        LoggerUtil.logMethodExit(null);
    }

    public void clickTransactionsTab() {
        LoggerUtil.logMethodEntry();
        logger.info("Clicking on transactions tab");
        wait.until(ExpectedConditions.elementToBeClickable(transactionsTab)).click();
        LoggerUtil.logMethodExit(null);
    }

    public String getDisplayedAccountNumber(String createdAccountNumber) {
        LoggerUtil.logMethodEntry();
        logger.info("Selecting the account number");
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountsList));
        Select accountsListSelect = new Select(driver.findElement(accountsList));
        accountsListSelect.selectByVisibleText(createdAccountNumber);
        String result = wait.until(ExpectedConditions.visibilityOfElementLocated(accountNumberDisplayed)).getText().trim();
        LoggerUtil.logMethodExit(result);
        return result;
    }
}
