@EditEmployeesPage
Feature: Check display views and handle events with editing employee

  Background: User navigates to edit employees page
    Given I am logged in as a team manager
    And Display employees page
    When Click on edit action of an employee
    Then Open successfully edit employee page

  Scenario: Check data and display of views
    Given Personal information tab is active
    Given Next button at edit employee page is clickable
    Given Submit button at edit employee page is clickable
    Given Deactive button at edit employee page is clickable
    Given Information of that employee displays correctly

  Scenario Outline: Check invalid data with first name input and submit button, next button, back button are not clickable
    When Fill First Name input with "<firstName>", "<aheadSpace>" and "<behindSpace>"
    Then Error message of first name is displayed
    And Submit button at edit employee page is not clickable
    And Next button at edit employee page is not clickable
    And Back button at edit employee page is not clickable
    Examples:
      | firstName                                         | aheadSpace | behindSpace |
      | First name with number 123                        | 0          | 0           |
      | First name longer than thirty five characters     | 0          | 0           |
      | First name with special characters ~!@#$%^&*()><? | 0          | 0           |
      | First name with spaces at the end of data         | 0          | 1           |
      | First name with spaces at the head of data        | 1          | 0           |