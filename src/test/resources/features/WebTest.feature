Feature: Web Test

  Scenario: Open app and check title
    Given I open the application
    Then the title should be "Example Domain"
