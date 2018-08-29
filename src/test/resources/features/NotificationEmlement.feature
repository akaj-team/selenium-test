@Notification
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
    When I scroll to notification at ten
    Then Next page of notification should displayed

  Scenario: I want to refresh list notification
    Given Notification menu is displayed
    When I click on reload text
    Then List notification should be reload
