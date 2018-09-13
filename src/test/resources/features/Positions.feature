Feature: See, create, edit, search position
  Login my account to portal and open positions page
  I want to check display and edit position of members

  Background: User navigates to leave planner page
    Given I am logged in as an android team manager
    And Display "/organisation/positions" page

  Scenario:  I see title is display
    Then I see header title "Positions" is display

  Scenario: Check button new positions and career path is display
    Then I see button new position and career path is display

  Scenario: Check new position page
    When I click on new position button
    Then I move to new position page