package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.requests.ClientRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;


/**
 * this class defines the steps implemented in the client crud feature
 */
public class ClientSteps extends BaseStepDefinition{

    private final ClientRequest clientRequest = new ClientRequest();
    private Client client;
    private static Map<String, String> clientDataMap;
    private  Client responseClient;

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


    @When("I send a DELETE request to delete the client with ID {string}")
    public void iSendADELETERequestToDeleteTheClientWithID(String clientId) {
        logger.info("I send a DELETE request to delete the client with ID " + clientId);
    }

    @When("I send a PUT request to update the client with ID {string}")
    public void iSendAPUTRequestToUpdateTheClientWithID(String clientId, String requestBody) {
        logger.info("I send a PUT request to update the client with ID " + requestBody + clientId);
    }

    @Then("the response should have the following details:")
    public void theResponseShouldHaveTheFollowingDetails(DataTable expectedData) {
        logger.info("the response should have the following details:" + expectedData);
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

    /**
     * Creates the client instance according to the client data table
     * @param clientDataTable
     */
    @Given("I have a client with the following details:")
    public void iHaveAClientWithTheFollowingDetails(DataTable clientDataTable) {
        logger.info(clientDataTable);
        clientDataMap = clientDataTable.asMaps().get(0);

        client = Client.builder().name(clientDataMap.get("Name"))
                .lastName(clientDataMap.get("LastName"))
                .country(clientDataMap.get("Country"))
                .city(clientDataMap.get("City"))
                .email(clientDataMap.get("Email"))
                .phone(clientDataMap.get("Phone"))
                .build();
    }

    /**
     * Performs the POST request for client
     */
    @When("I send a POST request to create a client")
    public void iSendAPOSTRequestToCreateAClient() {
        logger.info("Client Created");
        response = clientRequest.createClient(client);
    }

    /**
     * Verifies the response include the details of the created client
     */
    @Then("the response should include the details of the created client")
    public void theResponseShouldIncludeTheDetailsOfTheCreatedClient() {
        logger.info("the response should include the details of the created client");
        // response to client
        responseClient = clientRequest.getClientEntity(response);

        // Compare attributes between client response and the client sent
        Assert.assertEquals(responseClient.getName(), client.getName());
        Assert.assertEquals(responseClient.getCity(), client.getCity());
        Assert.assertEquals(responseClient.getCountry(), client.getCountry());
        Assert.assertEquals(responseClient.getEmail(), client.getEmail());
        Assert.assertEquals(responseClient.getPhone(), client.getPhone());
        logger.info("The client sent and the client from the response are the same");
    }

    /**
     * Runs the assertion to verify if the response fits with the client schema
     */
    @And("validates the response with the client JSON schema")
    public void validatesTheResponseWithTheClientJSONSchema() {
        logger.info("validates the response with the client JSON schema");
        Assert.assertTrue(clientRequest.validateSchema(response, "schemas/clientSchema.json"));
    }
}
