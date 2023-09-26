@active
Feature: Resource testing CRUD

  Scenario: Get the List of resources
    Given there are registered resources in the system
    When I send a GET request to view all the resources
    Then the response should have a status code of 200
    And validates the response with the resource list JSON schema


  Scenario: Update the Last Resource
    Given there are registered resources in the system
    And I retrieve the details of the latest resource
    When I send a PUT request to update the latest resource
    """
    {
    "name": "Basketbal ball",
    "trademark": "Wilson",
    "stock": 98765,
    "price": 10000,
    "description":"NBA basketball and movie actor",
    "tags": "basket",
    "is_active": true
    }
    """
    Then the response should have a status code of 200
    And the response should have the following details:
      | name           | trademark | stock | price | description                    | tags   | is_active |
      | Basketbal ball | Wilson    | 98765 | 10000 | NBA basketball and movie actor | basket | true      |
    And validates the response with the resource JSON schema