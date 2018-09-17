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
    When I enter the message
    And I choose type leave is "None Paid"
    Then Submit button still not enabled

  Scenario: Show message 0 days when choose type Annual Leave
    Given I enter the message
    When I choose type leave is "Annual Leave"
    Then Show message is "You have 0 days of left for type has chosen"
    When I click on box leave time
    Then Calendar not display

  Scenario: Show message 0 days when choose type Marriage Leave
    Given I enter the message
    When I choose type leave is "Marriage Leave"
    Then Show message is "You have 0 days of left for type has chosen"
    When I click on time from box leave time
    And I click on time to box leave time
    Then Calendar not display

  Scenario: Show message 0 days when choose type Overtime Leave
    Given I enter the message
    When I choose type leave is "Overtime Leave"
    Then Show message is "You have 0 days of left for type has chosen"
    When I click on box leave time
    Then Calendar not display

  Scenario: Show message 0 days when choose type Paternal Leave
    Given I enter the message
    When I choose type leave is "Paternal Leave"
    Then Show message is "You have 0 days of left for type has chosen"
    When I click on box leave time
    Then Calendar not display

  Scenario: Check choose time
    Given I choose type leave is "None Paid"
    When I click on time from box leave time
    Then Calendar display
    When I choose one day in calendar
    Then Show "Sep 17, 2018" in time from box
    When I click on time to box leave time
    Then Calendar display
    When I choose one day in calendar
    Then Show "Sep 20, 2018" in time to box
    And Show date request

  Scenario: Check date request
    Given I choose type leave is "None Paid"
    When I click on time from box leave time
    Then Calendar display
    When I choose one day in calendar
    Then Show "Sep 17, 2018" in time from box
    When I click on time to box leave time
    Then Calendar display
    When I choose one day in calendar
    Then Show "Sep 20, 2018" in time to box
    And Show date request
    When I choose afternoon on show date request
    Then Move circle form all day to afternoon
    When I choose morning on show date request
    Then Move circle form afternoon to morning

  Scenario: Check submit
    Given I choose type leave is "None Paid"
    When I click on time from box leave time
    Then Calendar display
    When I choose one day in calendar
    Then Show "Sep 17, 2018" in time from box
    When I click on time to box leave time
    Then Calendar display
    When I choose one day in calendar
    Then Show "Sep 17, 2018" in time to box
    When I enter the message
    Then Submit button is enabled
