@NewTeamPage
Feature: Login in AT portal
  As a team manager
  I want to check teams page

  Background: User navigates to Company teams page
    Given I am logged in as an "EM"
    And I am stayed in teams page
    And I click on button New Team
    And Page is redirected to New Team page

  Scenario: Open successfully teams page when click on button Team in title
    When I click on button Team in title
    Then Open successfully team page

  Scenario: I fill to inputName with name invalid, button Submit is unable and message error is showed
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

  Scenario: I selected a manager but not fill name team, button Submit is unable
    When I open dropdown Manager
    And I select a item in manager list
    Then Button submit is unable

  Scenario: I fill to inputName with name valid and selected manager, button Submit is enable
    When I fill in inputName with "football group"
    And I open dropdown Manager
    And I select a item in manager list
    Then Button submit is enable

  Scenario: Click button submit when fill name and selected manager
    When I fill in inputName with "football group 2"
    And I open dropdown Manager
    And I select a item in manager list
    When I click on button Submit
    Then Open successfully team detail page
    And I should see the created team success message
