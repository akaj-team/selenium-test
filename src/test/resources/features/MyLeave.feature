# new feature
# Tags: optional

Feature: Check my leave
  I want to login my account and check my  plan leave

  Background: User navigation to My leave page
    Given I am logged in as a team manager
    And My Leave page is displayed

  Scenario: I want to check status Pending
    When I click on menu Status
    Then Menu status drop down
    When I click on Pending in menu
    Then My Leave page with status Pending is displayed

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
