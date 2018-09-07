Feature: Check TimeSheet
  I want to login my account and check my  plan leave

  Background: User navigation to My leave page
    Given I logged in with a employee account
#    Given I am logged in as an android team manager

  Scenario: Login successfully with correct account
    When I click on Timesheet in menu
    Then Menu Timesheet drop down
    When I click on item My Timesheet
    Then My Timesheet page is displayed "/timesheet/submission"
    And Title content is "Sep 03 - Sep 09"
    And Display full record timesheet
    And Disable button this week and can not click
    And Disable button submit and can not click


  Scenario: Can click this week button after click back button
    Given I open my timesheet page
    When Click on back button
    Then Can click this week button

  Scenario: Can click this week button after click next button
    Given I open my timesheet page
    When Click on next button
    Then Can click this week button

  Scenario: Display button add timesheet after hover avatar
    Given I open my timesheet page
    When Move to columns timesheet
    Then Display button add new timesheet
