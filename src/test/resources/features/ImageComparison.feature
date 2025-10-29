Feature: Image Comparison

  Scenario: Compare screenshot with baseline
    Given I capture a screenshot
    Then the screenshot should match the baseline
