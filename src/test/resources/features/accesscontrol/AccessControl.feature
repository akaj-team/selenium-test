@AccessControlPage
Feature: I login in AT Portal
  As a team manager
  I want to check views in Access Control page

  Background: User navigates to Access Control page
    Given I am logged in as an "EM"
    And I stayed in Access Control page

  Scenario Outline: Check color item in toolbox when I click on any item
    When I click on tab item "<position>"
    Then Color of tab "<position>" is "<activeColor>"
    And Color other tab "<position>" is "<defaultColor>"
    Examples:
      | position | activeColor | defaultColor |
      | 1        | #bfac8a     | #c2c2c2      |
      | 2        | #bfac8a     | #c2c2c2      |
      | 3        | #bfac8a     | #c2c2c2      |
      | 0        | #bfac8a     | #c2c2c2      |

  Scenario: Check view is displayed of Normal User when I click on this item
    When I click on tab item "0"
    Then Table control is displayed
    And Button Submit is enable

  Scenario: Check view is displayed of Team when I click on this item
    When I click on tab item "1"
    Then Table control is displayed
    And Spinner Role is displayed
    And Spinner Team is displayed
    And Button Submit is unable

  Scenario: Check view is displayed of Group when I click on this item
    When I click on tab item "2"
    Then Table control is displayed
    And Spinner Role is displayed
    And Spinner Group is displayed
    And Button Submit is unable

  Scenario: Check view is displayed of Project when I click on this item
    When I click on tab item "3"
    Then Table control is displayed
    And Spinner Role is displayed
    And Button Submit is unable
