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
    When I click TimeSheet Of Other
    Then Item TimeSheet Of Other change color to "#ffffff"
    And Should redirect to time sheet of other page "/timesheet/approval"

  Scenario: Test item Leave
    Given Item leave close
    When I click item leave
    Then Open child leave item
    And Item leave change color to "#ffffff"

  Scenario: Test click child Leave item
    Given I click item leave
    When I click my leave
    Then Item my leave change color to "#ffffff"
    And Should redirect to my leave page "/leave/my-leave"
    When I click leave planners
    Then Should redirect to leave planners page "/leave/planning"
    When I click leave of others
    Then Should redirect to leave of others page "/leave/tracking"
    When I click leave balance
    Then Should redirect to leave balance page "/leave/balance"

  Scenario: Test item organisation
    Given Item organisation close
    When I click item organisation
    Then Open child organisation item

  Scenario Outline: Test click child item organisation
    Given I click item organisation
    When I click child item organisation with position "<position>"
    Then if count child item is zero should redirect to page "<path>"
    Examples:
      | position | path                         |
      | 0        | /organisation/employees      |
      | 1        | /organisation/teams          |
      | 2        | /organisation/teams/26       |
      | 3        | /organisation/positions      |
      | 4        | /organisation/positions/tree |
      | 5        | /organisation/groups         |

  Scenario Outline: Test click child item project management
    Given I click item project management
    When I click child item project management with position "<position>"
    Then Should redirect to page "<path>"
    Examples:
      | position | path                         |
      | 0        | /project-management/projects |
