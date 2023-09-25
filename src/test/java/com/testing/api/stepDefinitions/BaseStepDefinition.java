package com.testing.api.stepDefinitions;

import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class BaseStepDefinition {
    protected static final Logger logger = LogManager.getLogger(ClientSteps.class);
    protected static Response response;
}
