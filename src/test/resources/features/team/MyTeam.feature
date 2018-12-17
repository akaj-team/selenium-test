@MyTeamPage
Feature: Check My Teams information
  I want to login my account and check my team information

  Background: User navigates to home page
    Given I am logged in as a team manager
    And Display My Team page

  Scenario: Verify the information of my team is correct
    Given The member of Android team is displayed
    And The "Manager:" value is "Tien Hoang N."
    And The "Officer(s):" value is "Hieu Tran T."
    And The "Total Member:" value is "44"

  Scenario: Verify the behaviors of Update Team button
    When I click on 'Update Team' button
    Then The web page navigates to the "/update" page

  Scenario: Verify the behaviors of Teams button
    When I click on Teams button
    Then The web page navigates to the "/teams" page

  Scenario: Verify Add member function
    When I click on New Member button
    Then The Add Member popup is displayed
    When I input "Hue Thai T." into search input to add member
    Then I verify that search result list is correct
    When I click on Add button
    And I click on Close button
    Then The Add Member popup is disappeared

  Scenario Outline: Verify Search user function
    When I input "<empName>" into search input to search member
    Then I verify that members of team are displayed correctly as "<record>"
    Examples:
      | empName     | record |
      | Hue Thai T. | 1      |
      | Tien        | 2      |

  Scenario: Verify the Delete user function
    When I input "hue" into search input to search member
    And I click on Delete button to delete searched user
    Then I verify that deleting user successful

  Scenario Outline: Verify the Update Team info function
    When I click on 'Update Team' button
    Then The web page navigates to the "/update" page
    When I input "<name>" "<manager>" "<teamOfficer1>" "<teamOfficer2>" "<email>" "<teamFolder>" "<description>"
    And I click on Submit button
    Examples:
      | name    | manager           | teamOfficer1 | teamOfficer2    | email                 | teamFolder                                  | description       |
      | Android | Tien Hoang N.     | Hieu Tran T. | Huy Nguyen      | androidteam@gmail.com | http://portal-stg.asiantech.vn/organisation | There is My Team! |
