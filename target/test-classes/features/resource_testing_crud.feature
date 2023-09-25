@active
Feature: Resource testing CRUD

  Scenario: Get the List of resources
    Given there are registered resources in the system
    When I send a GET request to view all the resources
    Then the response should have a status code of 200
    And validates the response with the resource list JSON schema