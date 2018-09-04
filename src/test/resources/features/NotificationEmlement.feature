@NotificationElement
Feature: Open Notification
  I want to login my account and check my notification

  Background: User navigates to home page and notification is displayed
    Given I am logged in as an android team manager

  Scenario: Login success with correct account
    When I click on notification icon
    Then Notification menu will display

  Scenario: I want to see list first page notification
    When Notification menu is displayed
    Then First ten notifications displayed

  Scenario: I want to see all notification
    Given Notification menu is displayed
    When I click on seeAll
    Then Should navigate to correct page

  Scenario: I want to view detail a notification
    Given Notification menu is displayed
    When I click on menu item
    Then Navigate to correct detail page

  Scenario: I want to view next page on notification
    Given Notification menu is displayed
    When I scroll to item at last of list notification
    Then Next page of notification should displayed

  Scenario: I want to refresh list notification
    Given Notification menu is displayed
    When I click on reload text
    Then List notification should be reload

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

  Scenario: I want to make all notifications are as read
    Given Notification menu is displayed
    When I click on mark all as read text
    Then List notification should be hide

