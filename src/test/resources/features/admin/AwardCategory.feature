@AwardCategoryPage
Feature: Check award category page
  Login my account and open award category page
  Check display views and handle events

  Background: Login my account and go to award category page
    Given I am logged in as a team manager
    And Award Category page displayed

  Scenario: Open dialog new award when click new award button
    When I click new award button
    Then Show dialog new award

  Scenario: Open dialog edit when click edit award button
    When I click edit button
    Then Show dialog edit

  Scenario: Check status button submit in dialog
    Given New dialog is showed
    When I input name on Name box
    Then Submit change from disable to enable

  Scenario: Check when click submit
    Given New dialog is showed
    And I input name on Name box
    And I enter description
    When I click submit button
    Then Alert message is displayed

  Scenario: Check enter name was exist
    Given New dialog is showed
    And I input name was exist on Name box
    And I enter description
    When I click submit button
    Then Alert message is displayed

  Scenario Outline: Show message error when enter name have while space in ahead or end of name
    Given New dialog is showed
    When I input name have while space in "<whileSpaceBeginning>" or "<whileSpaceEnd>" of name
    And I click submit button
    Then Display message "Please enter value"
    Examples:
      | whileSpaceBeginning | whileSpaceEnd |
      | 1                   | 0             |
      | 0                   | 1             |
      | 1                   | 1             |

  Scenario: Check validate when clear name box
    Given I click edit button
    And Dialog edit is showed
    When I clear name box
    Then Display message "Please enter value"
    And Disable submit button

  Scenario: Check button close in dialog
    Given I click edit button
    And Dialog edit is showed
    When I clear name box
    And I input name on Name box
    But I click close button
    Then Disappear dialog

  Scenario: Check button cancel in dialog
    Given I click edit button
    And Dialog edit is showed
    When I click cancel button
    Then Disappear dialog

  Scenario: Open alert delete success when click delete button
    When I click Delete button
    And I click delete confirm button
    Then Alert delete success is showed
