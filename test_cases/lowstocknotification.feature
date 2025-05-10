Feature: Notify users of low-stock ingredients
  Scenario Outline: kitchen manager receive alerts when stock levels are low
    Given i am a kitchen manager my name is "Ali" and my email is "shorouq3822@gmail.com"
    And we have an ingredient "<ingredient>" with stock "<stock>"
    And each ingredient has minimum quantity "<Minquantity>"
    When the stock of "<ingredient>" is updated to "<newStock>"
    Then the system should send email to kitchen manager
    And the email should include ingredient name "<ingredient>"
    And the current quantity "<newStock>"
    And the minimum quantity "<Minquantity>"

    Examples:
      | ingredient | stock | Minquantity | newStock |
      | Tomatoes   | 7     | 6           | 5        |
      | Cheese     | 4     | 5           | 3        |
      | Chicken    | 10    | 8           | 7        |

  Scenario: Send daily low-stock summary email
    Given i am a kitchen manager my name is "Ali" and my email is "shorouq3822@gmail.com"
    And we have the following ingredients:
      | ingredient | stock | Minquantity |
      | Tomatoes   | 3     | 5           |
      | Cheese     | 2     | 4           |
      | Chicken    | 7     | 10          |
    When the end of the business day is reached
    And there are ingredients with stock levels below their minimum quantities
    Then the system should send a single email to the kitchen manager
    And the email should include:
      | ingredient | stock        | Minquantity     |
      | Tomatoes   | 3            | 5               |
      | Cheese     | 2            | 4               |
      | Chicken    | 7            | 10              |
    And the email subject should be "Daily Low-Stock Summary"
    And the email should include a message like "The following ingredients are below their minimum stock levels:"

  Scenario: Notify kitchen manager about near-expiry products
    Given i am a kitchen manager my name is "Ali" and my email is "shorouq3822@gmail.com"
    And we have a product "Milk" with expiry date "2025-05-12"
    And we have a product "Yogurt" with expiry date "2025-05-11"
    And the system is configured to alert 3 days before expiry
    When today's date is "2025-05-09"
    Then the system should send an expiry alert email to the kitchen manager
    And the email subject should be "Near-Expiry Product Alert"
    And the email should includes:
      | product | expiryDate  |
      | Milk    | 2025-05-12  |
      | Yogurt  | 2025-05-11  |
    And the email should include a message like "The following products are approaching their expiry date:"
