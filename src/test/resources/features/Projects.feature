@Projects
Feature: Check all projects on asiantech

  Background: User navigates to leave planner page
    Given I am logged in as an android team manager
    And I access to projects page

  Scenario: I want to check all project on asiantech
    Then List project should display

  Scenario: I want to check status of portal project
    When I search with "Link & Communication"
    Then Should search with correct key is "Link & Communication"
    And Project name should be "Link & Communication"

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

  Scenario: I want to see list table fields option show
    When I click on table filter item
    Then Table of field should show

  Scenario: I want to display end date of project on table list
    Given Table of field is displayed
    When I click on end date item of table filter item
    Then End date should display as current choose
