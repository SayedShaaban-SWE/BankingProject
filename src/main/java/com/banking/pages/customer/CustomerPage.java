package com.banking.pages.customer;

import com.banking.constants.AppConstants;
import com.banking.constants.XPaths;
import com.banking.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerPage {
    private final WebDriver driver;
    private final Logger logger = LoggerUtil.getLogger();
    private final WebDriverWait wait;

    // By locators
    private final By amountInput = By.xpath(XPaths.AMOUNT_INPUT);
    private final By submitBtn = By.xpath(XPaths.SUBMIT_BUTTON);
    private final By depositSuccessMsg = By.xpath(XPaths.DEPOSIT_SUCCESS_MSG);
    private final By withdrawalSuccessMsg = By.xpath(XPaths.WITHDRAWAL_SUCCESS_MSG);
    private final By startDate = By.xpath(XPaths.START_DATE);
    private final By endDate = By.xpath(XPaths.END_DATE);
    private final By transactionTableRows = By.xpath(XPaths.TRANSACTION_TABLE_ROWS);



    public CustomerPage(WebDriver driver) {
        LoggerUtil.logMethodEntry(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.EXPLICIT_WAIT_TIMEOUT));
        LoggerUtil.logMethodExit(null);
    }

    public boolean isDepositPageDisplayed() {
        LoggerUtil.logMethodEntry();
        boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput)).isDisplayed();
        LoggerUtil.logMethodExit(result);
        return result;
    }

    public boolean isWithdrawalPageDisplayed() {
        LoggerUtil.logMethodEntry();
        boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput)).isDisplayed();
        LoggerUtil.logMethodExit(result);
        return result;
    }

    public void deposit(String amount) {
        LoggerUtil.logMethodEntry(amount);
        logger.info("Entering deposit amount: {}", amount);
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput)).sendKeys(amount);
        LoggerUtil.logMethodExit(null);
    }

    public void withdrawal(String amount) {
        LoggerUtil.logMethodEntry(amount);
        logger.info("Entering withdrawal amount: {}", amount);
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput)).sendKeys(amount);
        LoggerUtil.logMethodExit(null);
    }

    public String getCurrentDateTime() {
        LoggerUtil.logMethodEntry();

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a");

        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);

        LoggerUtil.logMethodExit(formattedDateTime);
        return formattedDateTime;
    }

    public void clickDepositButton() {
        LoggerUtil.logMethodEntry();
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        LoggerUtil.logMethodExit(null);
    }

    public void clickWithdrawalButton() {
        LoggerUtil.logMethodEntry();
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        LoggerUtil.logMethodExit(null);
    }

    public boolean isDepositSuccessful() {
        LoggerUtil.logMethodEntry();
        boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(depositSuccessMsg)).isDisplayed();
        LoggerUtil.logMethodExit(result);
        return result;
    }

    public boolean isWithdrawalSuccessful() {
        LoggerUtil.logMethodEntry();
        boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(withdrawalSuccessMsg)).isDisplayed();
        LoggerUtil.logMethodExit(result);
        return result;
    }

    public boolean isTransactionsPageDisplayed() {
        LoggerUtil.logMethodEntry();
        boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(startDate)).isDisplayed();
        LoggerUtil.logMethodExit(result);
        return result;
    }

    public void searchTransactions(String start, String end) {
        LoggerUtil.logMethodEntry(start, end);
        logger.info("Searching transactions from {} to {}", start, end);
        wait.until(ExpectedConditions.visibilityOfElementLocated(startDate)).sendKeys(start);
        driver.findElement(endDate).sendKeys(end);
        LoggerUtil.logMethodExit(null);
    }

    public boolean isTransactionDisplayed(String amount) {
        LoggerUtil.logMethodEntry(amount);
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(transactionTableRows));
        boolean found = false;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 2 && cells.get(1).getText().equals(amount)) {
                found = true;
                break;
            }
        }
        LoggerUtil.logMethodExit(found);
        return found;
    }
}
