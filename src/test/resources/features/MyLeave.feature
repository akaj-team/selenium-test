# new feature
# Tags: optional

Feature: Check my leave
  I want to login my account and check my  plan leave

  Background: User navigation to My leave page
    Given I logged in with a employee account

  Scenario: Login successfully with correct account
    When I click on Leave in menu
    Then Menu Leave drop down
    When I click on item My Leave
    Then My Leave page is displayed
    And Status Menu is "All Status"
    And SYSID is "4703"
    And Type of Leave is "None Paid"
    And Status is "Pending"
    And Date Request is "Sep 05, 2018"
    And Quantity is "1"
    And Approver is "Empty"
    And Manager is "Toan Nguyen T."
    And Annual Leave is "0"
    And Marriage Leave is "0"
    And Overtime Leave is "0"
    And Paternal Leave is "0"

  Scenario: I want to check status Pending
    Given My Leave page is displayed
    When I click on menu Status
    Then Menu status drop down
    When I click on Pending in menu
    Then My Leave page with status Pending is displayed
    And Status Menu is "Pending"
    And SYSID is "4703"
    And Type of Leave is "None Paid"
    And Status is "Pending"
    And Date Request is "Sep 05, 2018"
    And Quantity is "1"
    And Approver is "Empty"
    And Manager is "Toan Nguyen T."
    And Annual Leave is "0"
    And Marriage Leave is "0"
    And Overtime Leave is "0"
    And Paternal Leave is "0"

  Scenario: I want to check status All Status
    Given My Leave page with status Pending displayed
    When I click on menu Status
    Then Menu status drop down
    When I click on All Status in menu
    Then My Leave page is displayed

  Scenario: I want to check status Approved
    Given My Leave page is displayed
    When I click on menu Status
    Then Menu status drop down
    When I click on Approved in menu
    Then My Leave page with status Approved is displayed
    And My Leave page display No records found

  Scenario: I want to check status Rejected
    Given My Leave page is displayed
    When I click on menu Status
    Then Menu status drop down
    When I click on Rejected in menu
    Then My Leave page with status Rejected is displayed
    And My Leave page display No records found

  Scenario: I want to check leave detail
    Given My Leave page is displayed
    When I click on SYSID "4703"
    Then Leave Detail SYSID "4703" page is displayed

  Scenario: I want to check employee detail
    Given My Leave page is displayed
    When I click on a name Manager
    Then Employee Detail page is displayed

  Scenario: I want to click icon search
    Given My Leave page is displayed
    When I click on icon search with SYSID "4703"
    Then Leave Detail SYSID "4703" page is displayed

  Scenario: I want to click button leave request
    Given My Leave page is displayed
    When I click on Leave Request button
    Then Leave Request page is displayed

  Scenario: I want to check tip status
    Given My Leave page is displayed
    When I hover mouse on status
    Then Tip status display is "Pending"
