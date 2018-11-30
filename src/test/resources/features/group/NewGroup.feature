Feature:  Login my account to portal and open group page
  I want to check new group page

  Background: User navigation to new group page
    Given I am logged in as a team manager
    And Displayed new group page

  Scenario Outline: Test value for Name of group
    When I enter name of group field with text is "<nameGroup>" and first space is "<firstSpace>" and last space is "<lastSpace>"
    Then Show name of group message error with text "<message>"
    Examples:
      | nameGroup | message            | firstSpace | lastSpace |
      |           | Please enter value | 0          | 0         |
      | aa bb     | Please enter value | 2          | 0         |
      | aa bb     | Please enter value | 0          | 2         |
      | aa bb     | Please enter value | 2          | 2         |

  Scenario: Test correct name group
    When I enter correct name group with text is "Android english"
    Then Hide name of group message error

  Scenario: I selected a leader in list employee but not fill name group, button Submit is unable
    When I open dropdown Leader
    And I select a item in employee list
    Then Button submit of new groups is unable

  Scenario Outline: Check fill email invalid, button submit is unable and message error is showed
    When I enter email of group in inputEmail with "<email>" with first space is "<firstSpace>" and last space is "<lastSpace>"
    Then Button submit of new groups is unable
    And Message error validate email of new group is displayed
    Examples:
      | email               | firstSpace | lastSpace |
      | email @gmail.com    | 0          | 0         |
      | email abc@gmail.com | 0          | 0         |
      | email.@gmail.com    | 0          | 0         |
      | email@gmail         | 0          | 0         |
      | email@gmail.com1    | 0          | 0         |
      | email@gmail. com    | 0          | 0         |
      | email@gmail.com     | 1          | 0         |
      | email@gmail.com     | 0          | 1         |

  Scenario Outline: Check fill Group Folder invalid, button submit is unable and message error is showed
    When I enter Group Folder of group in input value with "<groupFolder>" with first space is "<firstSpace>" and last space is "<lastSpace>"
    Then Button submit of new groups is unable
    And Message error validate groupFolder of new group is displayed
    Examples:
      | groupFolder       | firstSpace | lastSpace |
      |                   | 0          | 0         |
      | google.com        | 0          | 0         |
      | http:google.com   | 0          | 0         |
      | http://google.com | 1          | 0         |
      | http://google.com | 0          | 3         |

  Scenario: I fill all data is valid, button Submit is enable
    When I enter correct name group with text is "Android english"
    And I open dropdown Leader
    And I select a item in employee list
    And I enter email of group in inputEmail with "phuong.dang@asiantech.vn" with first space is "0" and last space is "0"
    And I enter Group Folder of group in input value with "http://google.com" with first space is "0" and last space is "0"
    Then Button submit of new groups is display

  Scenario: I fill all data is valid, button Submit is enable
    When I enter correct name group with text is "Android english"
    And I open dropdown Leader
    And I select a item in employee list
    And I enter email of group in inputEmail with "phuong.dang@asiantech.vn" with first space is "0" and last space is "0"
    And I enter Group Folder of group in input value with "http://google.com" with first space is "0" and last space is "0"
    Then Button submit of new groups is display
    When I click on button submit of new group
    Then I should see fail or success message of new group
