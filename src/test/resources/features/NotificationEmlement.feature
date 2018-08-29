@Notification
Feature: Open Notification
  I want to login my account and check my notification

  Background: User navigates to home page and notification is displayed
    Given I am logged in as an android team manager

  Scenario: Login success with correct account
    When I click on notification icon
    Then Notification menu will display

  Scenario: Show all notification
    Given Notification menu is displayed
    When I click on seeAll
    Then Should navigate to correct page

  Scenario: View detail a notification
    Given Notification menu is displayed
    When I click on menu item
    Then Navigate to correct page


