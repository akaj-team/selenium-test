@MyTeam
Feature: Check My Teams information
  I want to login my account and check my team information

  Background: User navigates to home page
    Given I am logged in as an "EM"
    When I click on Organisation tab
    And I click on My Teams tab
    And I click on Android tab

  Scenario: Verify the information of my team is correct
    Then The member of Android team is displayed
    And The "Manager:" value is "Tien Hoang N."
    And The "Officer(s):" value is "Hieu Tran T."
    And The "Total Member:" value is "39"
