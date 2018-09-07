@LoginPage
Feature: Login AT Portal
  As an employee of the company
  I want to login my employee profile using my credentials
  In order to check my profile

  Background: User navigates to Company home page
    Given I open home page
    Given I am an unauthenticated user
    Then browser should redirect to "/auth/login"

  Scenario: Login success with correct account
    When I enter my username with "stg.tien.hoang@asiantech.vn"
    And I fill in password with "Abc123@@"
    And I click on login button
    Then I should see the welcome message

  Scenario Outline: Login button is disabled when not validated
    When I enter my username with "<username>"
    And I fill in password with "<password>"
    Then Login button will be disabled
    Examples:
      | username                | password |
      | Test                    | !23      |
      | Tset                    | 123      |
      | tien,hoang@asiantech.vn | !23      |
      | Test                    |          |
      |                         | 123      |
      |                         |          |

  Scenario Outline: Show error message
    When I enter my username with "<username>"
    And I fill in password with "<password>"
    And I click on login button
    Then Error message should display and show "<warning>"

    Examples:
      | username                | password  | warning                      |
      | tien.hoang@asiantech.vn | 123       | Email or password incorrect. |
      | tien.hoang@asiantech.vn | 12345     | Email or password incorrect. |
      | tien.hoang@asiantech.vn | 123abc!@# | Email or password incorrect. |
      | tien.hoang@asiantech.vn | 1         | Email or password incorrect. |
      | tien.hoang@asiantech.vn | 123456788 | Email or password incorrect. |
      | tien.hoang@asiantech.vn | 3         | Email or password incorrect. |

  Scenario Outline: I want to login with account have my employee code is AT0506
    Given I am an unauthenticated user
    Given browser should redirect to "/auth/login"
    When I enter my username with "<username>"
    And I fill in password with "<password>"
    And I click on login button
    Then Employee code should be "<employee-code>"

    Examples:
      | username                     | password | employee-code |
      | stg.tri.pham@asiantech.vn    | Abc123@@ | AT0501        |
      | stg.thien.dang2@asiantech.vn | Abc123@@ | AT0498        |
      | stg.lam.le2@asiantech.vn     | Abc123@@ | AT0514        |
      | stg.tu.le.2@asiantech.vn     | Abc123@@ | AT0511        |
      | stg.hang.nguyen@asiantech.vn | Abc123@@ | AT0506        |

  Scenario: Login success with correct account
    Given Check current account employee code is my employee code "AT0036"
    Then Employee code should be "AT0036"
