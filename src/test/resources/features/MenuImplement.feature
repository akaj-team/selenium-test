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

  Scenario: Login success with correct account
    When I hover mouse to account name
    Then Account change color to "#bfac8a"

  Scenario:Login success with correct account
    Given Item Timesheet close
    When I click in Timesheet item
    Then Open Child Timesheet item