@NewEmployeePersonalInformation
Feature: Check display views and handle events with personal information
  Login my account and open new employee page
  Check display views and handle events

  Background: User navigates to leave planner page
    Given I am logged in as an "QCE"
    And Display new employee page

  Scenario: Check default display of views in new employee page
    Then Personal information tab is active
    Then Next button is not clickable
    Then Back button is not clickable
    Then Submit button is not clickable

  Scenario Outline: Fill imperative properties with validate data and next button is clickable
    When Fill First Name input with "<firstname>"
    And Fill Middle Name input with "<middlename>"
    And Fill Last Name input with "<lastname>"
    And Choose Gender check box
    And Choose nationality
    Then Next button is clickable

    Examples:
      | firstname | middlename | lastname |
      | A         | B          | C        |
      | D         | E          | F        |
      | G         | H          | S        |
      | G         | H          | S        |
      | K         | L          | M        |

  Scenario Outline: Check invalid data with first name input
    When Fill First Name input with "<firstname>", "<ahead space>" and "<behind space>"
    Then Error message of first name is displayed
    Examples:
      | firstname                                         | ahead space | behind space |
      | First name with number 123                        | 0           | 0            |
      | First name longer than thirty five characters     | 0           | 0            |
      | First name with special characters ~!@#$%^&*()><? | 0           | 0            |
      | First name with spaces at the end of data         | 0           | 1            |
      | First name with spaces at the head of data        | 1           | 0            |

  Scenario Outline: Check invalid data of middle name input
    When Fill Middle Name input with "<middlename>"
    And Click out of middle name input area
    And A red border of middle name is displayed
    Examples:
      | middlename                                    |
      | First name longer than thirty five characters |

  Scenario Outline: Check invalid data of last name input
    When Fill Last Name input with a "<lastname>", "<ahead space>" and "<behind space>"
    Then Error message of last name is displayed
    And A red border of last name is displayed
    Examples:
      | lastname                                          | ahead space | behind space |
      | First name with number 123                        | 0           | 0            |
      | First name longer than thirty five characters     | 0           | 0            |
      | First name with special characters ~!@#$%^&*()><? | 0           | 0            |
      | First name with spaces at the end of data         | 0           | 1            |
      | First name with spaces at the head of data        | 1           | 0            |

  Scenario: Check display of calendar form
    When Click to calendar input
    Then Calendar form is showed

  Scenario: Check display of data in calendar form and calendar input
    When Click to calendar input
    Then Calendar form is showed
    Then Data in calendar input displays correctly

  Scenario Outline: Check invalidate data of mobile input
    When Fill mobile input with "<mobile>"
    And Click out of mobile input area
    Then Error message "Please enter valid phone number" of mobile is displayed
    And  A red border of mobile input is displayed
    Examples:
      | mobile             |
      | 123456789123456789 |
      | 123456789abcdefgh  |
      | abcdefgh           |
      | 123456789!@#$%^    |

  Scenario Outline: Check invalidate data of telephone input
    When Fill telephone input with "<telephone>"
    And Click out of telephone input area
    Then Error message "Please enter valid phone number" of telephone is displayed
    And  A red border of telephone input is displayed
    Examples:
      | telephone          |
      | 123456789123456789 |
      | 123456789abcdefgh  |
      | abcdefgh           |
      | 123456789!@#$%^    |
