
Feature: Track past orders

  Scenario: Customer sees past orders
    Given the customer is identified in the system
    When the customer requests to see past orders
    Then the system displays the list of past meal orders in the console
    And the customer can choose to reorder meals from the console list

  Scenario: Chef reviews past orders
    Given the system has a list of customers and their past orders in memory
    When the chef requests the order history for a specific customer
    Then the system shows the customer’s past orders in the console
    And the chef can suggest personalized meal plans based on the orders

  Scenario: Admin reviews all orders
    Given the system stores customer orders in memory (e.g., in a list or array)
    When the administrator requests all customer order histories
    Then the system displays all order histories in the console
    And the administrator can analyze the data manually to improve service
