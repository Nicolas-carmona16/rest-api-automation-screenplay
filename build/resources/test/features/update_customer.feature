# Author: equipocalidad
# language: en

Feature: As a user, I need to update a customer in the store's customer service

  Scenario: update an existing customer successfully
    Given I am connected to the service
    When I update a customer with an existing id
    Then The response status code should be 200
    And The response should contain the updated customer data

