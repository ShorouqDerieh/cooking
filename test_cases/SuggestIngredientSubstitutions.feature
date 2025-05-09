Feature: Suggest ingredient substitutions

  Scenario: Substitute for lobster
    Then the system should suggest substitute "shrimp" for "lobster"

  Scenario: Substitute for chicken
    Then the system should suggest substitute "tofu" for "chicken"

  Scenario: Error message for unavailable ingredient
    Then the system should show an error message "Truffle is currently unavailable and no substitute is available"

  Scenario: Chef receives alert for substitution approval
    Then the chef should receive an alert for substitution approval

  Scenario: No substitute available
    Given no substitute is available for "truffle"
    Then the system should show an error message "Truffle is currently unavailable and no substitute is available"
