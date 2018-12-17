@MenuPage
Feature: Check displaying and handing menu
  As menu page
  I want to click item on menu
  In order to check status item on menu

  Background: Check menu page
    Given I am logged in as a team manager

  Scenario:Login success with correct account
    When I should see the welcome message
    Then Show name of account
    And Account name is "Tien Hoang N."
    And "Home" Item changes color to "#ffffff"

  Scenario: Test click Home
    When I click "Home" item
    Then "Home" Item changes color to "#ffffff"
    And Should redirect to home page "/homepage"

  Scenario: Test click item TimeSheet
    Given "Timesheet" Item closes
    When I click "Timesheet" item
    Then Open Child "Timesheet" item

  Scenario Outline: Test click item My TimeSheet
    Given I click "Timesheet" item
    When I click "<title>" child item
    Then "<title>" child item changes color to "#ffffff"
    And Should redirect to page "<path>"
    Examples:
      | title               | path                  |
      | My Timesheet        | /timesheet/submission |
      | Timesheet of Others | /timesheet/approval   |

  Scenario: Test item Leave
    Given "Leave" Item closes
    When I click "Leave" item
    Then Open Child "Leave" item
    Then "Leave" Item changes color to "#ffffff"

  Scenario Outline: Test click child Leave item
    Given I click "Leave" item
    When I click "<title>" child item
    Then "<title>" child item changes color to "#ffffff"
    And Should redirect to page "<path>"
    Examples:
      | title           | path            |
      | My Leave        | /leave/my-leave |
      | Leave Planners  | /leave/planning |
      | Leave of Others | /leave/tracking |
      | Leave Balance   | /leave/balance  |
      | Leave Cancel    | /leave/cancel   |

  Scenario: Test item organisation
    Given "Organisation" Item closes
    When I click "Organisation" item
    Then Open Child "Organisation" item

  Scenario Outline: Test click child item organisation
    Given I click "Organisation" item
    When I click "<title>" child item
    Then "<title>" child item changes color to "#ffffff"
    And Should redirect to page "<path>"
    Examples:
      | title       | path                         |
      | Employees   | /organisation/employees      |
      | Teams       | /organisation/teams          |
      | Positions   | /organisation/positions      |
      | Career Path | /organisation/positions/tree |
      | Groups      | /organisation/groups         |

  Scenario: Test click child item my teams
    Given I click "Organisation" item
    When I click "My Teams" item
    When I click "Ruby" child item
    Then "Ruby" child item changes color to "#ffffff"
    And Should redirect to page "/organisation/teams/21"
    When I click "Android" child item
    Then "Android" child item changes color to "#ffffff"
    And Should redirect to page "/organisation/teams/24"

  Scenario Outline: Test click child item project management
    Given I click "Project Management" item
    When I click "<title>" child item
    Then "<title>" child item changes color to "#ffffff"
    And Should redirect to page "<path>"
    Examples:
      | title    | path                         |
      | Projects | /project-management/projects |

  Scenario: Test click item wiki
    When I click "Wiki" item
    Then Should redirect to page "/wiki/168"

  Scenario Outline: Test click item thanks message
    Given I click "Thanks Message" item
    When I click "<title>" child item
    Then "<title>" child item changes color to "#ffffff"
    And Should redirect to page "<path>"
    Examples:
      | title             | path                      |
      | New Message       | /thanks-message/new       |
      | Message Dashboard | /thanks-message/dashboard |
      | Frames            | /thanks-message/frames    |

  Scenario Outline: Test click item administration
    Given I click "Administration" item
    When I click "<title>" child item
    Then "<title>" child item changes color to "#ffffff"
    And Should redirect to page "<path>"
    Examples:
      | title                | path                  |
      | Access control       | /admin/acl            |
      | Holidays Setting     | /admin/public-holiday |
      | Email Configurations | /admin/email-settings |
      | Award Category       | /admin/award-category |

  Scenario Outline: Test click item device
    Given I click "Device" item
    When I click "<title>" child item
    Then "<title>" child item changes color to "#ffffff"
    And Should redirect to page "<path>"
    Examples:
      | title                    | path                          |
      | Device Tracking          | /equipments/tracking          |
      | Device Tracking Approval | /equipments/tracking-approval |
      | Device Dashboard         | /equipments/dashboard         |
      | Device List              | /equipments                   |
      | Device Assignment        | /equipments/assignments       |
      | Device Category          | /equipments/categories        |
      | Device Condition         | /equipments/broken-status     |
      | My Device                | /equipments/my-equipment      |

  Scenario Outline: Test click item tools
    Given I click "Tools" item
    When I click "<title>" child item
    Then "<title>" child item changes color to "#ffffff"
    And Should redirect to page "<path>"
    Examples:
      | title                  | path                             |
      | Announcements          | /tools/announcement              |
      | Email Signature        | /tools/email-signature           |
      | Attendance Record      | /tools/attendance-record         |
      | Project Alloc. Report  | /tools/project-report            |
      | Timesheet Report       | /tools/timesheet-report          |
      | Device Report          | /tools/equipment-report          |
      | Device Tracking Report | /tools/equipment-tracking-report |

  Scenario Outline: Test click item career
    Given I click "Career" item
    When I click "<title>" child item
    Then "<title>" child item changes color to "#ffffff"
    And Should redirect to page "<path>"
    Examples:
      | title               | path                  |
      | My Performance Plan | /goals/my-goal        |
      | P.Plan of Others    | /goals/goal-of-others |
