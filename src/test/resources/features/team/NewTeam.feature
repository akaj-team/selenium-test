@NewTeamPage
Feature: Check teams page
  I Login as an EM and go to Teams page
  Check validate and button submit when fill in form create new team

  Background: User navigates to Company teams page
    Given I am logged in as a team manager
    And I am stayed in new team page

  Scenario Outline: Check fill name invalid, button Submit is unable and message error is showed
    When I fill in inputName with "<name>" with while space at beginning and end are <whileSpaceBeginning>, <whileSpaceEnd>
    Then Button submit is unable
    And Message error validate name is displayed
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

  Scenario Outline: Check fill email invalid, button submit is unable and message error is showed
    When I fill in inputEmail with "<email>" with while space at beginning and end are "<whileSpaceBeginning>", "<whileSpaceEnd>"
    Then Button submit is unable
    And Message error validate email is displayed
    Examples:
      | email               | whileSpaceBeginning | whileSpaceEnd |
      | email @gmail.com    | 0                   | 0             |
      | email abc@gmail.com | 0                   | 0             |
      | email.@gmail.com    | 0                   | 0             |
      | email@gmail         | 0                   | 0             |
      | email@gmail.com1    | 0                   | 0             |
      | email@gmail. com    | 0                   | 0             |
      | email@gmail.com     | 1                   | 0             |
      | email@gmail.com     | 0                   | 1             |

  Scenario: I fill data is valid, button Submit is enable
    When I fill in inputName with "football group" with while space at beginning and end are 0, 0
    And I open dropdown Manager
    And I select a item in manager list
    And I fill in inputEmail with "viet.phan@asiantech.vn" with while space at beginning and end are "0", "0"
    Then Button submit is enable

  Scenario: Show fail message when click button submit
    Given I fill in inputName with "Android" with while space at beginning and end are 0, 0
    And I open dropdown Manager
    And I select a item in manager list
    And I fill in inputEmail with "viet.phan@asiantech.vn" with while space at beginning and end are "0", "0"
    When I click on button Submit
    Then I should see fail or success message

  Scenario: Open successfully team detail page when click button submit
    Given I fill in inputName with new name
    And I open dropdown Manager
    And I select a item in manager list
    And I fill in inputEmail with new email
    When I click on button Submit
    And I should see fail or success message
