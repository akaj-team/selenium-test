Feature: Check award category page
  Login my account and open award category page
  Check display views and handle events

  Background: Login my account and go to award category page
    Given I am logged in as a "BDM"
    And Award Category page displayed

  Scenario: Check click new award button
    When I click new award button
    Then Show dialog new award

  Scenario: Check click edit button
    When I click edit button
    Then Show dialog edit

  Scenario: Check enable submit in dialog
    Given New dialog is showed
    When I input name on Name box
    Then Submit change from disable to enable

  Scenario: Check when click submit
    Given New dialog is showed
    And Check count line in table
    When I input name on Name box
    Then I click submit button

  Scenario: Check enter name was exist
    Given New dialog is showed
    When I input name on Name box

  Scenario: Check click submit with full information
    Given New dialog is showed and entered name
    And Check count line in table
    When I enter description
    Then I click submit button

  Scenario: Check clear name box
    Given I click edit button
    And Dialog edit is showed
    When I clear name box
    Then Display message "Please enter value"
    And Disable submit button

  Scenario: Check close button
    Given I click edit button
    And Dialog edit is showed
    And Check count line in table
    When I clear name box
    And I input name on Name box
    But I click close button
    Then Disappear dialog

  Scenario: Check cancel button
    Given I click edit button
    And Dialog edit is showed
    When I click cancel button
    Then Disappear dialog
