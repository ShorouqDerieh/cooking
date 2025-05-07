Feature:  Send reminders for upcoming orders and deliveries
  Scenario: customer receive reminders for upcoming meal deliveries
    Given i am a customer and my name is "Shorouq" and my email is "shorouq3822@gmail.com"
    And i have ordered meal with a delievary time "00:15:00" minutes from now
    When the system checks for upcoming deliveries
    Then i receive a reminder for my meal via email

  Scenario: chef notified of scheduled cooking tasks
    Given i am a chef and my name is "Ahmad" and my email is "shorouq3822@gmail.com"
     When the kitchen manager assigned meal to me at "11:30"
    Then i receive a notification for my task via email

  Scenario: System skips sending reminders for completed meal deliveries
    Given i am a customer and my name is "Shorouq" and my email is "shorouq3822@gmail.com"
    And i have ordered meal with a delievary time "00:15:00" minutes from now
    And my meal has already been delivered
    When the system checks for upcoming deliveries
    Then I should not receive a reminder email for my meal