Feature: API Test

  Scenario: Get user data
    Given I call the API endpoint "/users/1"
    Then the response status should be 200
