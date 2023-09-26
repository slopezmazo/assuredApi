package com.testing.api.stepDefinitions;

import com.testing.api.models.Resource;
import com.testing.api.requests.ResourceRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ResourceSteps extends BaseStepDefinition{
    private static final ResourceRequest resourceRequest = new ResourceRequest();
    private Resource resource;
    private Resource lastResource;
    private static Map<String, String> resourceDataMap;

    /**
     * Ensures there are registered resources in the system
     */
    @Given("there are registered resources in the system")
    public void thereAreRegisteredResourcesInTheSystem() {
        response = resourceRequest.getResources();
        logger.info(response.jsonPath().prettify());
        Assert.assertEquals(200, response.statusCode());
        List<Resource> resources = resourceRequest.getResourcesEntity(response);
        if(resources.isEmpty()) {
            response = resourceRequest.createDefaultResource();
            logger.info(response.statusCode());
            Assert.assertEquals(201, response.statusCode());
        }
    }
    /**
     * Performs the get request
     */
    @When("I send a GET request to view all the resources")
    public void iSendAGETRequestToViewAllTheResources() {
        response = resourceRequest.getResources();
    }

    /**
     * Ensure the resource list response fits with the schema
     */
    @And("validates the response with the resource list JSON schema")
    public void validatesTheResponseWithTheResourceListJSONSchema() {
        Assert.assertTrue(resourceRequest.validateSchema(response, "schemas/resourceListSchema.json"));
    }

    /**
     * asign the last resource of the response to the lastResource attribute
     */
    @And("I retrieve the details of the latest resource")
    public void iRetrieveTheDetailsOfTheLatestResource() {
        List<Resource> resources = resourceRequest.getResourcesEntity(response);
        lastResource = resources.get(resources.size()-1);
        logger.info("Last resource: \n" +lastResource);
    }

    /**
     * Sends the resource PUT request to the API
     */
    @When("I send a PUT request to update the latest resource")
    public void iSendAPUTRequestToUpdateTheLatestResource(String requestBody) {
        logger.info(requestBody);
        response = resourceRequest.updateResource(resourceRequest.getResourceEntity(requestBody),lastResource.getId());
        logger.info(response);
    }

    /**
     * Validate the details of the Resource response
     * @param resourceDataTable
     */
    @And("the response should have the following details:")
    public void theResponseShouldHaveTheFollowingDetails(DataTable resourceDataTable) {

        resourceDataMap = resourceDataTable.asMaps().get(0);
        resource = Resource.builder().name(resourceDataMap.get("name"))
                .trademark(resourceDataMap.get("trademark"))
                .stock(Integer.parseInt(resourceDataMap.get("stock")))
                .price(Double.valueOf(resourceDataMap.get("price")))
                .description(resourceDataMap.get("description"))
                .tags(resourceDataMap.get("tags"))
                .is_active(Boolean.valueOf(resourceDataMap.get("is_active")))
                .build();
        Resource resResource = resourceRequest.getResourceEntity(response);
        //Fields validations
        Assert.assertEquals(resResource.getName(), resource.getName());
        Assert.assertEquals(resResource.getTrademark(), resource.getTrademark());
        Assert.assertEquals(resResource.getStock(), resource.getStock());
        Assert.assertEquals(resResource.getPrice(), resource.getPrice());
        Assert.assertEquals(resResource.getDescription(), resource.getDescription());
        Assert.assertEquals(resResource.getTags(), resource.getTags());
        Assert.assertEquals(resResource.getIs_active(), resource.getIs_active());
        logger.info("Resource details assertion success");
    }

    /**
     * Function to validates that the response match with the resource schema
     */
    @And("validates the response with the resource JSON schema")
    public void validatesTheResponseWithTheResourceJSONSchema() {
        Assert.assertTrue(resourceRequest.validateSchema(response, "schemas/resourceSchema.json"));
        logger.info ("Resource matches with the schema");
    }
}
