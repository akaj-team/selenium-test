@MyTeam
Feature: Check My Teams information
  I want to login my account and check my team information

  Background: User navigates to home page
    Given I am logged in as an "EM"
    When I click on Organisation tab
    And I click on My Teams tab
    And I click on Android tab

  Scenario: Verify the information of my team is correct
    Then The member of Android team is displayed
    And The "Manager:" value is "Tien Hoang N."
    And The "Officer(s):" value is "Hieu Tran T."
    And The "Total Member:" value is "39"

  Scenario: Verify the behaviors of all functions
    When I click on 'Update Team' button
    Then The web page navigates to the "Update Team" page
    When I click on 'Teams' button
    Then The web page navigates to the "Teams" page
    When I click on 'Employee Avatar'
    Then The web page navigates to the "Employee Detail" page
    When I click on 'Employee Name'
    Then The web page navigates to the "Employee Detail" page
    When I click on 'Manager Name'
    Then The web page navigates to the "Employee Detail" page
    When I click on 'Officer name'
    Then The web page navigates to the "Employee Detail" page

#
#  Scenario Outline: Verify the behaviors of Search Employee function
#    When I enter "<name>" into Search field
#    Then Verify that the result of search is correct as "<result>"
#
#    Examples:
#      | name    | result |
#      | hue  | 2      |
#      | tran | 4      |


#// Scenario: Verify the behaviors of all functions
#    When I click on 'New Member' button
#    Then The web page navigates to the "Add New Member" page
