Feature: Customer Profile Management - Dietary Preferences and Allergies
  //1.1
  As a customer
  I want to input my dietary preferences and allergies
  So that the system can recommend appropriate meals and prevent unwanted ingredients

  Scenario: Customer enters dietary preferences and allergies
    Given I am a registered customer
    When I enter my dietary preferences as "vegetarian" and allergies as "peanuts"
    Then the system should save my preferences and allergies

  Scenario: System recommends meals based on dietary preferences and allergies
    Given I have entered my dietary preferences as "vegetarian" and allergies as "peanuts"
    When I search for meal recommendations
    Then the system should recommend only vegetarian meals that are free of peanuts

  Scenario: Chef views customer dietary preferences and allergies
    Given I am a chef
    And a customer has dietary preferences and allergies stored
    When I view the customer's profile
    Then I should see the customer's dietary preferences and allergies
    And I should customize the meal accordingly