@NewTeamPage
Feature: Check teams page
  I Login as an EM and go to Teams page
  Check validate and button submit when fill in form create new team

  Background: User navigates to Company teams page
    Given I am logged in as an "EM"
    And I am stayed in new team page

  Scenario Outline: I fill to inputName with name invalid, button Submit is unable and message error is showed
    When I fill in inputName with "<name>" with while space at begging and end are <whileSpaceBeginning>, <whileSpaceEnd>
    Then Button submit is unable
    And Message error is displayed
    Examples:
      | name                                                        | whileSpaceBeginning | whileSpaceEnd |
      | football group football group football group football group | 0                   | 0             |
      | football group !@#$%^&*                                     | 0                   | 0             |
      | football group                                              | 10                  | 0             |
      | football group                                              | 0                   | 20            |

  Scenario: I selected a manager but not fill name team, button Submit is unable
    When I open dropdown Manager
    And I select a item in manager list
    Then Button submit is unable

  Scenario: I fill to inputName with name valid and selected manager, button Submit is enable
    When I fill in inputName with "football group" with while space at begging and end are 0, 0
    And I open dropdown Manager
    And I select a item in manager list
    Then Button submit is enable

  Scenario: Show fail message when click button submit
    When I fill in inputName with "Android" with while space at begging and end are 0, 0
    And I open dropdown Manager
    And I select a item in manager list
    When I click on button Submit
    Then I should see fail or success message

  Scenario: Open successfully team detail page when click button submit
    When I fill in inputName with new name
    And I open dropdown Manager
    And I select a item in manager list
    When I click on button Submit
    And I should see fail or success message
