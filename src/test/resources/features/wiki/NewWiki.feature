@NewWikiPage
Feature: Check new page

  Background: User navigates to new page
    Given I am logged in as a team manager
    And Child Page page displayed

  Scenario: Check submit is enabled
    When I enter title "Pikalong"
    And I enter content
    Then Submit button is enabled

  Scenario: Check create a child page
    Given Enter title and content
    When Click submit child page or update page button
    Then Redirect wiki detail page

  Scenario: Check submit is disable when only enter title
    When I enter title "Pikalong"
    Then Submit button is disabled

  Scenario: Check submit is disable when only enter content
    When I enter content
    Then Submit button is disabled

  Scenario: Check show message "Please enter value"
    When I clear title
    Then Show message "Please enter value"
    When I enter title " Pikalong"
    Then Show message "Please enter value"
    And Submit button is disabled

  Scenario: Check dialog confirm
    Given I enter title "Pikalong"
    When I click wiki item on menu
    Then Display dialog confirm
    When Click button close dialog
    Then Dismiss dialog confirm
    Given I click wiki item on menu
    When Click button stay on dialog
    Then Dismiss dialog confirm and save data
    Given I click wiki item on menu
    When Click button leave on dialog
    Then Redirect wiki detail page
