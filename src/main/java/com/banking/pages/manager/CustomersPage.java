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
import java.util.List;

public class CustomersPage {
    private final WebDriver driver;
    private final Logger logger = LoggerUtil.getLogger();
    private final WebDriverWait wait;

    // By locators
    private final By searchCustomerInput = By.xpath(XPaths.SEARCH_CUSTOMER_INPUT);
    private final By customersTableRow = By.xpath(XPaths.CUSTOMERS_TABLE_ROW);
    private final By postcodeSort = By.xpath(XPaths.POSTCODE_SORT);
    private final By deleteCustomerBtn = By.xpath(XPaths.DELETE_CUSTOMER_BTN);

    public CustomersPage(WebDriver driver) {
        LoggerUtil.logMethodEntry(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.EXPLICIT_WAIT_TIMEOUT));
        LoggerUtil.logMethodExit(null);
    }

    public boolean isCustomersPageDisplayed() {
        LoggerUtil.logMethodEntry();
        boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(searchCustomerInput)).isDisplayed();
        LoggerUtil.logMethodExit(result);
        return result;
    }

    public void searchCustomer(String firstName) {
        LoggerUtil.logMethodEntry(firstName);
        logger.info("Searching for customer: {}", firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchCustomerInput)).sendKeys(firstName);
        LoggerUtil.logMethodExit(null);
    }

    /**
     * Checks whether customer records exist in the table and verifies if their presence matches
     * the expected state (e.g., after search, deletion, or filtering).
     * Uses explicit waits to handle table loading and logs outcomes for debugging.
     *
     * @param shouldBeExist  If `true`, expects customers to be found; if `false`, expects no customers.
     * @return `true` if the actual presence of customers matches `shouldBeExist`, otherwise `false`.
     *         Returns `false` immediately if the table fails to load (TimeoutException).
     */
    public boolean isCustomerFound(boolean shouldBeExist) {
        LoggerUtil.logMethodEntry();
        boolean result;

        try {
            // Wait for rows to be visible
            List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(customersTableRow));
            result = !rows.isEmpty(); // If rows are found, result is true
        } catch (TimeoutException e) {
            // If no rows are found, handle the exception
            logger.info("No rows found in the table.");
            result = false; // No rows means the customer is not found
        }

        LoggerUtil.logMethodExit(result);
        return result == shouldBeExist && result;
    }

    public void sortByPostcode(boolean ascending) {
        LoggerUtil.logMethodEntry(ascending);
        logger.info("Sorting customers by postcode, ascending: {}", ascending);
        wait.until(ExpectedConditions.elementToBeClickable(postcodeSort)).click();

        if (ascending) {
            driver.findElement(postcodeSort).click();
        }
        LoggerUtil.logMethodExit(null);
    }

    /**
     * Validates whether the customer table is sorted by postal code in the specified order
     * (ascending or descending). Assumes postal codes are in the third column (index 2) of the table.
     * An empty table is trivially considered sorted.
     *
     * @param ascending  If `true`, checks for A→Z order; if `false`, checks for Z→A.
     * @return `true` if postcodes are sorted correctly or the table is empty;
     *         `false` if any adjacent postcodes violate the sort order.
     */
    public boolean isSortedByPostcode(boolean ascending) {
        LoggerUtil.logMethodEntry(ascending);
        // Locate all rows that contain data (excluding empty rows)
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(customersTableRow));

        // Handle cases where there are no rows or only the header
        if (rows.isEmpty()) {
            logger.info("Table has {} rows (including header). Returning true.", 0);
            LoggerUtil.logMethodExit(true);
            return true;
        }

        boolean isSorted = true;
        for (int i = 1; i < rows.size(); i++) {
            String prevPostcode = rows.get(i - 1).findElements(By.tagName("td")).get(2).getText().trim().toUpperCase();
            String currPostcode = rows.get(i).findElements(By.tagName("td")).get(2).getText().trim().toUpperCase();

            logger.info("Comparing postal codes: {} and {}", prevPostcode, currPostcode);

            int comparison = prevPostcode.compareTo(currPostcode);
            if ((ascending && comparison > 0) || (!ascending && comparison < 0)) {
                logger.error("Sorting failed at row {}: {} vs {}", i, prevPostcode, currPostcode);
                isSorted = false;
                break;
            }
        }
        LoggerUtil.logMethodExit(isSorted);
        return isSorted;
    }

    public void deleteCustomer() {
        LoggerUtil.logMethodEntry();
        logger.info("Deleting customer");
        wait.until(ExpectedConditions.elementToBeClickable(deleteCustomerBtn)).click();
        LoggerUtil.logMethodExit(null);
    }
}
