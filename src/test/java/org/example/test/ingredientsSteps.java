package org.example.test;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ingredientsSteps {
    ArrayList<ingredient> ingredients;
    ArrayList<String> restock;
    ArrayList<String>ingredientsName;
    @Given("I have logged as kitchen manager")
    public void i_am_a_kitchen_manager() {
        ingredients = new ArrayList<>();
        restock = new ArrayList<>();
    }
    @And("We have the following ingredients")
    public void weHaveTheFollowingIngredients(DataTable dataTable) {
        List<Map<String, String>> ingredientData = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : ingredientData) {
            String name = row.get("name");
            int quantity = Integer.parseInt(row.get("quantity"));
            int minQuantity = Integer.parseInt(row.get("minquantity"));

            ingredients.add(new ingredient(name, quantity, minQuantity));
        }
    }

    @When("I view the current stock levels")
    public void iViewTheCurrentStockLevels() {
        System.out.println("We have the following ingredients:");
        for (ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName()+" ---> "+" Quantity: "+ingredient.getQuantity()+" | "+" Minimum Quantity: "+ingredient.getMinquantity());
        }
    }

    @Then("I should see the quantities of all ingredients displayed correctly")
    public void iShouldSeeTheQuantitiesOfAllIngredientsDisplayedCorrectly() {
        assertEquals("Ingredient Counts should be 10",10,ingredients.size());
        System.out.println("all ingredients displayed correctly");
    }

    @When("the system check for low stock")
    public void theSystemCheckForLowStock() {
        for(ingredient ingredient : ingredients) {
            if(ingredient.getQuantity() < ingredient.getMinquantity()) {
                restock.add(ingredient.getName());
            }
        }
    }

    @Then("it should suggest restocking for the following ingredients")
    public void itShouldSuggestRestockingForTheFollowingIngredients(DataTable dataTable) {
        List<String> expected = dataTable.asList();
     //   System.out.println("Restock List: " + restock);
       // System.out.println("Ingredients List: " + ingredients);
        //System.out.println("Expected restock items: " + expected);
        for(String ingredient : expected) {
            //check if restock contains the expected item if not appear the message
            assertTrue("Missing restock suggestion for: "+ingredient,restock.contains(ingredient));}
        assertEquals("Unexpected number of restock suggestions", expected.size(), restock.size());
        //all true
        System.out.println("Restock suggestions are correct: " + restock);


    }

    @When("I add the following new ingredients")
    public void iAddTheFollowingNewIngredients(DataTable dataTable) {
        List<Map<String, String>> newIngredientData = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : newIngredientData) {
            String name = row.get("name");
            int quantity = Integer.parseInt(row.get("quantity"));
            int minQuantity = Integer.parseInt(row.get("minquantity"));

            ingredients.add(new ingredient(name, quantity, minQuantity));
        }

    }

    @Then("I should see the new ingredients added to the inventory")
    public void iShouldSeeTheNewIngredientsAddedToTheInventory() {
        System.out.println("New ingredients List:\n");
        for (ingredient ing : ingredients) {
            System.out.println(ing);
        }
    }

    @And("total ingredients count should be {int}")
    public void totalIngredientsCountShouldBe(int arg0) {
        assertEquals("ingredients count not match",arg0,ingredients.size());
        System.out.println("total ingredients count match: "+arg0);
    }

    @When("I update the quantity of ingredient")
    public void iUpdateTheQuantityOfIngredient(DataTable dataTable) {
        List<Map<String, String>> updates = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : updates) {
            String name = row.get("name");
            int newQuantity = Integer.parseInt(row.get("quantity"));
            for (ingredient ing : ingredients) {
                if (ing.getName().equals(name)) {
                    ing.setQuantity(newQuantity);
                    System.out.println(" Updated " + name + " quantity to " + newQuantity);
                }
            }
        }
    }

    @Then("I should see the following ingredient quantities")
    public void iShouldSeeTheFollowingIngredientQuantities(DataTable dataTable) {
        List<Map<String, String>> expected = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : expected) {
            String name = row.get("name");
            int expectedQuantity = Integer.parseInt(row.get("quantity"));
            ingredient item = ingredients.stream()
                    .filter(ing -> ing.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null);

            assertEquals("Quantity mismatch for " + name, expectedQuantity, item.getQuantity());
            System.out.println("Quantity of " + name + " is " + expectedQuantity);
        }
    }


}
