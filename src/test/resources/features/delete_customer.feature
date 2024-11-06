# Author: equipocalidad
# language: en

  Feature: As a user, I need to delete a customer in the store's customer service

    Scenario: delete an existing customer successfully
      Given I am connected to the customer service 2
      When I delete a customer with an existing id
      Then the response status code should be 204