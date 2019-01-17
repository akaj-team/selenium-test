@Wiki
Feature: Check wiki page

  Background: User navigates to profile page
    Given I am logged in as a team manager
    And Wiki page displayed

  Scenario: Check click name author
    When I click on name author
    Then Redirect employee detail page

#  Scenario: Check click child page button
#    When I click on child page button
#    Then Redirect new page page
#
#  Scenario: Check click update button
#    When I click on update button
#    Then Redirect update wiki page
#
#  Scenario: Check click child page title
#    When I click on child page title
#    Then Redirect wiki detail page
#
#  Scenario: Check click delete button
#    Then Can not click delete button
#
#  Scenario: Check click icon package
#    When I click on icon package Wiki
#    Then Categories disappeared
#    When I click on icon package Company Document
#    Then Categories inside hide
#    When I click on icon package Company Document again
#    Then Categories inside show
