package org.example.test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PricingSteps {
    List<supplier> suppliers = new ArrayList<>();
    Map<String, Double> resultPrices;
    orderDisplay display;
    Map<String, Integer> stock;
    Map<String, Integer> minStock;
    orderDisplay orders;
    int actual=0;

    @Given("I am the kitchen manager")
    public void iAmTheKitchenManager() {
        resultPrices = new HashMap<>();
    }

    @And("system has a list of ingredients prices from vary suppliers")
    public void systemHasAListOfIngredientsPricesFromVarySuppliers() {
        supplier supplier1 = new supplier("supplier1");
        supplier1.addPrice("Cucumber", 10.0);
        supplier1.addPrice("Onion", 7.5);
     //  supplier1.addPrice("Tomato", 5.0);
        supplier supplier2 = new supplier("supplier2");
        supplier2.addPrice("Cucumber", 9.0);
        supplier2.addPrice("Onion", 8.0);
       // supplier2.addPrice("Tomato", 4.5);
        suppliers.add(supplier1);
        suppliers.add(supplier2);
    }

    @When("i requests the price of {string}")
    public void iRequestsThePriceOf(String arg0) {
        priceFetch fetcher=new priceFetch();
        resultPrices=fetcher.getPricesForIngredient(arg0,suppliers);
    }

    @Then("the system should display {string} price from each supplier")
    public void theSystemShouldDisplayPriceFromEachSupplier(String arg0) {
        System.out.println(arg0+" Prices from each supplier: ");
        for(String name:resultPrices.keySet()){//key set :supplier name
            System.out.println(name+" Price: "+resultPrices.get(name));//get name يعني بجيب قيمته الي عي السعر
        }

    }
    @When("i request to order {int} of {string}")
    public void iRequestToOrderOf(int arg0, String arg1) {
        orders order=new orders();
        display=order.makeOrder(arg1,suppliers,arg0);
    }


    @Then("the system should order from cheapest supplier")
    public void theSystemShouldOrderFromCheapestSupplier() {
        display.PrintOrder();

    }

    @Given("I am a system")
    public void iAmASystem() {
        stock=new HashMap<>();
        minStock=new HashMap<>();
    }

    @And("the stock of {string} is {int}")
    public void theStockOfIs(String arg0, int arg1) {
        stock.put(arg0,arg1);
    }

    @And("the minimum quantity for {string} is {int}")
    public void theMinimumQuantityForIs(String arg0, int arg1) {
        minStock.put(arg0,arg1);
    }

    @When("i check stock levels")
    public void iCheckStockLevels() {
    for(String name:stock.keySet()){
        System.out.println(name+" Stock:"+stock.get(name));
    }

    }

    @Then("i should automatically order {int} {string}")
    public void iShouldAutomaticallyOrder(int arg0, String arg1) {
        ingredientReplenished rep=new ingredientReplenished(stock,minStock,arg0);
         orders=rep.check(arg1,suppliers);
        if(orders!=null) {
            actual=rep.getStock().get(arg1);
            orders.PrintOrder();
            System.out.println("Stock after order: " + actual);
        }
        else{
            System.out.println("No available supplier!");
        }
    }

    @And("the quantity of {string} increased by {int}")
    public void theQuantityOfIncreasedBy(String arg0, int arg1) {
        assertEquals("Stock should be "+stock.get(arg0),(int)stock.get(arg0),actual);
    }


    @Then("system should notify no available supplier for {string}")
    public void systemShouldNotifyNoAvailableSupplierFor(String arg0) {

        assertNull("Should be no supplier for this ingredient", orders);
        System.out.println("No available supplier for " + arg0);
    }

    @And("i check for supplier availability for {string}")
    public void iCheckForSupplierAvailabilityFor(String arg0) {
        ingredientReplenished rep=new ingredientReplenished(stock,minStock,10);
        orders=rep.check(arg0,suppliers);
    }

}