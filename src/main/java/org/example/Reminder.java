package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Reminder {
List<meal> orders;
public Reminder(List<meal> orders) {
    this.orders = orders;
}
public boolean check(){
    LocalTime time=LocalTime.now();
    for(meal meal:orders){
        long duration= Duration.between(time,meal.delievryTime).toMinutes();
        if(duration<=15&&!meal.delivered){
            EmailService send=new EmailService();
            String email=meal.cus.getEmail();
            String name=meal.cus.getName();
            send.sendEmail(email,"Your Meal","Hello "+name+" your meal will arrive in less than 15 minutes. Please be ready!");
            return true;
        }
    }
    return false;
}
}
