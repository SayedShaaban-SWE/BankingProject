Feature: Customer Operations
  Background:
    Given I login as a bank customer with name "Harry Potter"

  @TC6 @Make-Deposit @Search-For-Transaction @Customer-Operations
  Scenario: As a customer i need to do deposit transaction and search about it
    When I click on “Deposit” tab
    Then I should navigated to deposit customer page
    When I enter the deposit amount from CSV
    And  I click on “Deposit” submit button
    Then I should see msg “Deposit Successful“
    When I click on “Transactions” tab
    Then I should navigated to transactions customer page
    When I choose “start Date “& “End Date “to find the transaction added
    Then I should see the transaction amount as "1000" is displayed successfully

  @TC7 @Make-Withdrawal @Customer-Operations
  Scenario: As a customer i need to do withdrawal transaction
    When I click on “Withdrawal” tab
    Then I should navigated to withdrawal customer page
    When I enter the withdrawal with amount "100"
    And  I click on “Withdrawal” submit button
    Then I should see msg “Withdrawal Successful“
