Feature: Check update page

  Background: User navigates to update page
    Given I am logged in as a team manager
    And Update Page page displayed

  Scenario: Check submit is enabled
    Then Submit button is enabled

  Scenario: Check update a child page
    When I clear title
    Then I enter title "Pikalong"
    When Click submit child page or update page button
    Then Redirect wiki detail page

  Scenario: Check show message "Please enter value"
    When I clear title
    Then Show message "Please enter value"
    And Submit button is disabled

  Scenario: Check select parent
    When I click on menu parent
    Then Menu is drop down
    When I click on item parent
    Then Change parent
