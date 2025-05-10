Feature: Generate invoices and track financial reports
  Scenario: customer  receive an invoice
    Given I am a customer and my name is "Shorouq"
    And i purchase these items
        | Pizza   |
        | Lasagna |
    And the total price is 75.0
    When i request invoice
    Then the system should generate an invoice and display it

    Scenario: System administrator generate financial reports
      Given i am a system adminstrator
      And  i select the month of "May 2025"
      And there are recorded sales data for that month
      When I generate the monthly financial report
      Then a PDF report should be generated containing:
        | Section                    | Description                                           |
        | System Name                | Special Cook System                                   |
        | Report Date                | Current system date                                   |
        | Selected Month             | May 2025                                              |
        | Product Sales Table        | Includes product name, quantity sold, total revenue   |
        | Total Number of Invoices   | Displays how many invoices were generated             |

    Scenario: Generate PDF report for a month with no sales
      Given i am a system adminstrator
      And  i select the month of "April 2025"
      When I generate the monthly financial report
      Then no PDF report should be generated
