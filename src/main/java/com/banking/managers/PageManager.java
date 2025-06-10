package com.banking.managers;

import com.banking.pages.HomePage;
import com.banking.pages.LoginPage;
import com.banking.pages.customer.CustomerPage;
import com.banking.pages.manager.AddCustomerPage;
import com.banking.pages.manager.CustomersPage;
import com.banking.pages.manager.OpenAccountPage;
import org.openqa.selenium.WebDriver;

public class PageManager {
    private final WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private AddCustomerPage addCustomerPage;
    private OpenAccountPage openAccountPage;
    private CustomersPage customersPage;
    private CustomerPage customerPage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public AddCustomerPage getAddCustomerPage() {
        if (addCustomerPage == null) {
            addCustomerPage = new AddCustomerPage(driver);
        }
        return addCustomerPage;
    }

    public OpenAccountPage getOpenAccountPage() {
        if (openAccountPage == null) {
            openAccountPage = new OpenAccountPage(driver);
        }
        return openAccountPage;
    }

    public CustomersPage getCustomersPage() {
        if (customersPage == null) {
            customersPage = new CustomersPage(driver);
        }
        return customersPage;
    }

    public CustomerPage getCustomerPage() {
        if (customerPage == null) {
            customerPage = new CustomerPage(driver);
        }
        return customerPage;
    }

}
