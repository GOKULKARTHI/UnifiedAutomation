package com.unifiedautomation.runners;

import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@Cucumber
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.unifiedautomation.stepdefinitions,com.unifiedautomation.hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html, json:target/cucumber-reports.json, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
@ConfigurationParameter(key = EXECUTION_MODE_PROPERTY_NAME, value = "CONCURRENT")
@ConfigurationParameter(key = PARALLEL_CONFIG_FIXED_THREAD_COUNT_PROPERTY_NAME, value = "2")

@Cucumber
public class RunCucumberTest {
    // JUnit 5 will discover and run Cucumber features
}
