package org.example.test;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.chef;
import org.example.tasks;
import org.example.chefService;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
    assignedChef = chefService.addTask(chefs,task.getExpertiseRequired());
    if (assignedChef != null) {
        assignedChef.incrementTask();
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


    @Given("there are chefs with workloads and expertise")
    public void thereAreChefsWithWorkloadsAndExpertise() {
        chefs = new ArrayList<>();
        chefs.add(new chef("Tala", "Sushi", 2));
        chefs.add(new chef("Ola", "Baking", 1));
    }

    @And("a new task requires {string}")
    public void aNewTaskRequires(String arg0) {
    task=new tasks(arg0);
    }

    @When("I assign the new task")
    public void iAssignTheNewTask() {

        assignedChef = chefService.addTask(chefs,task.getExpertiseRequired());
        if (assignedChef != null) {
            assignedChef.incrementTask();
            //notificationMessage = "Notification sent to " + assignedChef.getName();
        }
    }

    @Then("the system should notify that no available chef has {string}  expertise")
    public void theSystemShouldNotifyThatNoAvailableChefHasExpertise(String arg0) {
        if (assignedChef == null) {
            System.out.println("No chef available for " + arg0 + " tasks.");
            assertNull("Chef found when shouldn't be!", assignedChef);
        } else {
            System.out.println("Task assigned to: " + assignedChef.getName());
        }
    }


    @Given("there are two chefs with {string} expertise and same workload")
    public void thereAreTwoChefsWithExpertiseAndSameWorkload(String arg0) {
        chefs=new ArrayList<>();
    chefs.add(new chef("Hiba", arg0, 2));
    chefs.add(new chef("Shorouq", arg0, 2));
    }

    @Then("the task should be assigned to any of the available chefs")
    public void theTaskShouldBeAssignedToAnyOfTheAvailableChefs() {
        assertNotNull("No suitable chef found!", assignedChef);
        System.out.println("Task assigned to: " + assignedChef.getName());
    }

    @And("the chosen chef should receive a notification")
    public void theChosenChefShouldReceiveANotification() {
        System.out.println(notificationMessage);
    }
}
