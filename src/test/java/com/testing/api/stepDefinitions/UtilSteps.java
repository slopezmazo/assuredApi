package com.testing.api.stepDefinitions;

import io.cucumber.java.en.And;
import org.junit.Assert;

public class UtilSteps extends BaseStepDefinition{
    /**
     * This function runs the status code assertion
     * @param statusCode
     */
    @And("the response should have a status code of {int}")
    public void theResponseShouldHaveAStatusCodeOf(int statusCode) {
        logger.info("the response should have a status code of " + statusCode);
        Assert.assertEquals(statusCode, response.statusCode());
    }
}
