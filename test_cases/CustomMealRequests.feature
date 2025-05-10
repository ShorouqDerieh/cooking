Feature: Custom Meal Requests

  Scenario: Successfully create a custom meal with available and compatible ingredients
    Given the customer is on the custom meal option
    When the customer selects ingredients "chicken", "broccoli", and "brown rice"
    And the customer submits the custom meal request
    Then the system should confirm the custom meal is created successfully


  Scenario: Attempt to create a custom meal with unavailable ingredients
    Given the customer is on the custom meal option
    When the customer selects ingredients "lobster", "truffle", and "spinach"
    And "lobster" is currently unavailable
    And the customer submits the custom meal request
    Then the system should show an error message "Lobster is currently unavailable"

  Scenario: Attempt to create a custom meal with incompatible ingredients
    Given the customer is on the custom meal option
    When the customer selects ingredients "salmon" and "chocolate sauce"
    And the customer submits the custom meal request
    Then the system should show an error message "Salmon and chocolate sauce cannot be combined"

  Scenario: View updated ingredient availability after a custom meal submission
    Given the customer is on the custom meal option
    And "chicken" has limited stock
    When the customer successfully submits a custom meal with "chicken"
    Then the system should update the available stock for "chicken"

  Scenario: Save dietary preferences for future custom meals
    Given the customer has set dietary preferences "gluten-free"
    When the customer opens the custom meal page
    Then the system should only display gluten-free compatible ingredients