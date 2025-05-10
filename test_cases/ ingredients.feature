Feature: Track available ingredients and suggest restocking
 Scenario: Track stock levels in real time
   Given I have logged as kitchen manager
   And We have the following ingredients
     | name       | quantity | minquantity |
     | Flour      | 10       | 5           |
     | Sugar      | 8        | 5           |
     | Cheese     | 3        | 5           |
     | Eggs       | 12       | 10          |
     | Milk       | 6        | 4           |
     | Butter     | 2        | 3           |
     | Chicken    | 7        | 5           |
     | Rice       | 20       | 10          |
     | Tomatoes   | 4        | 5           |
     | Lettuce    | 9        | 5           |
   When I view the current stock levels
   Then I should see the quantities of all ingredients displayed correctly

   Scenario:  suggest restocking when ingredients are low
     Given I have logged as kitchen manager
     And We have the following ingredients
       | name       | quantity | minquantity |
       | Flour      | 10       | 5           |
       | Sugar      | 8        | 5           |
       | Cheese     | 3        | 5           |
       | Eggs       | 12       | 10          |
       | Milk       | 6        | 4           |
       | Butter     | 2        | 3           |
       | Chicken    | 7        | 5           |
       | Rice       | 20       | 10          |
       | Tomatoes   | 4        | 5           |
       | Lettuce    | 9        | 5           |
     When the system check for low stock
     Then it should suggest restocking for the following ingredients
      | Cheese   |
      | Butter   |
      | Tomatoes |

  Scenario: Add new ingredients to inventory
    Given I have logged as kitchen manager
    And We have the following ingredients
      | name       | quantity | minquantity |
      | Flour      | 10       | 5           |
      | Sugar      | 8        | 5           |
      | Cheese     | 3        | 5           |
      | Eggs       | 12       | 10          |
      | Milk       | 6        | 4           |
      | Butter     | 2        | 3           |
      | Chicken    | 7        | 5           |
      | Rice       | 20       | 10          |
      | Tomatoes   | 4        | 5           |
      | Lettuce    | 9        | 5           |
    When I add the following new ingredients
      | name      | quantity | minquantity |
      | cucumber  | 6        | 3           |
      | Olive     | 12       | 5           |
    Then I should see the new ingredients added to the inventory
    And total ingredients count should be 12

  Scenario: Update ingredient quantity
    Given I have logged as kitchen manager
    And We have the following ingredients
      | name       | quantity | minquantity |
      | Flour      | 10       | 5           |
      | Sugar      | 8        | 5           |
      | Cheese     | 3        | 5           |
      | Eggs       | 12       | 10          |
      | Milk       | 6        | 4           |
      | Butter     | 2        | 3           |
      | Chicken    | 7        | 5           |
      | Rice       | 20       | 10          |
      | Tomatoes   | 4        | 5           |
      | Lettuce    | 9        | 5           |
    When I update the quantity of ingredient
      | name       | quantity |
      | Flour      | 15       |
      | Eggs       | 20       |
      | Milk       | 7        |

    Then I should see the following ingredient quantities
      | name       | quantity | minquantity |
      | Flour      | 15       | 5           |
      | Sugar      | 8        | 5           |
      | Cheese     | 3        | 5           |
      | Eggs       | 20       | 10          |
      | Milk       | 7        | 4           |
      | Butter     | 2        | 3           |
      | Chicken    | 7        | 5           |
      | Rice       | 20       | 10          |
      | Tomatoes   | 4        | 5           |
      | Lettuce    | 9        | 5           |