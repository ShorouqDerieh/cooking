Feature: Generate invoices and track financial reports
  Scenario: customer  receive an invoice
    Given I am a customer and my name is "Shorouq"
    And i purchase these items
        | Pizza   |
        | Lasagna |
    And the total price is 75.0
    When i request invoice
    Then the system should generate an invoice and display it
