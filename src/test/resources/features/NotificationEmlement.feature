@Notification
Feature: Open Notification
  I want to login my account and check my notification

  Background: User navigates to home page
    Given I am logged in as an android team manager

  Scenario: Login success with correct account
    When I click on notification icon
    Then Notification menu is displayed

  Scenario Outline: Notification menu is displayed
    When I click on seeAll
    Then navigate to home page
    Examples:
      |  |

