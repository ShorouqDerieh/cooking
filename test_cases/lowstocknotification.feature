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

  Scenario:
