Feature: Check TimeSheet
  I want to login my account and check  My TimeSheet page

  Background: User navigation to My TimeSheet page
    Given I logged in with a employee account

  Scenario: Login successfully with correct account
    When I click on Timesheet in menu
    Then Menu Timesheet drop down
    When I click on item My Timesheet
    Then My Timesheet page is displayed "/timesheet/submission"
    And Title content is "Oct 29 - Nov 04"
    And Display full record timesheet
    And Disable button this week and can not click
    And Disable button submit and can not click

  Scenario: Can click this week button after click back button
    Given I open my timesheet page
    When Click on back button on timesheet
    Then Can click this week button on timesheet

  Scenario: Can click this week button after click next button
    Given I open my timesheet page
    When Click on next button on timesheet
    Then Can click this week button on timesheet

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

  Scenario: Display dialog timesheet and input value search
    Given I open my timesheet page
    When Click on button timesheet
    When Click first button add new timesheet
    Then Display dialog timesheet
    When Select first task on list task
    Then Display dialog task
    When Input search is "no data"
    Then Display Search result is "No results found"
    When Input search is "Coding"
    Then Display Search result is "Coding"

  Scenario: Create and submit timesheet
    Given I open my timesheet page
    When Click first button add new timesheet
    Then Display dialog timesheet
    When Select first project on list project
    And Select item dialog project is "Non-Project"
    Then Display title dropdown default is "Non-Project"
    When Select first task on list task
    And Select item dialog task is "Coding"
    Then Display title dropdown task default is "Coding"
    And Display button Repeat every day is enable
    And Display button Save is enable
    When Click button Repeat every day
    Then Display button submit is enable
    And Display Dialog success is enable and show message

  Scenario: Display timeSheet data
    Given I open my timesheet page
    Then Display full record timesheet
    And Display element timeSheet
    When Move to title columns timesheet
    Then Display button add new timesheet
    And Display dialog title project
    When Scroll edit timesheet
    Then Change time sheet info is "6"
    And Display Dialog success is enable and show message

  Scenario: Check click delete action control on timeSheet
    Given I open my timesheet page
    Then Display full record timesheet
    And Display element timeSheet
    When Click on columns timesheet
    Then Display dialog timesheet
    And Button delete is enable
    When Click on button delete
    Then Display dialog confirm delete
    And Display title is "Confirmation"
    And Display message is "Are you sure to delete a task?"
    And Display button confirm delete
    And Display button confirm cancel
    When Click button cancel
    Then Dismiss dialog confirm delete
    When Click button confirm delete
    Then Element timeSheet is delete
    And Display Dialog success is enable and show message

    Scenario: Check click button save and submit for timeSheet
      Given I open my timesheet page
      When Click on first item add timesheet
      Then Display dialog timesheet
      When I fill information for timeSheet
      And Click button save on timeSheet dialog
      And Click button submit on timeSheet
      Then Display Dialog success is enable and show message