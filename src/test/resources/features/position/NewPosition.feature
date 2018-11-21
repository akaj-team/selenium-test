@NewPosition
Feature:  Login my account to portal and open positions page
  I want to check new position page

  Background: User navigation to new position page
    Given I am logged in as a team manager
    And Displayed new position page

  Scenario: Test click button position
    Given I click on button position
    Then Displayed positions page

  Scenario Outline: Test long name fail
    Given I enter long name field with text is "<longName>" and first space is "<firstSpace>" and last space is "<lastSpace>"
    Then Show long name message error with text "<message>"
    And Short name text is "<shortName>"
    Examples:
      | longName | message            | shortName | firstSpace | lastSpace |
      |          | Please enter value |           | 0          | 0         |
      | aa bb    | Please enter value | AB        | 2          | 0         |
      | aa bb    | Please enter value | AB        | 0          | 2         |
      | aa bb    | Please enter value | AB        | 2          | 2         |

  Scenario: Test correct long name
    Given I enter correct long name with text is "Nguyen Mau Hanh"
    Then Short name text is "NMH"
    And Hide long name message error

  Scenario Outline: Test short name fail
    Given I enter short name field with text is "<shortName>" and first space is "<firstSpace>" and last space is "<lastSpace>"
    Then Show short name message error with text "<message>"
    Examples:
      | shortName | message            | firstSpace | lastSpace |
      |           | Please enter value | 0          | 0         |
      | aa bb     | Please enter value | 2          | 0         |
      | aa bb     | Please enter value | 0          | 2         |
      | aa bb     | Please enter value | 2          | 2         |

  Scenario: Check hint text in short name
    When Short name empty
    Then Short name hint text is "Short name is converted from long name"

  Scenario: Test create new position
    Given I enter correct long name with text is "HHHHHHHH"
    And I click first position on list position
    Then Displayed button save
    When I click button save position
    Then Redirect to position detail or show error massage

  Scenario: Test dialog confirmation when click stay
    Given I enter correct long name with text is "HHHHHHHH"
    And I click first position on list position
    When I click on button position
    Then Should show dialog exits confirmation
    When I click button stay
    Then Hide confirmation dialog

  Scenario: Test dialog confirmation when click leave
    Given I enter correct long name with text is "HHHHHHHH"
    And I click first position on list position
    When I click on button position
    Then Should show dialog exits confirmation
    When I click button leave
    Then Should redirect to position page
