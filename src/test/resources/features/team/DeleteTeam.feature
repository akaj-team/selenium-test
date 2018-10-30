@TeamsPage @Ignore
Feature: Check function delete team in teams page
  I want to login as an EM an check function delete team

  Background: User navigates to Company teams page
    Given I am logged in as an "EM"
    And I am stayed in dialog confirm delete team at position is 1

  Scenario: Check name team correct with team is deleted
    Then Name team is correct with team is chose

  Scenario: Delete team and cancel when dialog is showed
    When I choose button cancel on dialog
    Then Dialog is dismiss

  Scenario: Delete team and confirm ok when dialog is showed
    When I choose button ok on dialog
    Then Team is chose would be deleted
