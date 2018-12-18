@NewProjectPage
Feature: Check Add New Project feature
  I want to login my account and add new Project

  Background: User navigates to home page
    Given I am logged in as a team manager
    And Display New Project page

  Scenario: Verify the information of my team is correct
    Then The member of Android team is displayed