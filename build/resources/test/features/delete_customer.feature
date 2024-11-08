# Author: equipocalidad
# language: en

  Feature: As a user, I need to delete a customer in the store's customer service

    Scenario: delete an existing customer successfully
      Given I am connected to the customer service 2
      When I delete a customer with id 2
      Then the response status code should be 204

    Scenario: Attempt to delete a non-existing customer
      Given I am connected to the customer service 2
      When I delete a customer with id 9999
      Then the response status code should be 404
      And The response should contain a message "Error: El cliente con ID 9999 no existe."