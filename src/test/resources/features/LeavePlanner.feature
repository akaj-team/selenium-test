@LeavePlanner
Feature: Check display views and handle events
  Login my account and open leave planners page
  Check display views and handle events

  Background: User navigates to leave planner page
    Given I am logged in as an android team manager

  Scenario: Can not click  this week button
    Given Display leave planner page
    Then Can not click this week button

  Scenario: Can click this week button after click back button
    Given Display leave planner page
    When Click on back button
    Then Can click this week button

  Scenario: Can click this week button after click next button
    Given Display leave planner page
    When Click on next button
    Then Can click this week button

  Scenario: Open successfully profile page after click username
    Given Display leave planner page
    When Click username
    Then Open successfully profile page of that user

  Scenario: Display leave message after hover avatar
    Given Display leave planner page
    When Move to avatar user
    Then Display leave message

  Scenario: Display full seven columns after open leave planner page
    Given Display leave planner page
    Then Display full seven columns

  Scenario: Display time correctly after open leave planner page
    Given Display leave planner page
    Then Display time correctly
