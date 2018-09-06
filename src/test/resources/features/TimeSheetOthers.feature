@TimeSheetOthers
Feature: I want to see timesheet of others members
  As a manager I want to tracking status timeSheet of members
  I want to approve or reject members timeSheet

  Background: User navigates to timesheet of others page
    Given I am logged in as an android team manager
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

  Scenario: Check subordinator dropdown
    Then I see dropdown subordinator with "All Subordinator" is default value