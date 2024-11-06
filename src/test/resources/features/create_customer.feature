# Author: equipocalidad
# language: en

  Feature: As a user, I need to create a customer in the store's customer service

    Scenario: create a new customer successfully
      Given I am connected to the customer service
      When I create a customer
      Then the response body should contain the created customer's ID
      And I should see a response status code 201
