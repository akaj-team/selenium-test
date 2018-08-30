@MenuPage
Feature: Login AT Portal
  As menu page
  I want to click item on menu
  In order to check status item on menu

  Background: Check menu page
    Given I am logged in as an android team manager

  Scenario:Login success with correct account
    When I should see the welcome message
    Then Show name of account
    And Account name is "Tien Hoang N."
    And Home item with color text is "#ffffff"

  Scenario: Check account color
    When I hover mouse to account name
    Then Account change color to "#bfac8a"

  Scenario: Test click Home
    When I click Home item
    Then Item home change color to "#ffffff"
    And Should redirect to home page "/homepage"

  Scenario: Test click item TimeSheet
    Given Item Timesheet close
    When I click in Timesheet item
    Then Open Child Timesheet item

  Scenario: Test click item My TimeSheet
    Given I click in Timesheet item
    When I click My TimeSheet
    Then Item my TimeSheet change color to "#ffffff"
    And Should redirect to "/timesheet/submission"

  Scenario: Test click TimeSheet Of Other
    Given I click in Timesheet item
    When I click TimeSheet Of Other
    Then Item TimeSheet Of Other change color to "#ffffff"
    And Should redirect to time sheet of other page "/timesheet/approval"

  Scenario: Test item Leave
    Given Item leave close
    When I click item leave
    Then Open child leave item
    And Item leave change color to "#ffffff"

  Scenario: Test click My Leave
    Given I click item leave
    When I click my leave
    Then Item my leave change color to "#ffffff"
    And Should redirect to my leave page "/leave/my-leave"