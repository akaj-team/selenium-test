@TeamsPage
Feature: Login in AT portal
  As a team manager
  I want to check function delete team in teams page

  Background: User navigates to Company teams page
    Given I am logged in as an "EM"
    And I am stayed in teams page

  Scenario: Check name team correct with team is deleted
    Given I click on button delete team at position is 1
    Then I should see dialog confirm delete team
    And Name team is correct with team is chose

  Scenario: Delete team and cancel when dialog is showed
    Given I click on button delete team at position is 1
    Then I should see dialog confirm delete team
    When I choose button cancel on dialog
    Then Dialog is dismiss

  Scenario: Delete team and confirm ok when dialog is showed
    Given I click on button delete team at position is 1
    Then I should see dialog confirm delete team
    When I choose button ok on dialog
    Then Team is chose would be deleted
