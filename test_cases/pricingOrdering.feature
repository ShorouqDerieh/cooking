Feature:  Integrate with suppliers for real-time pricing and ordering
  Scenario: Display price of ingredient from many suppliers
    Given I am the kitchen manager
    And system has a list of ingredients prices from vary suppliers
    When i requests the price of "Cucumber"
    Then the system should display "Cucumber" price from each supplier

  Scenario: Kitchen manager make cost-effective purchasing decisions
    Given I am the kitchen manager
    And system has a list of ingredients prices from vary suppliers
    When i request to order 10 of "Cucumber"
    Then the system should order from cheapest supplier

  Scenario: automatically generate purchase orders when stock levels are critically low
    Given I am a system
    And the stock of "Onion" is 4
    And the minimum quantity for "Onion" is 10
    And system has a list of ingredients prices from vary suppliers
    When i check stock levels
    Then i should automatically order 20 "Onion"
    And the quantity of "Onion" increased by 20

  Scenario: Try ordering an ingredient not supplied by any supplier
    Given I am a system
    And the stock of "Tomato" is 2
    And the minimum quantity for "Tomato" is 10
    And system has a list of ingredients prices from vary suppliers
    When i check stock levels
    And i check for supplier availability for "Tomato"
    Then system should notify no available supplier for "Tomato"
