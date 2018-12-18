@Projects
Feature: Check all projects page

  Background: User navigates to leave planner page
    Given I am logged in as a team manager
    And I access to projects page

  Scenario: I want to see list table fields option show
    When I click on table filter item
    Then Table of field should show

  Scenario: I want to check all project on asiantech
    Then List project should display

  Scenario Outline: I want to search projects
    When I search with "<name>"
    Then Should search with correct key is "<name>"
    And Empty message is displayed
    Examples:
      | name        |
      | abc123!@#   |
      | `123456789` |
      | Not Project |

  Scenario: I want to view project detail via project name
    When I click on project name
    Then should go to correct detail page

  Scenario: I want to view project detail via project image
    When I click on project avatar
    Then should go to correct detail page

  Scenario: I want to view project detail via project code
    When I click on project code
    Then should go to correct detail page

  Scenario: I want to view all status of filter
    When I click status
    Then List status should display

  Scenario: Check current status filter
    When I click on item of filter list
    Then Filter tex should display correct
