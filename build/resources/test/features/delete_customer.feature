# Author: equipocalidad
# language: en

  Feature: As a user, I need to delete a customer in the store's customer service

    Scenario: delete an existing customer successfully
      Given I am connected to the server
      When I delete a customer with id 7
      Then The delete response status code should be 204

    Scenario: Attempt to delete a non-existing customer
      Given I am connected to the server
      When I delete a customer with id 999
      Then The delete response status code should be 404
      And The delete response should contain an error message "Error: El cliente con ID 999 no existe."
