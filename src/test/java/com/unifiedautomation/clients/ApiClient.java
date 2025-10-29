package com.unifiedautomation.clients;

import com.unifiedautomation.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiClient {
    private static final Logger logger = LogManager.getLogger(ApiClient.class);
    private final String baseUrl;

    public ApiClient() {
        ConfigManager config = ConfigManager.getInstance();
        this.baseUrl = config.getString("apiBaseUrl", "https://jsonplaceholder.typicode.com");
        RestAssured.baseURI = this.baseUrl;
    }

    private RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all();
    }

    public Response get(String endpoint) {
        logger.info("Performing GET request to: {}", endpoint);
        Response response = getRequestSpec().get(endpoint);
        logger.info("Response: {}", response.asString());
        return response;
    }

    public Response post(String endpoint, Object body) {
        logger.info("Performing POST request to: {} with body: {}", endpoint, body);
        Response response = getRequestSpec().body(body).post(endpoint);
        logger.info("Response: {}", response.asString());
        return response;
    }

    public Response put(String endpoint, Object body) {
        logger.info("Performing PUT request to: {} with body: {}", endpoint, body);
        Response response = getRequestSpec().body(body).put(endpoint);
        logger.info("Response: {}", response.asString());
        return response;
    }

    public Response delete(String endpoint) {
        logger.info("Performing DELETE request to: {}", endpoint);
        Response response = getRequestSpec().delete(endpoint);
        logger.info("Response: {}", response.asString());
        return response;
    }
}
