package com.unifiedautomation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class RetryHook implements TestExecutionExceptionHandler {
    private static final int MAX_RETRIES = 2;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        int retryCount = 0;
        while (retryCount < MAX_RETRIES) {
            try {
                // Retry the test
                context.getRequiredTestMethod().invoke(context.getRequiredTestInstance());
                return; // Success, exit
            } catch (Throwable t) {
                retryCount++;
                if (retryCount >= MAX_RETRIES) {
                    throw t; // Max retries reached, rethrow
                }
                System.out.println("Test failed, retrying... Attempt: " + retryCount);
            }
        }
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("Finished scenario: " + scenario.getName());
    }
}
