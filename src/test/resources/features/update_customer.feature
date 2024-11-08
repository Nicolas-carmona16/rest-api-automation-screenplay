# Author: equipocalidad
# language: en

Feature: As a user, I need to update a customer in the store's customer service

  Scenario: Update an existing customer successfully
    Given I am connected to the service
    When I update a customer with ID 1 with the following data:
      | name       | email               | phoneNumber |
      | Jane Doe   | jane.doe@example.com| 0987654321  |
    Then The update response status code should be 200
    And The response should contain the updated customer data

  Scenario: Attempt to update a non-existing customer
    Given I am connected to the service
    When I update a customer with ID 9999 with the following data:
      | name       | email               | phoneNumber |
      | John Doe   | john@example.com    | 1234567890  |
    Then The update response status code should be 404
    And The update response should contain an error message "Error: El cliente con ID 9999 no existe."