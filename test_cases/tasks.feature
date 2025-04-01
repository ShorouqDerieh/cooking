Feature: Assign Tasks to Chefs and Kitchen Staff

  Scenario: Kitchen manager assigns tasks to chefs
    Given I am a kitchen manager
    And there are available chefs with different expertise levels
    When i am assign a cooking task to a chef based on workload and expertise
    Then the task should be assigned to the most suitable chef
    And the chef should receive a notification about the assigned task

  Scenario: Chef receives notification for assigned task
    Given I am a chef
    And the kitchen manager has assigned a cooking task to me
    When i am check my notifications
    Then i should see the new task in my task list
    And i should receive a notification with task details

