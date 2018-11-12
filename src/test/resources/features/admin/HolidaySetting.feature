@HolidaySetting
Feature: Check holidays setting
  I want to login my account and check holidays setting page

  Background: User navigation to holidays setting page
    Given I am logged in as a team manager
    And Display holiday setting page

  Scenario: Login successfully with correct account
    Then Holiday setting page is displayed "/admin/public-holiday"
    And Display title content
    And Display calendar content
    And Display button next and previous
    And Disable button today and can not click

  Scenario: Can click today button after click next button
    When Click button next on header
    Then Can click today button on header

  Scenario: Can click today button after click previous button
    When Click button previous on header
    Then Can click today button on header

  Scenario: Display title header calendar and content calendar
    Then Display full seven columns title header calendar
    And Display full item calendar day of month

  Scenario: Display Holiday detail and view show info calendar
    When Click item calendar holiday
    Then Display holiday detail dialog
    And Display title holiday detail dialog is "Holiday detail"
    And Display title input name
    And Display title holiday time
    And Display input description
    And Disable button save

  Scenario: Display Holiday detail and view show info calendar
    When Click item calendar holiday
    Then Display holiday detail dialog
    When Input Name is empty
    Then Show message error validate is "Please enter value"
    And Disable button save

  Scenario: Display Holiday detail and view show info calendar
    When Click item calendar holiday
    Then Display holiday detail dialog
    When Input name is "Request tet holiday"
    And Click on box holiday time to
    Then Display calendar date time
    When Choose today on dialog calendar date time
    Then Enable button save

  Scenario: Enter full value for dialog holiday detail
    Given Enter full information
    When Click button save
    Then Message success is showing

  Scenario: Delete holiday plain
    When Click item holiday
    Then Display holiday detail dialog
    And Enable button delete
    When Click button delete on dialog
    Then Display dialog confirm delete
    And Display title dialog confirm is "Confirmation"
    And Display content dialog confirm is "Are you about to delete this holiday?"
    When Click button cancel on dialog confirm delete
    Then Dismiss dialog confirm delete
    When Click button delete on dialog confirm delete
    Then Item holiday is delete
    And Message success is showing
