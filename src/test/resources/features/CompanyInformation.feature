@NewEmployeeCompanyInformation
Feature: Check display views and handle events with company information
  Login my account and open new employee page
  Check display views and handle events

  Background: User navigates to leave planner page
    Given I am logged in as an "EM"
    And Display new employee page
    Then Open successfully company information tab
    And Display new employee page with company information tab

  Scenario: Check creating new employee unsuccessfully
    When Click the assign check box
    And Choose join date input
    And Select employee type
    And Fill employee code input with "AT00054321"
    And Select position
    And Select line manager
    And Click submit button
    And Click submit button of a dialog is showed
    Then A error alert is showed

  Scenario: Check checkbox assignment displays correctly
    When Click the assign check box
    Then Checkbox is unchecked or checked

  Scenario: Check display of data in calendar form and join date input
    When Click to join date input
    Then Calendar form of join date is showed
    Then Data in join date input displays correctly

  Scenario: Check display and operation of employee type 
    When Click to join date input
    Then Calendar form of join date is showed
    Then Data in join date input displays correctly

#    Examples:
#      | code                                        | ahead space | behind space |
#      | Code longer than thirty ten characters      | 0           | 0            |
#      | Code with special characters ~!@#$%^&*()><? | 0           | 0            |
#      | Code with spaces at the end of data         | 0           | 1            |
#      | Code with spaces at the head of data        | 1           | 0            |
