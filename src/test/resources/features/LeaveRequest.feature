Feature: Create new my leave
  As an employee of the company
  I want to create new my leave
  In order to take a few days off work

  Background: User navigates to profile page
    Given I am logged in as an team member
    And Display leave request page
    And Annual Leave is "0"
    And Marriage Leave is "0"
    And Overtime Leave is "0"
    And Paternal Leave is "0"

  Scenario: Submit button is disabled when just enter the message
    When I enter the message
    Then Submit button still disabled

  Scenario: Submit button is disabled when just enter the message and choose time
    Given I chose type leave is "None Paid"
    When I enter the message
    Then Submit button still disabled

  Scenario: Show message 0 days when choose type Annual Leave
    Given I enter the message
    And I click menu type of leave
    Then Menu type of leave drop down
    When I choose type leave is "Annual Leave"
    Then Show message is "You have 0 days of left for type has chosen"

  Scenario: Show message 0 days when choose type Marriage Leave
    Given I enter the message
    And I click menu type of leave
    Then Menu type of leave drop down
    When I choose type leave is "Marriage Leave"
    Then Show message is "You have 0 days of left for type has chosen"

  Scenario: Show message 0 days when choose type Overtime Leave
    Given I enter the message
    And I click menu type of leave
    Then Menu type of leave drop down
    When I choose type leave is "Overtime Leave"
    Then Show message is "You have 0 days of left for type has chosen"

  Scenario: Show message 0 days when choose type Paternal Leave
    Given I enter the message
    And I click menu type of leave
    Then Menu type of leave drop down
    When I choose type leave is "Paternal Leave"
    Then Show message is "You have 0 days of left for type has chosen"

  Scenario: Check choose time
    Given I chose type leave is "None Paid"
    When I click on timeFrom box leave time
    Then Calendar timeFrom display
    And I choose Sep 17, 2018 in timeForm calendar
    When I click on timeTo box leave time
    Then Calendar timeTo display
    And I choose Sep 17, 2018 in timeTo calendar
    And Show date request

  Scenario: Check date request
    Given I chose type leave is "None Paid"
    When I click on timeFrom box leave time
    Then Calendar timeFrom display
    When I choose Sep 17, 2018 in timeForm calendar
    When I click on timeTo box leave time
    Then Calendar timeTo display
    When I choose Sep 17, 2018 in timeTo calendar
    Then Show date request
    And Check date is "Monday, Sep 17, 2018"
    And I choose afternoon on show date request
    And I choose morning on show date request
    And I choose all day on show date request
    When I click remove button
    Then Date request is removed

  Scenario: Check submit
    Given I chose type leave is "None Paid"
    When I click on timeFrom box leave time
    Then Calendar timeFrom display
    And I choose Sep 17, 2018 in timeForm calendar
    When I click on timeTo box leave time
    Then Calendar timeTo display
    And I choose Sep 17, 2018 in timeTo calendar
    When I enter the message
    Then Submit button is enabled

  Scenario: Check show dialog
    Given Enter full information
    When I click submit
    Then Show dialog confirmation

  Scenario: Check click submit on dialog
    Given Dialog was showed
    When I click submit on dialog
    Then Redirect to leave detail

  Scenario: Check click cancel on dialog
    Given Dialog was showed
    When I click cancel on dialog
    Then Dialog disappeared
