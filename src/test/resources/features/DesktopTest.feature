Feature: Desktop Application Testing

  @desktop
  Scenario: Launch desktop app and check window
    Given I launch the desktop application
    Then the desktop window should be visible
