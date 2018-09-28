@UpdateTeamPage
Feature: Check update team page
  I login as an EM
  Check validate fill in form update team and status enable of button submit

  Background: User navigates to Company teams page
    Given I am logged in as an "EM"
    And I am stayed in update team page at position is 1

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

  Scenario: I fill to inputName with name valid, button Submit will enable and message error is hidden
    When I fill in inputName with "football group"
    Then Button submit is enable
    And Message error is hidden

  Scenario: Click button Submit, show fail message
    When I fill in inputName with "Android"
    And I click on button Submit
    Then I should see fail or success message

  Scenario: Click button Submit, redirect to Team Detail page
    When I click on button Submit
    Then Open successfully team detail page after update
    And I should see fail or success message
