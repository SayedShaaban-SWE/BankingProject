package com.banking.pages.manager;

import com.banking.constants.AppConstants;
import com.banking.constants.XPaths;
import com.banking.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddCustomerPage {
    private final WebDriver driver;
    private final Logger logger = LoggerUtil.getLogger();
    private final WebDriverWait wait;

    // By locators
    private final By firstNameInput = By.xpath(XPaths.FIRST_NAME_INPUT);
    private final By lastNameInput = By.xpath(XPaths.LAST_NAME_INPUT);
    private final By postCodeInput = By.xpath(XPaths.POST_CODE_INPUT);
    private final By submitBtn = By.xpath(XPaths.SUBMIT_BUTTON);

    public AddCustomerPage(WebDriver driver) {
        LoggerUtil.logMethodEntry(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.EXPLICIT_WAIT_TIMEOUT));
        LoggerUtil.logMethodExit(null);
    }

    public void clickAddCustomerButton() {
        LoggerUtil.logMethodEntry();
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        LoggerUtil.logMethodExit(null);
    }

    public boolean isAddCustomerPageDisplayed() {
        LoggerUtil.logMethodEntry();
        boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).isDisplayed();
        LoggerUtil.logMethodExit(result);
        return result;
    }

    public void addCustomer(String firstName, String lastName, String postCode) {
        LoggerUtil.logMethodEntry(firstName, lastName, postCode);
        logger.info("Adding customer: {} {}", firstName, lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postCodeInput).sendKeys(postCode);
        LoggerUtil.logMethodExit(null);
    }

    public boolean isCustomerAdded() {
        LoggerUtil.logMethodEntry();
        boolean result = driver.switchTo().alert().getText().contains("Customer added successfully");
        driver.switchTo().alert().accept(); // Accept alert
        LoggerUtil.logMethodExit(result);
        return result;
    }
}