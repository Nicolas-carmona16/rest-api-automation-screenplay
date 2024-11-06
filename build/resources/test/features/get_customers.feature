# Author: equipocalidad
# language: en

Feature: As a customer, I need to get all the customers registered in the store

  Scenario: get information about the store
    Given I am connect to capacities of the service
    When I get the information of the store
    Then I can see all information about the customers