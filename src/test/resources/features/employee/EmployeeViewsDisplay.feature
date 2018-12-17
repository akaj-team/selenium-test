@EmployeesPage
Feature: Check display views after click views at employee page
  Click name, avatar, code, manager, team, group and edit
  Display a corresponding page
  Click promotion, award category and new employee
  Display a corresponding dialog
  Display views after click page indicators and header

  Background: User navigates to employees page
    Given I am logged in as a team manager
    And Display employees page

  # Table
  Scenario: Open successfully profile page of a employee with name
    When Click on employee name
    Then Open successfully profile page of that employee

  Scenario: Open successfully profile page of a employee with avatar
    When Click on employee avatar
    Then Open successfully profile page of that employee

  Scenario: Open successfully profile page of a employee with code
    When Click on employee code
    Then Open successfully profile page of that employee

  Scenario: Open successfully profile page of a manager
    When Click on manager name
    Then Open successfully profile page of that manager

  Scenario: Open successfully team detail page
    When Click on team name
    Then Open successfully a team detail page

  Scenario: Open successfully group detail page
    When Click on group name
    Then Open successfully a group detail page

  Scenario: Open successfully edit employee page
    When Click on edit action
    Then Open successfully update employee page

  Scenario: Open a dialog when click on promotion button
    When Click on promotion button
    Then Open an import promotion dialog

  Scenario: Open a dialog when click on award category button
    When Click on award category button
    Then Open an import award dialog

  Scenario: Open a dialog when click on new employee button
    When Click on new employee button
    Then Open successfully new employee page

  Scenario: Show maximum 50 cells
    Then Show maximum 50 cells

 # Indicator
  Scenario: Check page indicators are active when click next indicator
    When Click on next indicator
    Then Corresponding page indicator is active
    And Check compatibility sum of cells with page number

  Scenario: Check page indicators are active when click back indicator
    When Click on back indicator
    Then Corresponding page indicator is active
    And Check compatibility sum of cells with page number

  Scenario: Check page indicators are active when click fist indicator
    When Click on fist indicator
    Then Corresponding page indicator is active
    And Fist indicator and back indicator are not clickable

  Scenario: Check page indicators are active when click last indicator
    When Click on last indicator
    Then Corresponding page indicator is active
    And Last indicator and next indicator are not clickable

  Scenario: Check compatibility sum of cells with page number
    Then Check compatibility sum of cells with page number

 # Header
  Scenario: Check page indicator '1' is active
    Then Page indicator 1 is active
    And Fist indicator and back indicator are not clickable

  Scenario: Display correctly a position list with default position
    When Click on position view
    Then Show a position list
    And Item "All Position" is selected

    # Promotion
  Scenario: Check display of promotion dialog
    When Click on promotion button
    And Open an import promotion dialog
    Then The import promotion button is clickable
    When Click on cancel button
    Then The import promotion dialog is dismissed

    # Award
  Scenario: Check display of award category dialog
    When Click on award category button
    And Open an import award dialog
    Then The import award button is clickable
    When Click on cancel button
    Then The import award category dialog is dismissed

  Scenario: Check display of award category dialog
    When Click on award category button
    And Open an import award dialog
    And Select award category dropdown
    Then Data in award category dropdown is correct
