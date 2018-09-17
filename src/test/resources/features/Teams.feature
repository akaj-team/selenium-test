@TeamsPage
Feature: Login in AT portal
  As a team manager
  I want to check teams page

  Background: User navigates to Company teams page
    Given I am stayed in teams page

  Scenario: Check Teams page is started
    When I am stayed in teams page
    Then Page heading is displayed
    And Main content is displayed

  Scenario: Check data in main content
    When I am stayed in teams page
    Then Toolbox search is displayed
    And DataTable is displayed

  Scenario Outline: Input into toolbox search
    When I enter on toolbox search with key is "<search>"
    Examples:
      | search |
      | a      |
      | b      |
      | c      |

  Scenario: Show profile when click on avatar or name of team
    When I click on avatar of team
    Then Team details is displayed
    When I click on name team
    Then Team details is displayed

  Scenario: Show profile when click on username of manager
    When I click on username of manager
    Then User profile is displayed

  Scenario: Add new team
    When I click on button New Team
    Then Page is redirected to New Team page
    And I want to check validate when fill form New Team

  Scenario: Edit team detail
    When I click on button edit team
    Then Page is redirected to Update Team page
    And I want to check data is displayed on this page
    And I want to check validate when update form Update Team
