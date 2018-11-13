@AccessControlPage
Feature: I login in AT Portal
  As a team manager
  I want to check views in Access Control page

  Background: User navigates to Access Control page
    Given I am logged in as a team manager
    And I stayed in Access Control page

  Scenario Outline: Check color item in toolbox when I click on any item
    When I click on access control tab item "<position>"
    Then Color of access control tab "<position>" is "<activeColor>"
    And Color other access control tab "<position>" is "<defaultColor>"
    Examples:
      | position | activeColor | defaultColor |
      | 1        | #bfac8a     | #c2c2c2      |
      | 2        | #bfac8a     | #c2c2c2      |
      | 3        | #bfac8a     | #c2c2c2      |
      | 0        | #bfac8a     | #c2c2c2      |

  Scenario Outline: I click on tab Item, check view is displayed in this tab
    Given I click on access control tab item "<position>"
    Then Has "<sum>" drop down is displayed
    And BodyTable is displayed
    And Button Submit is "<enable>"
    Examples:
      | position | sum | enable |
      | 0        | 0   | true   |
      | 1        | 2   | false  |
      | 2        | 2   | false  |
      | 3        | 1   | false  |

  Scenario: I click on tab Normal User, show alert message then clicked on button submit
    Given I click on access control tab item "0"
    When I click on Button Submit
    Then I should see the alert message

  Scenario: I click on tab Team, check isEnable button Submit, show alert message then clicked on button submit
    Given I click on access control tab item "1"
    When I open dropDown Role
    And I click on any role
    When I open DropDown Team
    And I click on any team
    Then Button Submit of access control is enable
    When I click on Button Submit
    Then I should see the alert message

  Scenario: I click on tab Group, check isEnable button Submit, show alert message then clicked on button submit
    Given I click on access control tab item "2"
    When I open dropDown Role
    And I click on any role
    When I open DropDown Group
    And I click on any group
    Then Button Submit of access control is enable
    When I click on Button Submit
    Then I should see the alert message

  Scenario: I click on tab Project, check isEnable button Submit, show alert message then clicked on button submit
    Given I click on access control tab item "3"
    When I open dropDown Role
    And I click on any role
    Then Button Submit of access control is enable
    When I click on Button Submit
    Then I should see the alert message
