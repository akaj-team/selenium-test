@HomePage
Feature: Open home page
  I want to Login Portal and check home page

  Background: User navigates to home page
    Given  I am stayed in home page

  Scenario: Check Home page is started
    Then Header navigation is displayed
    And Color of tab "AllNews" is "#bfac8a"
    And Color of tab "AllEvents" is "#bfac8a" in right sideBar
    And Title New Feeds is displayed
    And ToolBox Search is displayed

  Scenario Outline: Check status button and data when click on tab navigation header
    When I click on tab item "<tabName>"
    Then Color of tab "<tabName>" is "<colorActive>"
    And Color other tab "<tabName>" is "<colorDefault>"
    And I Should see data when has or no
    Examples:
      | tabName       | colorActive | colorDefault |
      | Announcerment | #bfac8a     | #c2c2c2      |
      | Notifications | #bfac8a     | #c2c2c2      |
      | AllNews       | #bfac8a     | #c2c2c2      |

  Scenario Outline: Check function search
    When I enter in toolbox search with string value is "<valueSearch>"
    Examples:
      | valueSearch |
      | vi          |
      | viet        |
      | wh          |

  Scenario Outline: Check status button and data when click on item in right sideBar
    When I click on tab item "<tabName>" in right sideBar
    Then Color of tab "<tabName>" is "<colorActive>" in right sideBar
    And Color other tab "<tabName>" is "<colorDefault>" in right sideBar
    And I should see data Today's Event and Upcoming Event on right sideBar
    Examples:
      | tabName     | colorActive | colorDefault |
      | Birthday    | #bfac8a     | #c2c2c2      |
      | Anniversary | #bfac8a     | #c2c2c2      |
      | AllEvents   | #bfac8a     | #c2c2c2      |

  Scenario: Check click username on homeContent and open successfully profile page
    When I click on username in social-box
    Then I should see User profile is displayed

  Scenario: Check click avatar on homeContent and open successfully profile page
    When I click on avatar in social-box
    Then I should see User profile is displayed

  Scenario: Check click avatar on right sideBar and open successfully profile page
    When I click on avatar in right sideBar
    Then I should see User profile is displayed

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

