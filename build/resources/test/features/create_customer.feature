# Author: equipocalidad
# language: en

  Feature: As a user, I need to create a customer in the store's customer service

    Scenario: create a new customer successfully
      Given I am connected to the customer service
      When I create a customer with the following data:
        | name       | email             | phoneNumber |
        | John Doe   | john@example.com  | 1234567890  |
      Then the response body should contain the created customer's ID
      And I should see a response status code 201

    Scenario: Attempt to create a customer with all fields empty
      Given I am connected to the customer service
      When I create a customer with the following data:
        | name | email | phoneNumber |
        |      |       |             |
      Then I should see a response status code 400
      And The response should contain an error message "All fields are required"