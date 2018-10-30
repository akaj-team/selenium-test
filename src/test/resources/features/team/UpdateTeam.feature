@UpdateTeamPage
Feature: Check update team page
  I am logged in as a team manager
  Check validate fill in form update team and status enable of button submit

  Background: User navigates to Company teams page
    Given I am logged in as a team manager
    And I am stayed in update team page at position is 1

  Scenario Outline: I fill to inputName with name invalid, button Submit is unable and message error is showed
    When I fill in inputName with "<name>" with while space at beginning and end are <whileSpaceBeginning>, <whileSpaceEnd>
    Then Button submit is unable
    And Message error is displayed
    Examples:
      | name                                                        | whileSpaceBeginning | whileSpaceEnd |
      | football group football group football group football group | 0                   | 0             |
      | football group !@#$%^&*                                     | 0                   | 0             |
      | football group                                              | 3                   | 0             |
      | football group                                              | 0                   | 5             |

  Scenario: I fill to inputName with name valid, button Submit will enable and message error is hidden
    When I fill in inputName with "football group" with while space at beginning and end are 0, 0
    Then Button submit is enable
    And Message error is hidden

  Scenario: Click button Submit, show fail message
    When I fill in inputName with "Android" with while space at beginning and end are 0, 0
    And I click on button Submit
    Then I should see fail or success message

  Scenario: Click button Submit, redirect to Team Detail page
    When I click on button Submit
    And I should see fail or success message
