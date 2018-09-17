@EmployeesPage
Feature: Check display views and handle events
  Login my account and open employees page
  Check display views and handle events

  Background: User navigates to employees page
    Given I am logged in as an android team manager
    And Display employees page

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
    When Click on edit action edit
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

  Scenario: Check compatibility sum of cells with page number
    Then Check compatibility sum of cells with page number

  Scenario: Check page indicator '1' is active
    Then Page indicator 1 is active
    And Fist indicator and back indicator are not clickable

  Scenario: Check page indicators are active when click next indicator
    When Click on next indicator
    Then Page indicator corresponding is active
    And Check compatibility sum of cells with page number

  Scenario: Check page indicators are active when click back indicator
    When Click on back indicator
    Then Page indicator corresponding is active
    And Check compatibility sum of cells with page number

  Scenario: Check page indicators are active when click fist indicator
    When Click on fist indicator
    Then Page indicator corresponding is active
    And Fist indicator and back indicator are not clickable

  Scenario: Check page indicators are active when click last indicator
    When Click on last indicator
    Then Page indicator corresponding is active
    And Last indicator and next indicator are not clickable

  Scenario Outline: Search employees with name and having a result list
    When Enter search employee with "<name>"
    Then Show an employee list
    Examples:
      | name  |
      | Hang  |
      | Tien  |
      | Hoa   |
      | Huong |


  Scenario Outline: Search employees with name and having an empty list
    When Enter search employee with "<name>"
    Then Show an empty message
    Examples:
      | name |
      | abc  |
      | hehe |
      | kaka |
      | hoho |

  Scenario: Display correctly a position list
    When Click on position view
    Then Show a position list
    And Item "All Position" is selected

  Scenario Outline: Search employees with position and having a result list
    Given Click on position view
    When Search position with "<position>"
    Then  Display an employee list corresponding
    Examples:
      | position |
      | CEO      |
      | ASE      |
      | SSE      |

  Scenario Outline: Search employees with position and having no result
    Given Click on position view
    When Search position with "<position>"
    Then Display "No results found" message
    Examples:
      | position |
      | AAA      |
      | BBB      |
      | CCC      |

  Scenario: Filter employees with position and having a employee list
    Given Click on position view
    When Select any item in the position list
    Then Display an employee list corresponding

  Scenario: Filter employees with employee type
    Given Click on employee type view
    When Select any item in that type list
    Then Employee type is choosed
    Then Display an employee list corresponding

  Scenario: Filter employees with status
    Given Click on status view
    When Select any item in that status list
    Then A status item is choosed
    Then Display an employee list corresponding
