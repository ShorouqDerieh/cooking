package org.example.test;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.*;

import org.example.*;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static org.junit.Assert.*;

public class TrackPastOrders {
    private TrackPastOrdersSystem system;
    private Customer customer;
    @Before
    public void setUp() {
        system = new TrackPastOrdersSystem();
    }
    @Given("the system has a list of customers and their past orders in memory")
    public void theSystemHasAListOfCustomersAndTheirPastOrdersInMemory() {
        system = new TrackPastOrdersSystem();
    }

    @Given("the system stores customer orders in memory \\(e.g., in a list or array)")
    public void theSystemStoresCustomerOrdersInMemoryEGInAListOrArray() {
        system = new TrackPastOrdersSystem();
    }

    @Given("the customer is identified in the system")
    public void theCustomerIsIdentifiedInTheSystem() {
        customer = system.getCustomerById("1");  // test id "1" (hiba)
        assertNotNull("Customer should be found", customer);
    }

    @When("the chef requests the order history for a specific customer")
    public void theChefRequestsTheOrderHistoryForASpecificCustomer() {
        customer = system.getCustomerById("2");  // test id "2" (ali)
        assertNotNull("Chef should find the customer", customer);
        //  system.displayPastOrders(customer);
    }

    @When("the customer requests to see past orders")
    public void theCustomerRequestsToSeePastOrders() {
        assertNotNull("Customer should not be null before displaying orders", customer);
        system.displayPastOrders(customer);
    }

    @When("the administrator requests all customer order histories")
    public void theAdministratorRequestsAllCustomerOrderHistories() {
        system.displayAllOrderHistories();
    }

    @Then("the system displays the list of past meal orders in the console")
    public void theSystemDisplaysTheListOfPastMealOrdersInTheConsole() {
        assertFalse("Customer should have past orders", customer.getOrders().isEmpty());
    }

    @Then("the customer can choose to reorder meals from the console list")
    public void theCustomerCanChooseToReorderMealsFromTheConsoleList() {
        system.reorderFirstMeal(customer);
    }

    @Then("the system shows the customer’s past orders in the console")
    public void theSystemShowsTheCustomerSPastOrdersInTheConsole() {
        assertFalse("Customer should have past orders", customer.getOrders().isEmpty());
    }

    @Then("the chef can suggest personalized meal plans based on the orders")
    public void theChefCanSuggestPersonalizedMealPlansBasedOnTheOrders() {
        system.suggestMealPlans(customer);
    }

    @Then("the system displays all order histories in the console")
    public void theSystemDisplaysAllOrderHistoriesInTheConsole() {
        system.displayAllOrderHistories();
    }

    @Then("the administrator can analyze the data manually to improve service")
    public void theAdministratorCanAnalyzeTheDataManuallyToImproveService() {
        system.analyzeData();
    }
}