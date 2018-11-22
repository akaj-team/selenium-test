@NewPage
Feature: Check wiki page

  Background: User navigates to profile page
    Given I am logged in as a team manager
    And Child Page page displayed

  Scenario: Check submit is enabled
    When I enter title
    And I enter content
    Then Submit button is enabled

  Scenario: Check create a child page
    Given Enter title and content
    When Click submit child page button
    Then Redirect wiki detail page

  Scenario: Check submit is disable when only enter title
    When I enter title
    Then Submit button is disabled

  Scenario: Check submit is disable when only enter content
    When I enter content
    Then Submit button is disabled

  Scenario: Check show message "Please enter value"
    When I clear title
    Then Show message "Please enter value"
    And Submit button is disabled
