@EditEmployeesPage
Feature: Check display views and handle events with editing employee

  Background: User navigates to edit employees page
    Given I am logged in as a team manager
    And Display employees page
    When Click on edit action of an employee
    Then Open successfully edit employee page

    # At Personal Information and Company's Information Tab
  Scenario: Check data and display of views
    Given Click "Personal infomation" tab and this tab is active
    And Next button at edit employee page is clickable
    And Submit button at edit employee page is clickable
    And Deactivate button at edit employee page is clickable
    And Information of that employee displays correctly

  Scenario Outline: Check invalid data with first name input and submit button, next button, back button are not clickable
    When Fill First Name input with "<firstName>", "<aheadSpace>" and "<behindSpace>"
    Then Error message of first name is displayed
    And Submit button at edit employee page is not clickable
    And Next button at edit employee page is not clickable
    And Back button at edit employee page is not clickable
    Examples:
      | firstName                                         | aheadSpace | behindSpace |
      | First name with number 123                        | 0          | 0           |
      | First name longer than thirty five characters     | 0          | 0           |
      | First name with special characters ~!@#$%^&*()><? | 0          | 0           |
      | First name with spaces at the end of data         | 0          | 1           |
      | First name with spaces at the head of data        | 1          | 0           |

  Scenario: Handle editing employee successfully
    When Click submit button
    Then A confirm dialog displayed
    When Click submit button in this dialog
    Then Open successfully profile of this employee

     # Deactivate an employee
  Scenario: Deactivate unsuccessfully an employee with management roles
    When Click deactivate button
    Then A notification dialog is display
    When Click close button
    Then This deactivate dialog dismissed

  Scenario: Deactivate an employee without management roles
    When Click deactivate button
    Then A notification dialog is display
    When Choose day to deactivate this employee
    And Click deactivate button of dialog button
    Then Deactivate this employee

  Scenario: Deactivate unsuccessfully an employee without management roles
    When Click deactivate button
    Then A notification dialog is display
    When Click cancel button of dialog button
    Then Deactivate this employee

    # At Timeline Tab
  Scenario: Check display when at timeline tab
    When Click "Timeline" tab and this tab is active
    And Submit button is not displayed
    And Deactivate button is not displayed

  Scenario: Handle create new event with "Promotion - Position"
    Given Click "Timeline" tab and this tab is active
    When Click new event button
    Then A new event dialog displayed
    When Select type of event "Promotion - Position"
    And Select position and date of promotion
    And Click submit button of new event dialog
    Then This event is added correctly

  Scenario: Handle create new event with "Promotion - Employee Type"
    Given Click "Timeline" tab and this tab is active
    When Click new event button
    Then A new event dialog displayed
    When Select type of event "Promotion - Employee Type"
    And Select employee type and date of promotion
    And Click submit button of new event dialog
    Then This event is added correctly

  Scenario: Handle create new event with "Achievement - Award"
    Given Click "Timeline" tab and this tab is active
    When Click new event button
    Then A new event dialog displayed
    When Select type of event "Achievement - Award"
    And Select award type, date and fill praise for this award
    And Click submit button of new event dialog
    Then This event is added correctly

  Scenario: Handle editing new events
    Given Click "Timeline" tab and this tab is active
    When Click edit button of a new event
    Then A new event dialog displayed
    When Click submit button of new event dialog
    Then Edit successfully this new event

  Scenario: Handle deleting new events
    Given Click "Timeline" tab and this tab is active
    When Click delete button of a new event
    Then A confirm dialog displayed
    When Click delete button of this dialog
    Then Delete successfully this new event

      # At Information Contact Tab
  Scenario Outline: Handle adding successfully relative
    Given Click "Information Contact" tab and this tab is active
    When Click add relative button
    And Fill information contact with "Child-Parent" and name of relative with "Harry"
    And Fill phone of relative with valid data "<phone>"
    And Click save button
    Then A relative is created successfully
    Examples:
      | phone     |
      | 123456789 |
      | 347856627 |
      | 987654321 |
      | 000000000 |

  Scenario Outline: Handle adding unsuccessfully relative
    Given Click "Information Contact" tab and this tab is active
    When Click add relative button
    And Fill information contact with "Child-Parent" and name of relative with "Harry"
    And Fill phone of relative with invalid data "<phone>"
    Then A message error is displayed
    And Save button is not clickable
    Examples:
      | phone             |
      | abcdefghi         |
      | 12345678987654321 |
      | @#$$%%%^^&&*((_   |
      | abc123%&**        |

  Scenario: Handle deleting relatives
    Given Click "Information Contact" tab and this tab is active
    When Click delete button of a relative
    Then A confirm dialog displayed
    When Click remove button of this dialog
    Then Delete successfully this relative

      # At Attachments Tab
  Scenario: Check display of attachment tab
    When Click "Attachments" tab and this tab is active
