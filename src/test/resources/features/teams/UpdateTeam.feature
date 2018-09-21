@UpdateTeamPage
Feature: Login in AT portal
  As a team manager
  I want to check update team page

  Background: User navigates to Company teams page
    Given I am logged in as an "EM"
    And I am stayed in teams page
    And I click on button update team
    And Page is redirected to Update Team page

  Scenario: I fill to inputName with name invalid, button Submit will unable and message error is showed
    When I fill in inputName with "football group sssssssssssssssssssssssssssssssssss most 50 characters"
    Then Button submit is unable
    And Message error is displayed
    When I fill in inputName with "football group !@#$%^&*"
    Then Button submit is unable
    And Message error is displayed
    When I fill in inputName with " football group"
    Then Button submit is unable
    And Message error is displayed
    When I fill in inputName with "football group "
    Then Button submit is unable
    And Message error is displayed
    When I fill in inputName with ""
    Then Button submit is unable
    And Message error is displayed

  Scenario: I fill to inputName with name valid, button Submit will enable and message error is hidden
    When I fill in inputName with "football group"
    Then Button submit is enable
    And Message error is hidden

  Scenario: Open successfully teams page when click on button Team in title
    When I click on button Team in title
    Then Open successfully team page

  Scenario: Click button Submit, show fail message
    When I fill in inputName with "Android"
    And I click on button Submit
    Then I should see fail or success message

  Scenario: Click button Submit, redirect to Team Detail page
    When I click on button Submit
    Then Open successfully team detail page after update
    And I should see fail or success message
