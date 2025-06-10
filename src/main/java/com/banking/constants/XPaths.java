package com.banking.constants;

import org.openqa.selenium.By;

public class XPaths {
    public static final String BANK_MANAGER_LOGIN_BTN = "//button[contains(text(),'Bank Manager Login')]";
    public static final String ADD_CUSTOMER_TAB = "//button[contains(text(),'Add Customer')]";
    public static final String FIRST_NAME_INPUT = "//input[@placeholder='First Name']";
    public static final String LAST_NAME_INPUT = "//input[@placeholder='Last Name']";
    public static final String POST_CODE_INPUT = "//input[@placeholder='Post Code']";
    public static final String SUBMIT_BUTTON = "//button[@type='submit']";
    public static final String OPEN_ACCOUNT_TAB = "//button[contains(text(),'Open Account')]";
    public static final String CUSTOMER_DROPDOWN = "//select[@id='userSelect']";
    public static final String CURRENCY_DROPDOWN = "//select[@id='currency']";
    public static final String ACCOUNTS_LIST = "//select[@id='accountSelect']";
    public static final String CUSTOMERS_TAB = "//button[contains(text(),'Customers')]";
    public static final String SEARCH_CUSTOMER_INPUT = "//input[@placeholder='Search Customer']";
    public static final String CUSTOMERS_TABLE_ROW = "//tbody/tr[contains(@class, 'ng-scope')]";
    public static final String POSTCODE_SORT = "//a[contains(text(),'Post Code')]";
    public static final String DELETE_CUSTOMER_BTN = "//button[contains(text(),'Delete')]";
    public static final String CUSTOMER_LOGIN_BTN = "//button[contains(text(),'Customer Login')]";
    public static final String DEPOSIT_TAB = "//button[contains(text(),'Deposit')]";
    public static final String WITHDRAWAL_TAB = "//button[contains(text(),'Withdrawl')]";
    public static final String AMOUNT_INPUT = "//input[@type='number']";
    public static final String TRANSACTIONS_TAB = "//button[contains(text(),'Transactions')]";
    public static final String START_DATE = "//input[@id='start']";
    public static final String END_DATE = "//input[@id='end']";
    public static final String DEPOSIT_SUCCESS_MSG = "//span[contains(text(),'Deposit Successful')]";
    public static final String WITHDRAWAL_SUCCESS_MSG = "//span[contains(text(),'Transaction successful')]";
    public static final String CUSTOMER_SELECT_LOGIN = "//select[@id='userSelect']";
    public static final String LOGIN_BTN = "//button[contains(text(),'Login')]";
    public static final String ACCOUNT_NUMBER_DISPLAYED = "//div[@ng-hide='noAccount']//strong[1]";
    public static final String TRANSACTION_TABLE_ROWS = "//table[@class='table table-bordered table-striped']//tr[td]";
}