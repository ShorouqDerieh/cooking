package org.example.test;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.chef;
import org.example.tasks;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class tasksSteps {
    ArrayList<chef> chefs;
   tasks task;
    chef assignedChef=null;
    String notificationMessage;
    @Given("I am a kitchen manager")
    public void i_am_a_kitchen_manager() {
       chefs=new ArrayList<>();
    }
@Given("there are available chefs with different expertise levels")
    public void availableChefs(){
    chefs.add(new chef("Shorouq", "Baking", 3));
    chefs.add(new chef("Hiba", "Sushi", 2));
    chefs.add(new chef("Ahmad", "Pizza", 1));
    chefs.add(new chef("Ali", "Baking", 0));
}
@When("i am assign a cooking task to a chef based on workload and expertise")
    public void assignTasks(){
    task=new tasks("Baking");
    chef best=null;
    for (chef c : chefs) {
        if (c.getExpertise().equals(task.getExpertiseRequired())) {
            if (best == null || c.getTaskCount() < best.getTaskCount()) {
                best = c;
            }
        }
    }
    assignedChef = best;
    if (assignedChef != null) {
        assignedChef.addTask();
        notificationMessage = "Notification sent to " + assignedChef.getName();
    }
}
@Then("the task should be assigned to the most suitable chef")
    public void assignTask() {
        assertNotNull("No suitable chef found!", assignedChef);
        System.out.println("Task assigned to: " + assignedChef.getName());
    }
@Then("the chef should receive a notification about the assigned task")
    public void receiveNotification() {
    System.out.println(notificationMessage);
}
}
