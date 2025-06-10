Feature:  Manager Manages Customer
Background:
  Given I login as a bank manager

  @TC1 @Create-Customer @Manager-Operations
  Scenario: As a manager i need to create a new customer with valid details
    When I click on “Add Customer” tab
    Then I should navigated to add new customer page
    When I add a new customer with details from CSV
    And  I click on “Add Customer” submit button
    Then I should see msg “Customer added successfully with customer id: XXX"

  @TC2 @Open-Account @Manager-Operations
  Scenario: As a manager i need to open an account for the new customer added before
    When I click on “Open Account” Tab
    Then I should navigated to open account page
    When I select the customer name as "Harry Potter" and the currency as "Dollar"
    And  I click on “Process” submit button
    Then I should see msg “Account created successfully with account Number :XXX”
    When I login as a bank customer with name "Harry Potter"
    Then I should see the account number is same as the one created on the account creation step

  @TC3 @Search-for-Customers @Manager-Operations
  Scenario: As a manager i need to search on a specific customer by firstName
    When I click on “Customers” tab
    Then I should navigated to customers page
    When I enter the customer first name as "Harry" on the search input field
    Then I should see customer found by his details

  @TC4 @Sort-Customers @Manager-Operations
  Scenario: As a manager i need to sort the customers by PostalCode
    When I click on “Customers” tab
    Then I should navigated to customers page
    When I click on “postcode” column to sort ascending
    Then I should see customers sorted ascending correctly
    When I click on “postcode” column to sort descending
    Then I should see customers sorted descending correctly

  @TC5 @Delete-Customer @Manager-Operations
  Scenario: As a manager i need to delete customer
    When I click on “Customers” tab
    Then I should navigated to customers page
    When I enter the customer first name as "Harry" on the search input field
    Then I should see customer found by his details
    When I click on “Delete” button
    Then I should see the customer deleted successfully



