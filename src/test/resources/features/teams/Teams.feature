@TeamsPage
Feature: Login in AT portal
  As a team manager
  I want to check teams page

  Background: User navigates to Company teams page
    Given I am logged in as an "EM"
    And I am stayed in teams page

  Scenario Outline: Search teams with enter name team and result is list team
    When Enter toolbox search with name is "<search>"
    Then I should see list team
    Examples:
      | search  |
      | Android |
      | BA      |
      | IOS     |


  Scenario Outline: Search teams with enter name team and result is empty team
    When Enter toolbox search with name is "<search>"
    Then I should see list team is empty
    And I should see message "No records found"
    Examples:
      | search |
      | aaa    |
      | bbb    |
      | ccc    |

  Scenario: Open successfully profile when click on avatar of team
    When I click on avatar of team
    Then Team details is displayed

  Scenario: Open successfully profile when click on name of team
    When I click on name team
    Then Team details is displayed

  Scenario: Open successfully profile when click on username of manager
    When I click on username of manager
    Then Manager profile is displayed
