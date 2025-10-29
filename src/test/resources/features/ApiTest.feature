Feature: API Test

  @api
  Scenario: Get user data
    Given I call the API endpoint "/users/1"
    Then the response status should be 200

  @api @regression
  Scenario: Another API test
    Given I call the API endpoint "/users/2"
    Then the response status should be 200
