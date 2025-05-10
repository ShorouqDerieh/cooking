package org.example.test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class notificationSteps {
    NewCustomer cust;
    meal me;
    List<meal> meals;
    boolean sent;
    Chef cheff;
    //chefService service;
    ArrayList<Chef> Chefs;
    EmailService service;
    @Given("i am a customer and my name is {string} and my email is {string}")
    public void iAmACustomerAndMyNameIsAndMyEmailIs(String arg0, String arg1) {
        cust = new NewCustomer(arg0, arg1);
    }
    @And("i have ordered meal with a delievary time {string} minutes from now")
    public void iHaveOrderedMealWithADelievaryTimeMinutesFromNow(String arg0) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse(arg0, formatter);
        meals=new ArrayList<>();
        me=new meal(cust,time,false);
        meals.add(me);
    }

    @When("the system checks for upcoming deliveries")
    public void theSystemChecksForUpcomingDeliveries() {
        Reminder reminder=new Reminder(meals);
        sent=reminder.check();
    }

    @Then("i receive a reminder for my meal via email")
    public void iReceiveAReminderForMyMealViaEmail() {
        assertTrue("The email should sent",sent);
    }

    @Given("i am a chef and my name is {string} and my email is {string}")
    public void iAmAChefAndMyNameIsAndMyEmailIs(String arg0, String arg1) {
        cheff=new Chef(arg0,"Baking",1);
        cheff.setEmail(arg1);
        Chefs =new ArrayList<>();
        Chefs.add(cheff);

    }

    @When("the kitchen manager assigned meal to me at {string}")
    public void theKitchenManagerAssignedMealToMeAt(String arg0) {
        ChefService.addTask(Chefs,"Baking");
        service=new EmailService();
    }

    @Then("i receive a notification for my task via email")
    public void iReceiveANotificationForMyTaskViaEmail() {
        service.sendEmail(cheff.getEmail(), "Task Reminder!!","Hello Chef "+cheff.getName()+
                " you have a new meal to prepare");

    }

    @And("my meal has already been delivered")
    public void myMealHasAlreadyBeenDelivered() {
        me.setDelivered(true);
    }

    @Then("I should not receive a reminder email for my meal")
    public void iShouldNotReceiveAReminderEmailForMyMeal() {
        assertFalse("No reminder should be sent", sent);
    }
}
