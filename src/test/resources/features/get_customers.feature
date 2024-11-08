# Author: equipocalidad
# language: en

Feature: As a customer, I need to get the information of the store's customers

  Scenario: Get information about all customers successfully
    Given I am connect to capacities of the service
    When I get the information of the store
    Then I can see all information about the customers
    And The get response status code should be 200

  Scenario: Get information of a specific customer by ID
    Given I am connect to capacities of the service
    When I get the information of the customer with ID 1
    Then I can see the customer's name as "Jane Doe"
    And The get response status code should be 200

  Scenario: Attempt to get information of a non-existing customer
    Given I am connect to capacities of the service
    When I get the information of the customer with ID 999
    Then The get response status code should be 404
    And The get response should contain an error message "Error: El cliente con ID 999 no existe."