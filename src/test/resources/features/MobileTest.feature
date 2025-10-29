Feature: Mobile Application Testing

  @mobile
  Scenario: Launch mobile app and check screen
    Given I launch the mobile application
    Then the mobile screen should be visible
