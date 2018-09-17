@LeaveBalance
Feature: Check leave balance of employee

  Background: User navigates to leave planner page
    Given I am logged in as an android team manager
    And Display leave balance page

  Scenario: I want to show all team filter
    Given I click on all team filter
    Then List filter should display

  Scenario: I want to check first page of list balance leave
    Then List balance leave should be 50 item