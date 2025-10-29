package com.unifiedautomation.stepdefinitions;

import com.unifiedautomation.clients.ApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class ApiStepDefinitions {
    private Response response;
    private final ApiClient apiClient = new ApiClient();

    @Given("I call the API endpoint {string}")
    public void iCallTheApiEndpoint(String endpoint) {
        response = apiClient.get(endpoint);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }
}
