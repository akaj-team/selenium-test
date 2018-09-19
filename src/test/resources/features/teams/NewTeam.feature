@TeamsPage
Feature: Login in AT portal
  As a team manager
  I want to check teams page

  Background: User navigates to Company teams page
    Given I am logged in as an "EM"
    And I am stayed in teams page

  Scenario: Add new team
    When I click on button New Team
    Then Page is redirected to New Team page

  Scenario: Validate data input to enable button submit
    Given I click on button New Team
    And Page is redirected to New Team page
    When I fill in Name with "<name>"
    And I selected Manager
    Then Button submit is enable

  Scenario: Validate data input to unable button submit
    Given I click on button New Team
    And Page is redirected to New Team page
    When I not fill in Name
    And I selected Manager
    Then Button submit is unable

  Scenario: Validate data input to unable button submit
    Given I click on button New Team
    And Page is redirected to New Team page
    When I fill in Name with "<name>"
    And I unselected Manager
    Then Button submit is unable