package com.banking.stepdefinitions;

import com.banking.managers.ConfigManager;
import com.banking.managers.DriverManager;
import com.banking.managers.PageManager;
import com.banking.utils.FilesReader;
import com.banking.utils.LoggerUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;

import static com.banking.stepdefinitions.Hooks.*;

public class ManagerSteps{
    private final PageManager pageManager;

    public ManagerSteps() {
        LoggerUtil.logMethodEntry();
        this.pageManager = new PageManager(driver);
        LoggerUtil.logMethodExit(null);
    }

    @Given("I login as a bank manager")
    @Step("Logging in as Bank Manager")
    public void iAmLoggedInAsBankManager() {
        LoggerUtil.logMethodEntry();
        DriverManager.getDriver().get(ConfigManager.getProperty("bank_url"));
        pageManager.getLoginPage().loginAsBankManager();
        LoggerUtil.logMethodExit(null);
    }

    @Step("Adding customer: {0} {1}, PostCode: {2}")
    public void iAddANewCustomer(String firstName, String lastName, String postCode) {
        LoggerUtil.logMethodEntry(firstName, lastName, postCode);
        pageManager.getAddCustomerPage().addCustomer(firstName, lastName, postCode);
        LoggerUtil.logMethodExit(null);
    }

    @When("I add a new customer with details from CSV")
    @Step("Adding customers from CSV")
    public void iAddNewCustomerFromCsv() {
        LoggerUtil.logMethodEntry();
        List<String[]> customers = FilesReader.readCustomerData("src/main/resources/testData/customers-data.csv");
        for (String[] customer : customers) {
            String firstName = customer[0];
            String lastName = customer[1];
            String postCode = customer[2];
            logger.info("Adding customer: {} {}, PostCode: {}", firstName, lastName, postCode);
            iAddANewCustomer(firstName, lastName, postCode);
        }
        LoggerUtil.logMethodExit(null);
    }

    @Then("the customer should be added successfully")
    @Step("Verifying customer addition")
    public void theCustomerShouldBeAddedSuccessfully() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getAddCustomerPage().isCustomerAdded(), "Customer was not added successfully");
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “Add Customer” submit button")
    @Step("Clicking Add Customer submit button")
    public void iClickOnAddCustomerButton() {
        LoggerUtil.logMethodEntry();
        pageManager.getAddCustomerPage().clickAddCustomerButton();
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should navigated to add new customer page")
    @Step("Verifying navigation to Add Customer page")
    public void iShouldNavigatedToAddNewCustomerPage() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getAddCustomerPage().isAddCustomerPageDisplayed(), "Add Customer page not displayed");
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should see msg “Customer added successfully with customer id: XXX\"")
    @Step("Verifying customer addition with message")
    public void iShouldSeeMsgCustomerAddedSuccessfully() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getAddCustomerPage().isCustomerAdded(), "Customer was not added successfully");
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “Open Account” Tab")
    @Step("Clicking Open Account Tab")
    public void iClickOnOpenAccountTab() {
        LoggerUtil.logMethodEntry();
        pageManager.getHomePage().clickOpenAccountTab();
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should navigated to open account page")
    @Step("Verifying navigation to Open Account page")
    public void iShouldNavigatedToOpenAccountPage() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getOpenAccountPage().isOpenAccountPageDisplayed(), "Open Account page not displayed");
        LoggerUtil.logMethodExit(null);
    }

    @When("I select the customer name as {string} and the currency as {string}")
    @Step("Selecting customer and currency")
    public void iSelectTheCustomerNameAndTheCurrency(String customerName, String currency) {
        LoggerUtil.logMethodEntry();
        pageManager.getOpenAccountPage().selectAccountDetails(customerName, currency);
        LoggerUtil.logMethodExit(null);
    }

    @And("I click on “Process” submit button")
    @Step("Clicking Process submit button")
    public void iClickOnProcessButton() {
        LoggerUtil.logMethodEntry();
        pageManager.getOpenAccountPage().clickProcessButton();
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should see msg “Account created successfully with account Number :XXX”")
    @Step("Verifying account creation")
    public void iShouldSeeMsgAccountCreatedSuccessfully() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getOpenAccountPage().isNewAccountSuccessMsgDisplayed(), "The Account is not added");
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “Customers” tab")
    @Step("Clicking Customers tab")
    public void iClickOnCustomersTab() {
        LoggerUtil.logMethodEntry();
        pageManager.getHomePage().clickCustomersTab();
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should navigated to customers page")
    @Step("Verifying navigation to Customers page")
    public void iShouldNavigatedToCustomersPage() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getCustomersPage().isCustomersPageDisplayed(), "Customers page not displayed");
        LoggerUtil.logMethodExit(null);
    }

    @When("I enter the customer first name as {string} on the search input field")
    @Step("Searching customer by first name")
    public void iEnterTheCustomerFirstName(String customerFirstName) {
        LoggerUtil.logMethodEntry();
        pageManager.getCustomersPage().searchCustomer(customerFirstName);
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should see customer found by his details")
    @Step("Verifying customer search")
    public void iShouldSeeCustomerFound() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getCustomersPage().isCustomerFound(true), "Customer not found in search");
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “postcode” column to sort ascending")
    @Step("Sorting by postcode ascending")
    public void iClickOnPostcodeColumnToSortAscending() {
        LoggerUtil.logMethodEntry();
        pageManager.getCustomersPage().sortByPostcode(true);
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should see customers sorted ascending correctly")
    @Step("Verifying ascending sort")
    public void iShouldSeeCustomersSortedAscendingCorrectly() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getCustomersPage().isSortedByPostcode(true), "Customers not sorted ascending by postcode");
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “postcode” column to sort descending")
    @Step("Sorting by postcode descending")
    public void iClickOnPostcodeColumnToSortDescending() {
        LoggerUtil.logMethodEntry();
        pageManager.getCustomersPage().sortByPostcode(false);
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should see customers sorted descending correctly")
    @Step("Verifying descending sort")
    public void iShouldSeeCustomersSortedDescendingCorrectly() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getCustomersPage().isSortedByPostcode(false), "Customers not sorted descending by postcode");
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “Delete” button")
    @Step("Clicking Delete button")
    public void iClickOnDeleteButton() {
        LoggerUtil.logMethodEntry();
        pageManager.getCustomersPage().deleteCustomer();
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should see the customer deleted successfully")
    @Step("Verifying customer deletion")
    public void iShouldSeeTheCustomerDeletedSuccessfully() {
        LoggerUtil.logMethodEntry();
        Assert.assertFalse(pageManager.getCustomersPage().isCustomerFound(false), "Customer still present after deletion");
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “Add Customer” tab")
    public void iClickOnAddCustomerTab() {
        LoggerUtil.logMethodEntry();
        pageManager.getHomePage().clickAddCustomerTab();
        LoggerUtil.logMethodExit(null);
    }
}