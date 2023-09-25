package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.requests.ClientRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.List;


/**
 * this class defines the steps implemented in the client crud feature
 */
public class ClientSteps {
    private static final Logger logger = LogManager.getLogger(ClientSteps.class);
    private Response response;
    private final ClientRequest clientRequest = new ClientRequest();
    private Client   client;

    @Given("there are registered clients in the system")
    public void thereAreRegisteredClientsInTheSystem() {
        response = clientRequest.getClients();
        logger.info("Response: "+response.jsonPath().prettify());
        Assert.assertEquals(200, response.statusCode());
        List<Client> clients = clientRequest.getClientsEntity(response);
        if(clients.isEmpty()) {
            response = clientRequest.createDefaultClient();
            logger.info(response.statusCode());
            Assert.assertEquals(201, response.statusCode());
        }
    }

    @Given("I have a client with the following details:")
    public void iHaveAClientWithTheFollowingDetails(DataTable clientData) {
        logger.info("I have a client with the following details:" + clientData);
    }

    @When("I retrieve the details of the client with ID {string}")
    public void sendGETRequest(String clientId) {
        logger.info("I retrieve the details of the client with ID " + clientId);
    }

    /**
     * this function performs the get request of all clients
     */
    @When("I send a GET request to view all the clients")
    public void iSendAGETRequestToViewAllTheClient() {
        logger.info("I send a GET request to view all the clients");
        response = clientRequest.getClients();
    }

    @When("I send a POST request to create a client")
    public void iSendAPOSTRequestToCreateAClient() {
        logger.info("I send a POST request to create a client");
    }

    @When("I send a DELETE request to delete the client with ID {string}")
    public void iSendADELETERequestToDeleteTheClientWithID(String clientId) {
        logger.info("I send a DELETE request to delete the client with ID " + clientId);
    }

    @When("I send a PUT request to update the client with ID {string}")
    public void iSendAPUTRequestToUpdateTheClientWithID(String clientId, String requestBody) {
        logger.info("I send a PUT request to update the client with ID " + requestBody + clientId);
    }

    /**
     * This function runs the status code assertion
     * @param statusCode
     */
    @And("the response should have a status code of {int}")
    public void theResponseShouldHaveAStatusCodeOf(int statusCode) {
        logger.info("the response should have a status code of " + statusCode);
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("the response should have the following details:")
    public void theResponseShouldHaveTheFollowingDetails(DataTable expectedData) {
        logger.info("the response should have the following details:" + expectedData);
    }

    @Then("the response should include the details of the created client")
    public void theResponseShouldIncludeTheDetailsOfTheCreatedClient() {
        logger.info("the response should include the details of the created client");
    }

    @Then("validates the response with client JSON schema")
    public void userValidatesResponseWithClientJSONSchema() {
        logger.info("validates the response with client JSON schema");
    }

    /**
     * Runs the assertion to validate the response with client list JSON schema
     */
    @And("validates the response with the client list JSON schema")
    public void userValidatesResponseWithClientListJSONSchema() {
        logger.info("validates the response with client list JSON schema");
        Assert.assertTrue(clientRequest.validateSchema(response, "schemas/clientListSchema.json"));
    }
}
