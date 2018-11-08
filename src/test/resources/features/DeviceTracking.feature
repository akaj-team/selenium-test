Feature: Check Device tracking
  I want to login my account and check device tracking page

  Background: User navigation to device tracking page
    Given I logged in with a employee account

  Scenario: Login successfully with correct account
    When I click on device in menu
    Then Menu device drop down
    When I click on item device tracking
    Then Device tracking page is displayed "/equipments/tracking"
    And Display title content is "Nov 05 - Nov 11"
    And Display list content device
    And Display button next and previous
    And Display checkbox select all not tick
    And Disable button this week and can not click
    And Disable button submit and can not click

  Scenario: Can click today button after click next button
    Given I open device tracking page
    When Click button next on header
    Then Can click this week button on header

  Scenario: Can click today button after click previous button
    Given I open device tracking page
    When Click button previous on header
    Then Can click this week button on header

  Scenario: Display title header device and content device
    Given I open device tracking page
    Then Display full seven columns title header device
    And Display full seven columns content device

  Scenario: Display title header device and content device
    Given I open device tracking page
    When move to item device
    Then Display title and border item device

  Scenario: Display title header device and content device
    Given I open device tracking page
    When move to item device
    Then Display title and border item device

  Scenario: Display button Submit when click item device
    Given I open device tracking page
    When Click item on list device item
    Then Display item device with color selected
    And Display button submit and can clickable

  Scenario: Display dialog confirm and leave page
    Given I open device tracking page
    When Click item on list device item
    And Display button submit and can clickable
    When Click button next on header
    Then Display dialog confirm
    And Display title dialog confirm is "Confirmation"
    And Display message dialog confirm is "Are you sure you want to leave this page?"
    And Display button stay and button leave
    When Click button close dialog
    Then Dismiss dialog confirm
    When Click button stay on dialog confirm
    Then Dismiss dialog confirm and keep state selected
    When Click button leave on dialog confirm
    Then Dismiss dialog confirm and clear state selected

  Scenario: Display button Submit when click item device
    Given I open device tracking page
    When Click checkbox select all trackings
    Then Display all item device with color selected
    And Display button submit and can clickable
    When Click button submit
    Then Display dialog confirm Submit
    And Display title dialog confirm submit is "Confirmation"
    And Display message dialog confirm submit is "Are you sure to submit trackings?"
    And Display button cancel and button submit
    When Click button close dialog confirm submit
    Then Dismiss dialog confirm submit
    When Click button cancel on dialog confirm submit
    Then Dismiss dialog confirm submit and keep state selected all
    When Click button submit on dialog confirm submit
    Then Dismiss dialog confirm and change state to submit
    And Display message success
