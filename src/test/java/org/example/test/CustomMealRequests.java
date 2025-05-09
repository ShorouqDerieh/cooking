package org.example.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main_classes.*;
import java.util.*;

import static org.junit.Assert.*;

public class CustomMealRequests {

    private MealService mealService = new MealService();
    private List<String> selectedIngredients;
    private String lastErrorMessage;
    private String dietaryPreference;
    private boolean mealCreated;

    @Given("the customer is on the custom meal option")
    public void theCustomerIsOnTheCustomMealOption() {
        mealService.reset();
        selectedIngredients = new ArrayList<>();
        lastErrorMessage = null;
        mealCreated = false;
    }

    @When("the customer selects ingredients {string}, {string}, and {string}")
    public void theCustomerSelectsIngredientsAnd(String ing1, String ing2, String ing3) {
        selectedIngredients.addAll(Arrays.asList(ing1, ing2, ing3));
    }

    @When("{string} is currently unavailable")
    public void isCurrentlyUnavailable(String ingredient) {
        mealService.markAsUnavailable(ingredient);
    }

    @When("the customer selects ingredients {string} and {string}")
    public void theCustomerSelectsIngredientsAnd(String ing1, String ing2) {
        selectedIngredients.addAll(Arrays.asList(ing1, ing2));
    }

    @When("the customer submits the custom meal request")
    public void theCustomerSubmitsTheCustomMealRequest() {
        try {
            mealService.createMeal(selectedIngredients);
            mealCreated = true;
        } catch (IllegalStateException e) {
            lastErrorMessage = e.getMessage();
        }
    }

    @Then("the system should confirm the custom meal is created successfully")
    public void theSystemShouldConfirmTheCustomMealIsCreatedSuccessfully() {
        assertTrue("Meal should be created", mealCreated);
    }

//    @Then("the system should show an error message {string}")
//    public void theSystemShouldShowAnErrorMessage(String expectedMessage) {
//        assertNotNull("Error message should not be null", lastErrorMessage);
//        assertEquals(expectedMessage, lastErrorMessage);
//    }
@Then("the system should show an error message when creating a custom meal {string}")
public void theSystemShouldShowAnErrorMessageForCustomMeal(String message) {
    // Code for handling error in CustomMealRequests
    assertNotNull("Error message should not be null", lastErrorMessage);
    assertEquals(message, lastErrorMessage);
}
    @Given("{string} has limited stock")
    public void hasLimitedStock(String ingredient) {
        mealService.setLimitedStock(ingredient);
    }

    @When("the customer successfully submits a custom meal with {string}")
    public void theCustomerSuccessfullySubmitsACustomMealWith(String ingredient) {
        selectedIngredients.add(ingredient);
        theCustomerSubmitsTheCustomMealRequest();
    }

    @Then("the system should update the available stock for {string}")
    public void theSystemShouldUpdateTheAvailableStockFor(String ingredient) {
        assertTrue("Stock should be updated", mealService.isStockUpdated(ingredient));
    }

    @Given("the customer has set dietary preferences {string}")
    public void theCustomerHasSetDietaryPreferences(String preference) {
        dietaryPreference = preference;
        mealService.setDietaryPreference(preference);
    }

    @When("the customer opens the custom meal page")
    public void theCustomerOpensTheCustomMealPage() {
        // Simulate page load
    }

    @Then("the system should only display gluten-free compatible ingredients")
    public void theSystemShouldOnlyDisplayGlutenFreeCompatibleIngredients() {
        List<String> displayedIngredients = mealService.getDisplayedIngredients();
        assertTrue(displayedIngredients.stream().allMatch(ing -> mealService.isCompatibleWith(dietaryPreference, ing)));
    }

}
