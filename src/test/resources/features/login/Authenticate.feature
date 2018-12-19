@LoginAndLogout
Feature: Check Login and logout

  Scenario: I open homepage and check my certificate, if url redirect to login page then I login with my account
    Given I am logged in as a team manager
    Given I am an unauthenticated user
