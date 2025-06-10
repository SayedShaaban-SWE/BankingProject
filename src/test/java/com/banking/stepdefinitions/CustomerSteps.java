package com.banking.stepdefinitions;

import com.banking.managers.ConfigManager;
import com.banking.managers.DriverManager;
import com.banking.managers.PageManager;
import com.banking.utils.FilesReader;
import com.banking.utils.LoggerUtil;
import com.banking.utils.WaitHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.List;

import static com.banking.stepdefinitions.Hooks.*;

public class CustomerSteps{
    private final PageManager pageManager;
    private String transactionDateTime;

    public CustomerSteps() {
        LoggerUtil.logMethodEntry();
        this.pageManager = new PageManager(driver);
        LoggerUtil.logMethodExit(null);
    }

    @Given("I login as a bank customer with name {string}")
    @Step("Logging in as a bank customer with customer full name")
    public void iLoginAsBankCustomer(String customerFullName) {
        LoggerUtil.logMethodEntry();
        DriverManager.getDriver().get(ConfigManager.getProperty("bank_url"));
        pageManager.getLoginPage().loginAsCustomer(customerFullName);
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “Deposit” tab")
    @Step("Clicking Deposit tab")
    public void iClickOnDepositTab() {
        LoggerUtil.logMethodEntry();
        pageManager.getHomePage().clickDepositTab();
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “Withdrawal” tab")
    @Step("Clicking Withdrawal tab")
    public void iClickOnWithdrawalTab() {
        LoggerUtil.logMethodEntry();
        pageManager.getHomePage().clickWithdrawalTab();
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “Deposit” submit button")
    @Step("Clicking Deposit submit button")
    public void iClickOnDepositButton() {
        LoggerUtil.logMethodEntry();
        pageManager.getCustomerPage().clickDepositButton();
        transactionDateTime=pageManager.getCustomerPage().getCurrentDateTime();
        logger.info("transactionDateTime is: {}", transactionDateTime);
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “Withdrawal” submit button")
    @Step("Clicking Withdrawal submit button")
    public void iClickOnWithdrawalButton() {
        LoggerUtil.logMethodEntry();
        pageManager.getCustomerPage().clickWithdrawalButton();
        transactionDateTime=pageManager.getCustomerPage().getCurrentDateTime();
        logger.info("transactionDateTime is : {}", transactionDateTime);
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should navigated to deposit customer page")
    @Step("Verifying navigation to deposit page")
    public void iShouldNavigatedToDepositCustomerPage() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getCustomerPage().isDepositPageDisplayed(), "Deposit page not displayed");
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should navigated to withdrawal customer page")
    @Step("Verifying navigation to withdrawal page")
    public void iShouldNavigatedToWithdrawalCustomerPage() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getCustomerPage().isWithdrawalPageDisplayed(), "Withdrawal page not displayed");
        LoggerUtil.logMethodExit(null);
    }

    @When("I enter the deposit amount from CSV")
    @Step("Entering deposit amounts from CSV")
    public void iEnterTheDepositAmountFromCsv() {
        LoggerUtil.logMethodEntry();
        List<String> amounts = FilesReader.readTransactionsData("src/main/resources/testData/transactions-amounts-data.csv");
        for (String amount : amounts) {
            logger.info("Depositing amount: {}", amount);
            pageManager.getCustomerPage().deposit(amount);
        }
        LoggerUtil.logMethodExit(null);
    }

    @When("I enter the withdrawal with amount {string}")
    @Step("Entering withdrawal with amount {string}")
    public void iEnterTheWithdrawalAmountFromCsv(String amount) {
        LoggerUtil.logMethodEntry();
        logger.info("Withdrawal amount: {}", amount);
        pageManager.getCustomerPage().withdrawal(amount);
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should see msg “Deposit Successful“")
    @Step("Verifying deposit success")
    public void iShouldSeeMsgDepositSuccessful() {
        LoggerUtil.logMethodEntry();
        WaitHelper.waitFor(5);
        Assert.assertTrue(pageManager.getCustomerPage().isDepositSuccessful(), "Deposit was not successful");
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should see msg “Withdrawal Successful“")
    @Step("Verifying Withdrawal success")
    public void iShouldSeeMsgWithdrawalSuccessful() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getCustomerPage().isWithdrawalSuccessful(), "Withdrawal was not successful");
        LoggerUtil.logMethodExit(null);
    }

    @When("I click on “Transactions” tab")
    @Step("Clicking Transactions tab")
    public void iClickOnTransactionsTab() {
        LoggerUtil.logMethodEntry();
        pageManager.getHomePage().clickTransactionsTab();
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should navigated to transactions customer page")
    @Step("Verifying navigation to transactions page")
    public void iShouldNavigatedToTransactionsCustomerPage() {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getCustomerPage().isTransactionsPageDisplayed(), "Transactions page not displayed");
        LoggerUtil.logMethodExit(null);
    }

    @When("I choose “start Date “& “End Date “to find the transaction added")
    @Step("Searching transactions by date range")
    public void iChooseStartDateAndEndDate() {
        pageManager.getCustomerPage().searchTransactions(transactionDateTime, pageManager.getCustomerPage().getCurrentDateTime());
        System.out.println("current dateTime is: " + pageManager.getCustomerPage().getCurrentDateTime());
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should see the transaction amount as {string} is displayed successfully")
    @Step("Verifying transaction display")
    public void iShouldSeeTheTransactionIsDisplayedSuccessfully(String amount) {
        LoggerUtil.logMethodEntry();
        Assert.assertTrue(pageManager.getCustomerPage().isTransactionDisplayed(amount), "Transaction not displayed");
        LoggerUtil.logMethodExit(null);
    }

    @Then("I should see the account number is same as the one created on the account creation step")
    @Step("Verifying account number")
    public void iShouldSeeTheAccountNumberIsSame() {
        LoggerUtil.logMethodEntry();
        String actualAccountNumber = pageManager.getOpenAccountPage().getTheCreatedAccountNumber();
        String displayedAccountNumber = pageManager.getHomePage().getDisplayedAccountNumber(actualAccountNumber);

        logger.info("The created account number is: {} and The actual account number is: {}", actualAccountNumber, displayedAccountNumber);

        Assert.assertEquals(displayedAccountNumber, actualAccountNumber, "Account numbers do not match");
        LoggerUtil.logMethodExit(null);
    }
}