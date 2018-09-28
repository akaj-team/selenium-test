Feature: Check TimeSheet
  I want to login my account and check  My TimeSheet page

  Background: User navigation to My TimeSheet page
    Given I logged in with a employee account
#    Given I am logged in as an android team manager

  Scenario: Login successfully with correct account
    When I click on Timesheet in menu
    Then Menu Timesheet drop down
    When I click on item My Timesheet
    Then My Timesheet page is displayed "/timesheet/submission"
    And Title content is "Sep 24 - Sep 30"
    And Display full record timesheet
    And Disable button this week and can not click
    And Disable button submit and can not click

  Scenario: Can click this week button after click back button
    Given I open my timesheet page
    When Click on back button
    Then Can click this week button

  Scenario: Can click this week button after click next button
    Given I open my timesheet page
    When Click on next button
    Then Can click this week button

  Scenario: Display button add timesheet after hover avatar
    Given I open my timesheet page
    When Move to columns timesheet
    Then Display button add new timesheet

  Scenario: Display dialog create timesheet after click button add timesheet
    Given I open my timesheet page
    When Click on button timesheet
    When Click first button add new timesheet
    Then Display dialog timesheet

  Scenario: Create and submit timesheet
    Given I open my timesheet page
    When Click first button add new timesheet
    Then Display dialog timesheet
    And Display title item project is "PROJECT"
    And Display title dropdown default is "Select project"
    And Display title item task is "TASK"
    And Display title dropdown task default is "Select task"
    And Display text area description default value is "Short description of task..."
    And Display text input time is "8"
    And Display button Repeat every day is disable
    And Display button Save is disable

  Scenario: Create and submit timesheet
    Given I open my timesheet page
    When Click first button add new timesheet
    Then Display dialog timesheet
    When Select first project on list project
    Then Select item dialog project is "Non-Project"
    And Display title dropdown default is "Non-Project"
    And Disable dialog project
    When Select first task on list task
    Then Display dialog task
    And Select item dialog task is "Coding"
    And Display title dropdown task default is "Coding"
    And Display button Repeat every day is enable
    And Display button Save is enable
    When Click button Repeat every day
    Then Display button submit is enable
    And Display Dialog success is enable and show message
