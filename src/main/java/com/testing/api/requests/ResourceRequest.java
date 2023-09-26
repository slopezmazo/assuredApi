package com.testing.api.requests;

import com.google.gson.Gson;
import com.testing.api.models.Resource;
import com.testing.api.utils.Constants;
import com.testing.api.utils.JsonFileReader;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class ResourceRequest extends BaseRequest {
    private String endpoint;

    /**
     * Function to retrieve resources from endpoint
     * @return : Response with resources in it
     */
    public Response getResources() {
        endpoint = String.format(Constants.URL, Constants.RESOURCE_PATH);
        return requestGet(endpoint, createBaseHeaders());
    }
    /**
     * Function to return the list of Resource
     * @param response
     * @return : List<Resource>
     */
    public List<Resource> getResourcesEntity(@NotNull Response response) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("", Resource.class);
    }

    /**
     * Performs a POST request to create a resource
     * @param resource
     * @return response
     */
    public Response createResource(Resource resource) {
        endpoint = String.format(Constants.URL, Constants.RESOURCE_PATH);
        return requestPost(endpoint, createBaseHeaders(), resource);
    }
    
    /**
     * Function to create a default resource from a jsonfile if there is not any resource in the system
     * @return Response
     */
    public Response createDefaultResource() {
        JsonFileReader jsonFile = new JsonFileReader();
        return this.createResource(jsonFile.getResourceByJson(Constants.DEFAULT_RESOURCE_FILE_PATH));
    }

    /**
     * this function performs the update request and provides the response
     * @param resource : Resource object
     * @param resourceId : Id of the object to update
     * @return : Response 200 if update was successful
     */
    public Response updateResource(Resource resource, String resourceId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.RESOURCE_PATH, resourceId);
        return requestPut(endpoint, createBaseHeaders(), resource);
    }
    /**
     * Function to convert a String json to Resource
     * @param resourceJson
     * @return Resource
     */
    public Resource getResourceEntity(String resourceJson) {
        Gson gson = new Gson();
        return gson.fromJson(resourceJson, Resource.class);
    }
    /**
     * this function converts response to Resource instance
     * @param response
     * @return resource
     */
    public Resource getResourceEntity(@NotNull Response response) {
        return response.as(Resource.class);
    }
}
