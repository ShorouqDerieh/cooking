Feature: Assign Tasks to Chefs and Kitchen Staff

  Scenario: Kitchen manager assigns tasks to chefs
    Given I am a kitchen manager
    And there are available chefs with different expertise levels
    When i am assign a cooking task to a chef based on workload and expertise
    Then the task should be assigned to the most suitable chef
    And the chef should receive a notification about the assigned task


