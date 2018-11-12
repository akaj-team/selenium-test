@LeaveOtherPage
Feature: Login AT Portal
  As leave other page
  I want to test leave other page

  Background: Check leave other page
    Given I am logged in as a team manager
    And Open leave of other page
    And Wait for load data

  Scenario: Test show dropdown popup when click items to sort on Leave Of Other page
    When I click status item
    Then Should show status dropdown
    When I click group by item
    Then Should show group by dropdown
    When I click choose start date
    Then Should show start date dialog
    When I click end date
    Then Should show end day dialog

  Scenario: Test show error message date
    Given I click choose start date
    When Should show start date dialog
    Then I choose a start date
    Given I click end date
    When Should show end day dialog
    Then I choose a end date
    When end date less than start date
    Then show error message with text is "End date must be on or after start date."

  Scenario: Test click item on employer id
    Given Leave of other have data
    When I click in employer id
    Then Should redirect to organisation employees

  Scenario: Test click item on employer name
    Given Leave of other have data
    When I click in employees name
    Then Should redirect to profile detail

  Scenario: Test click item on icon search
    Given Leave of other have data
    When I click in icon search
    Then Should redirect to organisation employees

  Scenario: Test show/hide button next
    Given Leave of other have data
    When Button next_to_end_page is shown
    And I click button next_to_end_page
    Then Should show end page
