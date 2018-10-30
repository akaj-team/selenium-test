@EmployeesPage
Feature: Check display views after search
  Fill the position
  Display corresponding employee list

  Background: User navigates to employees page
    Given I am logged in as an "QCE"
    And Display employees page

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

  Scenario Outline: Search position and having a result list
    Given Click on position view
    When Search position with "<position>"
    Then  Display a corresponding employee list
    And Display a corresponding position list with that position

    Examples:
      | position |
      | CEO      |
      | ASE      |
      | SSE      |

  Scenario Outline: Search position and having no result
    Given Click on position view
    When Search position with "<position>"
    Then Display "No results found" message
    Examples:
      | position |
      | AAA      |
      | BBB      |
      | CCC      |
