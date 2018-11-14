@MyTeamPage
Feature: Check My Teams information
  I want to login my account and check my team information

  Background: User navigates to home page
    Given I am logged in as a team manager
    And Display My Team page

  Scenario: Verify the information of my team is correct
    Then The member of Android team is displayed
    And The "Manager:" value is "Tien Hoang N."
    And The "Officer(s):" value is "Hieu Tran T."
    And The "Total Member:" value is "39"

  Scenario: Verify the behaviors of Update Team button
    When I click on 'Update Team' button
    Then The web page navigates to the "/update" page

  Scenario: Verify the behaviors of Teams button
    When I click on 'Teams' button
    Then The web page navigates to the "/teams" page

  Scenario: Verify Add member function
    When I click on New Member button
    Then The Add Member popup is displayed
    When I input "Hue" into search input to add member
    Then I verify that search result list is correct with "2"
    When I click on Add button
    And I click on Close button
    Then The Add Member popup is disappeared

  Scenario Outline: Verify Search user function
    When I input "<empName>" into search input to search member
    Then I verify that members of team are displayed correctly as "<record>"
    Examples:
      | empName | record |
      | hue     | 1      |
      | tien    | 2      |

  Scenario Outline: Verify the Delete user function
    When I input "<empName>" into search input to search member
    And I click on Delete button to delete searched user
    Then I verify that deleting user successful
    Examples:
      | empName |
      | hue     |

  Scenario Outline: Verify the Update Team info function
    When I click on 'Update Team' button
    Then The web page navigates to the "/update" page
    When I input "<name>" "<manager>" "<teamOfficer1>" "<teamOfficer2>" "<logo>" "<teamFolder>" "<description>"
    And I click on Submit button
    Examples:
      | name    | manager       | teamOfficer1 | teamOfficer2 | logo                                   | teamFolder                                  | description       |
      | Android | Tien Hoang N. | Hieu Tran T. | Huy Nguyen   | /Users/huethai/Pictures/teamavatar.jpg | http://portal-stg.asiantech.vn/organisation | There is My Team! |

