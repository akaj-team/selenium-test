@EmployeesPage
Feature: Filter employees with position, type and status at employee page
  Choose position, type or status
  Display an employee list with that standard

  Background: User navigates to employees page
    Given I am logged in as a team manager
    And Display employees page

  Scenario: Filter employees with position and having a employee list
    Given Click on position view
    When Select any item in the position list
    Then Display a corresponding employee list

  Scenario: Filter employees with employee type
    Given Click on employee type view
    When Select any item in that type list
    Then Employee type is chose
    Then Display a corresponding employee list

  Scenario: Filter employees with status
    Given Click on status view
    When Select any item in that status list
    Then A status item is chose
    Then Display a corresponding employee list
