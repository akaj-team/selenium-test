@LoginPage
Feature: Login AT Portal
  As an employee of the company
  I want to login my employee profile using my credentials
  In order to check my profile

#  Background: User navigates to Company home page
#    Given I open home page
#    Given I am an unauthenticated user
#    Then browser should redirect to "/auth/login"
#
#  Scenario: Login success with correct account
#    When I enter my username with "stg.tien.hoang@asiantech.vn"
#    And I fill in password with "Abc123@@"
#    And I click on login button
#    Then I should see the welcome message
#
#  Scenario Outline: Login button is disabled when not validated
#    When I enter my username with "<username>"
#    And I fill in password with "<password>"
#    Then Login button will be disabled
#    Examples:
#      | username                | password |
#      | Test                    | !23      |
#      | Tset                    | 123      |
#      | tien,hoang@asiantech.vn | !23      |
#      | Test                    |          |
#      |                         | 123      |
#      |                         |          |
#
#  Scenario Outline: Show error message
#    When I enter my username with "<username>"
#    And I fill in password with "<password>"
#    And I click on login button
#    Then Error message should display and show "<warning>"
#
#    Examples:
#      | username                | password  | warning                      |
#      | tien.hoang@asiantech.vn | 123       | Email or password incorrect. |
#      | tien.hoang@asiantech.vn | 12345     | Email or password incorrect. |
#      | tien.hoang@asiantech.vn | 123abc!@# | Email or password incorrect. |
#      | tien.hoang@asiantech.vn | 1         | Email or password incorrect. |
#      | tien.hoang@asiantech.vn | 123456788 | Email or password incorrect. |
#      | tien.hoang@asiantech.vn | 3         | Email or password incorrect. |
