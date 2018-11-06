@TimeSheetOthers
Feature: I want to see timeSheet of others members
  As a manager I want to tracking status timeSheet of members
  I want to approve or reject members timeSheet

  Background: User navigates to timeSheet of others page
    Given I am logged in as a team manager
    Given I move to timeSheet of others page

  Scenario: Title timesheet of others is display
    Then I see label "Timesheet of Others" is show

  Scenario: Button this week is disable
    Then I see button this week is disable

  Scenario: Button pre and next is enable
    Then I see button pre and next is enable

  Scenario: Tab "Line Manager" is default selected
    Then I see tab "Line Manager" is selected

  Scenario: Check status dropdown
    Then I see dropdown status with "All Status" is default value
    When I click on dropdown status
    And I click on "Pending" checkbox
    Then I see checkbox "Pending" is selected
    And I see dropdown status change to "Pending"

  Scenario: Check Subordinator dropdown
    Then I see dropdown Subordinator with "All Subordinator" is default value

  Scenario: Check status button this week
    When I click on pre button
    Then I see button this week is enable

  Scenario: Check status button next
    When I click button next two times
    Then I see button next is disable
    And I see button this week is enable

  Scenario: Check tab status
    When I click on tab "Line Manager"
    Then I see tab "Line Manager" is selected
    When I click on tab "Project"
    Then I see tab "Project" is selected
