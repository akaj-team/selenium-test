@TimeSheetOthers
Feature: I want to see timeSheet of others members
  As a manager I want to tracking status timeSheet of members
  I want to approve or reject members timeSheet

  Background: User navigates to timeSheet of others page
    Given I am logged in as a team manager
    And I move to timeSheet of others page

  Scenario: Check views are display when start page
    Then I see label "Timesheet of Others" is show
    And I see button this week is disable
    And I see button pre and next is enable
    And I see tab "Line Manager" is selected
    And I see dropdown Subordinator with "All Subordinator" is default value
    And I see dropdown status with "All Status" is default value
    And I see list label status on bottom is show

  Scenario: Check status dropdown
    When I click on dropdown status
    And I choose option "Pending" in Status
    And I see dropdown status change to "Pending"

  Scenario: Check Subordinator dropdown
    When I click on dropdown Subordinator
    And I choose option "Member(s) of Android Team" in Subordinator
    And I see dropdown Subordinator change to "Android"

  Scenario: Check status button this week
    When I click on pre button
    Then I see button this week is enable

  Scenario: Check status button next
    When I click button next 2 times
    Then I see button next is disable
    And I see button this week is enable

  Scenario: Check tab status
    When I click on tab "Project"
    Then I see tab "Project" is selected
    When I click on tab "Line Manager"
    Then I see tab "Line Manager" is selected

  Scenario: Open successfully Employee detail page when click on information user
    When I click on information user in 2
    Then I redirected to employee detail page

  Scenario: Open popup when click on any has task of employee
    When I click on any has task of employee with position is 1
    Then I see popup is displayed
