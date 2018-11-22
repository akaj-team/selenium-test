@EditEmployeesPage
Feature: Check display views and handle events with editing employee

  Background: User navigates to edit employees page
    Given I am logged in as a team manager
    And Display employees page
    When Click on edit action of an employee
    Then Open successfully edit employee page

#  Scenario: Check data and display of views
#    Given Personal information tab is active
#    Given Next button at edit employee page is clickable
#    Given Submit button at edit employee page is clickable
#    Given Deactive button at edit employee page is clickable
#    Given Information of that employee displays correctly

#  Scenario Outline: Check invalid data with first name input and submit button, next button, back button are not clickable
#    When Fill First Name input with "<firstName>", "<aheadSpace>" and "<behindSpace>"
#    Then Error message of first name is displayed
#    And Submit button at edit employee page is not clickable
#    And Next button at edit employee page is not clickable
#    And Back button at edit employee page is not clickable
#    Examples:
#      | firstName                                         | aheadSpace | behindSpace |
#      | First name with number 123                        | 0          | 0           |
#      | First name longer than thirty five characters     | 0          | 0           |
#      | First name with special characters ~!@#$%^&*()><? | 0          | 0           |
#      | First name with spaces at the end of data         | 0          | 1           |
#      | First name with spaces at the head of data        | 1          | 0           |

#  Scenario: Check display when at timeline tab
#    When Click next button two times
#    Then Timeline tab is active
#    And Submit button is not displayed
#    And Deactive button is not displayed

  Scenario: Handle create new event with "Promotion - Position"
    Given Click next button two times
    Then Click new event button
    And A new event dialog displayed
    And Select type of event "Promotion - Position"
    When Select position of promotion
    When Select date of promotion
    When Click submit button of new event dialog

#    Scenario: Deactivate unsuccessfully an employee with management roles
#    When Click deactive button
#    Then A notification dialog is display
#    When Click close button
#    Then This deactive dialog dismissed

#  Scenario: Deactivate successfully an employee without management roles
#    When Click deactive button
#    Then A notification dialog is display
#    When Choose day to deactivate this employee
#    Then Click deactive button of dialog button
#    Then Deactivate successfully this employee
#
#  Scenario: Deactivate unsuccessfully an employee without management roles
#    When Click deactive button
#    Then A notification dialog is display
#    Then Click cancel button of dialog button
#    Then Deactivate successfully this employee