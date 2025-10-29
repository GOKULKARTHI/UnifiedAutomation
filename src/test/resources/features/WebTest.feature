Feature: Web Test

  @smoke
  Scenario: Open app and check title
    Given I open the application
    Then the title should be "Example Domain"

  @regression
  Scenario: Another web test
    Given I open the application
    Then the title should contain "Example"
