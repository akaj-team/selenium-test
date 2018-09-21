# new feature
# Tags: optional

Feature: Check my leave
  I want to login my account and check my  plan leave

  Background: User navigation to My leave page
    Given I logged in with a employee account
    And My Leave page is displayed

  Scenario: Login successfully with correct account
    And Status Menu is "All Status"
    And Manager is "Toan Nguyen T."
    And Status is "Pending"
    And Annual Leave is "0"
    And Marriage Leave is "0"
    And Overtime Leave is "0"
    And Paternal Leave is "0"

  Scenario: I want to check status Pending
    When I click on menu Status
    Then Menu status drop down
    When I click on Pending in menu
    Then My Leave page with status Pending is displayed
    And Status Menu is "Pending"
    And Manager is "Toan Nguyen T."
    And Status is "Pending"
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
    When I click on menu Status
    Then Menu status drop down
    When I click on Approved in menu
    Then My Leave page with status Approved is displayed
    And My Leave page display No records found

  Scenario: I want to check status Rejected
    When I click on menu Status
    Then Menu status drop down
    When I click on Rejected in menu
    Then My Leave page with status Rejected is displayed
    And My Leave page display No records found

  Scenario: I want to check leave detail
    When I click an SYSID
    Then Leave Detail page is displayed

  Scenario: I want to check employee detail
    When I click on a name Manager
    Then Employee Detail page is displayed

  Scenario: I want to click icon search
    When I click on an icon search
    Then Leave Detail page is displayed

  Scenario: I want to click button leave request
    When I click on Leave Request button
    Then Leave Request page is displayed

  Scenario: I want to check tip status
    When I hover mouse on status
    Then Tip status display is "Pending"
