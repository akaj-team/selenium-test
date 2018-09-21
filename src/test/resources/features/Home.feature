@HomePage
Feature: Open home page
  I want to Login Portal and check home page

  Background: User navigates to home page
    Given  I am logged in as an "EM"
    And I am stayed in home page

  Scenario Outline: Check color and data of tab on navigation header when clicked
    When I click on tab item "<position>"
    Then Color of tab "<position>" is "<colorActive>"
    And Color other tab "<position>" is "<colorDefault>"
    And I Should see data when has or no
    Examples:
      | position | colorActive | colorDefault |
      | 1        | #bfac8a     | #c2c2c2      |
      | 2        | #bfac8a     | #c2c2c2      |
      | 0        | #bfac8a     | #c2c2c2      |

  Scenario Outline: Check function search
    When I fill to search with value is "<valueSearch>"
    Examples:
      | valueSearch |
      | vi          |
      | viet        |
      | wh          |

  Scenario Outline: Check color and data of tab on right sideBar when clicked
    When I click on tab item "<position>" in right sideBar
    Then Color of tab "<position>" is "<colorActive>" in right sideBar
    And Color other tab "<position>" is "<colorDefault>" in right sideBar
    And I should see data Today's Event and Upcoming Event on right sideBar
    Examples:
      | position | colorActive | colorDefault |
      | 1        | #bfac8a     | #c2c2c2      |
      | 2        | #bfac8a     | #c2c2c2      |
      | 0        | #bfac8a     | #c2c2c2      |

  Scenario: Check click username on homeContent and open successfully profile page
    When I click on username in social-box
    Then Open successfully User Profile page

  Scenario: Check click avatar on homeContent and open successfully profile page
    When I click on avatar in social-box
    Then Open successfully User Profile page

  Scenario: Check click avatar on right sideBar and open successfully profile page
    When I click on avatar in right sideBar
    Then Open successfully User Profile page

  Scenario: Check click on Flowers and Congrats
    When I click on btn Flowers
    Then I should see Flowers is displayed
    When I click on btn Congrats
    Then I should see Congrats is displayed

  Scenario: Check scrollview on homeContent
    When I scroll down homeContent
    Then I scroll up homeContent

  Scenario: Check scrollview on right sideBar
    When I scroll down right sideBar
    Then I scroll up right sideBar
