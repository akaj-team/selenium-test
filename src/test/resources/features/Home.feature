@HomePage
Feature: Open home page
  I want to Login Portal and check home page

  Background: User navigates to home page
    Given  I am logged in as an "EM"
    And I am stayed in home page

  Scenario Outline: Check color and data of tab on navigation header when clicked
    When I click on tab item "<position>"
    Then Color of tab "<position>" is "<activeColor>"
    And Color other tab "<position>" is "<defaultColor>"
    And I Should see data when has or no
    Examples:
      | position | activeColor | defaultColor |
      | 1        | #bfac8a     | #c2c2c2      |
      | 2        | #bfac8a     | #c2c2c2      |
      | 0        | #bfac8a     | #c2c2c2      |

  Scenario Outline: Check color and data of tab on right sideBar when clicked
    When I click on tab item "<position>" in right sideBar
    Then Color of tab "<position>" is "<activeColor>" in right sideBar
    And Color other tab "<position>" is "<defaultColor>" in right sideBar
    And I should see data Today's Event and Upcoming Event on right sideBar
    Examples:
      | position | activeColor | defaultColor |
      | 1        | #bfac8a     | #c2c2c2      |
      | 2        | #bfac8a     | #c2c2c2      |
      | 0        | #bfac8a     | #c2c2c2      |

  Scenario Outline: I fill to search and result is list feed
    When I fill to search with value is "<searchData>"
    Then I should see list feed is displayed
    Examples:
      | searchData |
      | vi          |
      | viet        |
      | wh          |

  Scenario Outline: I fill to search and result is empty
    When I fill to search with value is "<searchData>"
    Then I should see message "No data available"
    Examples:
      | searchData |
      | vivivivivi  |
      | iviviviviv  |
      | zzzzzzzzzz  |

  Scenario: Click username on homeContent and open successfully profile page
    When I click on username in social-box
    Then Open successfully User Profile page

  Scenario: Click avatar on homeContent and open successfully profile page
    When I click on avatar in social-box
    Then Open successfully User Profile page

  Scenario: Click avatar on right sideBar and open successfully profile page
    When I click on avatar in right sideBar
    Then Open successfully User Profile page

  Scenario: Show effect when click on Flowers and Congrats
    When I click on button Flowers
    Then I should see Flowers is displayed
    When I click on button Congrats
    Then I should see Congrats is displayed

  Scenario: Scrollview on homeContent
    When I scroll down homeContent
    Then I scroll up homeContent

  Scenario: Scrollview on right sideBar
    When I scroll down right sideBar
    Then I scroll up right sideBar
