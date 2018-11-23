@DeviceTracking
Feature: Check Device tracking
  I want to login my account and check device tracking page

  Background: User navigation to device tracking page
    Given I am logged in as a team manager
    And Display device tracking page

  Scenario: Login successfully with correct account
    Then Device tracking page is displayed "/equipments/tracking"
    And Display title content device
    And Display list content device
    And Display button next and previous device tracking
    And Display checkbox select all not tick
    And Disable button this week and can not click
    And Disable button submit and can not click

  Scenario: Can click today button after click next button
    When Click button next on header device tracking
    Then Can click this week button on header device tracking

  Scenario: Can click today button after click previous button
    When Click button previous on header device tracking
    Then Can click this week button on header device tracking

  Scenario: Display title header device and content device
    Then Display full seven columns title header device
    And Display full seven columns content device

  Scenario: Display title header device and content device
    When Move to item device
    Then Display title and border item device

  Scenario: Display title header device and content device
    When Move to item device
    Then Display title and border item device

  Scenario: Display button Submit when click item device
    When Click item on list device item
    Then Display item device with color selected
    And Display button submit and can clickable

  Scenario: Display dialog confirm and leave page
    When Click item on list device item
    And Display button submit and can clickable
    When Click button next on header device tracking
    Then Display dialog confirm device tracking
    And Display title dialog confirm device tracking is "Confirmation"
    And Display message dialog confirm is "Are you sure you want to leave this page?"
    And Display button stay and button leave
    When Click button close dialog device tracking
    Then Dismiss dialog confirm device tracking
    When Click button stay on dialog confirm
    Then Dismiss dialog confirm and keep state selected
    When Click button leave on dialog confirm
    Then Dismiss dialog confirm and clear state selected

  Scenario: Display button Submit when click item device
    When Click checkbox select all tracking
    Then Display all item device with color selected
    And Display button submit and can clickable
    When Click button submit device tracking
    Then Display dialog confirm Submit device tracking
    And Display title dialog confirm submit is "Confirmation"
    And Display message dialog confirm submit is "Are you sure to submit trackings?"
    And Display button cancel and button submit device tracking
    When Click button close dialog confirm submit device tracking
    Then Dismiss dialog confirm submit device tracking
    When Click button cancel on dialog confirm submit device tracking
    Then Dismiss dialog confirm submit and keep state selected all
    When Click button submit on dialog confirm submit device tracking
    Then Dismiss dialog confirm and change state to submit device tracking
    And Display message success device tracking
