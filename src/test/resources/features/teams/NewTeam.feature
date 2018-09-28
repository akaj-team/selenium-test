@NewTeamPage
Feature: Check teams page
  I Login as an EM and go to Teams page
  Check validate and button submit when fill in form create new team

  Background: User navigates to Company teams page
    Given I am logged in as an "EM"
    And I am stayed in new team page

  Scenario Outline: I fill to inputName with name invalid, button Submit is unable and message error is showed
    When I fill in inputName with "<searchData>" as type valid is "<typeValid>"
    Then Button submit is unable
    And Message error is displayed
    Examples:
      | searchData                                                  | typeValid                |
      | football group football group football group football group | moreThan50Characters     |
      | football group !@#$%^&*                                     | specialCharacters        |
      | football group                                              | whiteSpaceAtTheBeginning |
      | football group                                              | whiteSpaceAtTheEnd       |

  Scenario: I selected a manager but not fill name team, button Submit is unable
    When I open dropdown Manager
    And I select a item in manager list
    Then Button submit is unable

  Scenario: I fill to inputName with name valid and selected manager, button Submit is enable
    When I fill in inputName with "football group"
    And I open dropdown Manager
    And I select a item in manager list
    Then Button submit is enable

  Scenario: Show fail message when click button submit
    When I fill in inputName with "Android"
    And I open dropdown Manager
    And I select a item in manager list
    When I click on button Submit
    Then I should see fail or success message

  Scenario: Open successfully team detail page when click button submit
    When I fill in inputName with new name
    And I open dropdown Manager
    And I select a item in manager list
    When I click on button Submit
    Then Open successfully team detail page after created
    And I should see fail or success message
