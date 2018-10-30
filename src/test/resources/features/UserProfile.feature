@UserProfile
Feature: Check user profile
  I want to login my account and check my information

  Background: User navigates to profile page
    Given I am logged in as an "EM"

  Scenario: Login success with correct account
    When I click on my name
    Then User profile is displayed
    And "Employed Date:" is "Jul 23, 2018"
    And "Employee Type:" is "Employee"
    And "Employee Code:" is "AT0514"
    And "Position:" is "QC Engineer"
