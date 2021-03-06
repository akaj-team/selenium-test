@NewEmployeeCompanyInformation
Feature: Check display views and handle events with company information at new employee page
  Login my account and open new employee page
  Check display views and handle events

  Background: User navigates to leave planner page
    Given I am logged in as a team manager
    And Display new employee page
    Then Open successfully company information tab with FirstName "Abbey", LastName "Bly"

  Scenario: Check creating new employee unsuccessfully
    When Click the assign check box
    And Choose join date input
    And Choose employee type "Collaborator"
    And Fill employee code input with "AT00054321", "0" and "0"
    And Click on dropdown position
    And Select position
    And Click on line manager
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

  Scenario Outline: Check display and operation of employee type
    When Choose employee type "<type>"
    Then Data in Employee Type is "<type>"
    Examples:
      | type         |
      | Collaborator |
      | Employee     |
      | Intern       |

  Scenario Outline: Validate data of employee code input
    When Fill employee code input with "<code>", "<aheadSpace>" and "<behindSpace>"
    And Click out of employee code input area
    Then Error message "Please enter value" of employee code is displayed
    And  A red border of employee code input is displayed
    Examples:
      | code                                        | aheadSpace | behindSpace |
      | Code longer than thirty ten characters      | 0          | 0           |
      | Code with special characters ~!@#$%^&*()><? | 0          | 0           |
      | Code with spaces at the end of data         | 0          | 1           |
      | Code with spaces at the head of data        | 1          | 0           |

  Scenario: Check display of email correctly
    Given Data in email input is correct with FirstName "Abbey", LastName "Bly"

  Scenario Outline: Validate data of email input
    When Fill email input with "<email>"
    And Click out of email input area
    Then Error message "Please enter value follow email address format" of email is displayed
    And  A red border of email input is displayed
    Examples:
      | email                 |
      | @asiantech.vn         |
      | Abc .def@asiantech.vn |
      | Abc. def@asiantech.vn |
      | Abc.def @asiantech.vn |
      | Abc.def@ asiantech.vn |
      | Abc.def@asiantech. vn |
      | Abcdef @asiantech.vn  |

  Scenario: Check display of dropdown position
    When Click on dropdown position
    And Select position
    Then Data of dropdown position is correct

  Scenario: Check display of dropdown line manager
    When Click on line manager
    And Select line manager
    Then Data of dropdown line manager is correct
