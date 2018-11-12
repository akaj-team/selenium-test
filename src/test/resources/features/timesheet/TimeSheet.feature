@TimeSheet
Feature: Check TimeSheet
  I want to login my account and check  My TimeSheet page

  Background: User navigation to My TimeSheet page
    Given I am logged in as a team manager
    And Display time sheet page

  Scenario: Login successfully with correct account
    Then My Timesheet page is displayed "/timesheet/submission"
    And Display title timesheet content
    And Display full record timesheet
    And Disable button this week and can not click
    And Disable button submit and can not click

  Scenario: Can click this week button after click back button
    When Click on back button on timesheet
    Then Can click this week button on timesheet

  Scenario: Can click this week button after click next button
    When Click on next button on timesheet
    Then Can click this week button on timesheet

  Scenario: Display button add timesheet after hover avatar
    When Move to columns timesheet
    Then Display button add new timesheet

  Scenario: Display dialog create timesheet after click button add timesheet
    When Click on button timesheet
    When Click first button add new timesheet
    Then Display dialog timesheet

  Scenario: Create and submit timesheet
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
    And Display Dialog success is enable and show message timesheet

  Scenario: Display timeSheet data
    Then Display full record timesheet
    And Display element timeSheet
    When Move to title columns timesheet
    Then Display button add new timesheet
    And Display dialog title project timesheet
    When Scroll edit timesheet
    Then Change time sheet info is "6"
    And Display Dialog success is enable and show message timesheet

  Scenario: Check click delete action control on timeSheet
    Then Display full record timesheet
    And Display element timeSheet
    When Click on columns timesheet
    Then Display dialog timesheet
    And Button delete timesheet is enable
    When Click on button delete timesheet
    Then Display dialog confirm delete timesheet
    And Display title timesheet is "Confirmation"
    And Display message timesheet is "Are you sure to delete a task?"
    And Display button confirm delete timesheet
    And Display button confirm cancel timesheet
    When Click button cancel timesheet
    Then Dismiss dialog confirm delete timesheet
    When Click button confirm delete timesheet
    Then Element timeSheet is delete timesheet
    And Display Dialog success is enable and show message timesheet

    Scenario: Check click button save and submit for timeSheet
      When Click on first item add timesheet
      Then Display dialog timesheet
      When I fill information for timeSheet
      And Click button save on timeSheet dialog
#      And Click button submit on timeSheet
#      Then Display Dialog success is enable and show message
