@LeaveBalance
Feature: Check leave balance of employee

  Background: User navigates to leave planner page
    Given I am logged in as an android team manager
    And Display leave balance page

#  Scenario: I want to show all team filter
#    When I click on all team filter
#    Then List filter should display
#
#  Scenario: I want to check leave balance of this year
#    Then Year on this page should be "2018"
#
#  Scenario: I want to check leave balance of last year
#    When I click on previous
#    Then Year on this page should be "2017"
#
#  Scenario: I want to check first page of list balance leave
#    Then List balance leave should be 50 item
#
#  Scenario: I want to view profile via avatar of an employee on leave list
#    When I click to avatar of an employee
#    Then Should open correct profile page of that employee

#  Scenario: I want to view profile via name of an employee on leave list
#    When I click to name of an employee
#    Then Should open correct profile page of that employee
#
#  Scenario: I want to view leave history of an employee via search icon
#    Given I click to search icon of an employee on leave list
#    Then Should open correct history page of that employee

  Scenario: I want to view leave history of an employee via sysid
    Given I click to sysId of an employee on leave list
    Then Should open correct history page of that employee

#  Scenario: I want to view all leave  balance of an employee with name
#    Given I fill in search view with name is "Vinh Huynh L.B."
#    Then Should show list result of employ with name is "Vinh Huynh L.B."
#
#  Scenario: I want to show all leave balance of android team
#    Given I click on all team filter
#    Given List filter should display
#    When I select item "Android"
#    Then Team will display "Android"
#    And Show list result of employ on Android team
#
#  Scenario: I want to check second page of list balance leave
#    When I click on page number 2
#    Then Should load data at page 2
#    And List balance leave should be 50 item