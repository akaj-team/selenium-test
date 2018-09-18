@LeavePlanner
Feature: Check display views and handle events
  Login my account and open leave planners page
  Check display views and handle events

  Background: User navigates to leave planner page
    Given I am logged in as an android team manager
    And Display leave planner page

  Scenario: Can not click  this week button
    Then Can not click this week button

  Scenario: Can click this week button after click back button
    When Click on back button
    Then Can click this week button

  Scenario: Can click this week button after click next button
    When Click on next button
    Then Can click this week button

  Scenario: Open successfully profile page after click username
    When Click username
    Then Open successfully profile page of that user

  Scenario: Display leave message after hover avatar
    When Move to avatar user
    Then Display leave message

  Scenario: Display full seven columns after open leave planner page
    Then Display full seven columns

  Scenario: Display time correctly after open leave planner page
    Then Display time correctly
