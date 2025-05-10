Feature: Assign Tasks to Chefs and Kitchen Staff

  Scenario: Kitchen manager assigns tasks to chefs
    Given I am a kitchen manager
    And there are available chefs with different expertise levels
    When i am assign a cooking task to a chef based on workload and expertise
    Then the task should be assigned to the most suitable chef
    And the chef should receive a notification about the assigned task

  Scenario Outline: No available chef with required expertise
    Given there are chefs with workloads and expertise
    And a new task requires "<expertise>"
    When I assign the new task
    Then the system should notify that no available chef has "<expertise>"  expertise

    Examples:
      | expertise |
      | Baking    |
      | Sushi     |
      | Pizza     |

  Scenario Outline: Distribute tasks equally among chefs with same expertise
    Given there are two chefs with "<expertise>" expertise and same workload
    And a new task requires "<expertise>"
    When I assign the new task
    Then the task should be assigned to any of the available chefs
    And the chosen chef should receive a notification

    Examples:
      | expertise |
      | Baking    |
      | Sushi     |
      | Pizza     |

