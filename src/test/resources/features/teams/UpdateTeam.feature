@TeamsPage
Feature: Login in AT portal
  As a team manager
  I want to check teams page

  Background: User navigates to Company teams page
    Given I am logged in as an "EM"
    And I am stayed in teams page

  Scenario: Update team detail
    When I click on button update team
    Then Page is redirected to Update Team page

  Scenario: I delete input Name, button Submit will unable and message error is showed
    Given I click on button update team
    And Page is redirected to Update Team page
    When I clear text in inputName
    Then Button submit is unable
    When I fill in inputName with "football group"
    Then Button submit is enable
