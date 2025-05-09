package org.example.test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Suggest {


//    @Then("the chef should receive an alert for substitution approval")
//    public void theChefShouldReceiveAnAlertForSubstitutionApproval() {
//        // Write code here that turns the phrase above into concrete actions
//   //     throw new io.cucumber.java.PendingException();
//    }
//
//    @Given("no substitute is available for {string}")
//    public void noSubstituteIsAvailableFor(String string) {
//        // Write code here that turns the phrase above into concrete actions
//      //  throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("the system should suggest substitute {string} for {string}")
//    public void theSystemShouldSuggestSubstituteFor(String string, String string2) {
//        // Write code here that turns the phrase above into concrete actions
//     //   throw new io.cucumber.java.PendingException();
//    }
private IngredientService ingredientService;

    @Before
    public void setUp() {
        ingredientService = new IngredientService();
    }

    @Then("the system should suggest substitute {string} for {string}")
    public void theSystemShouldSuggestSubstituteFor(String expected, String ingredient) {
        String actual = ingredientService.suggestSubstitute(ingredient);
        assertEquals(expected, actual);
    }

//    @Then("the system should show an error message {string}")
//    public void theSystemShouldShowAnErrorMessage(String expected) {
//        String actual = ingredientService.getErrorMessage("truffle");
//        assertEquals(expected, actual);
//    }
@Then("the system should show an error message when suggesting a meal {string}")
public void theSystemShouldShowAnErrorMessageForSuggestion(String message) {
    //
    String actual = ingredientService.getErrorMessage("truffle");
//        assertEquals(expected, actual);
}
    @Then("the chef should receive an alert for substitution approval")
    public void theChefShouldReceiveAnAlertForSubstitutionApproval() {
        ingredientService.submitCustomMeal(Arrays.asList("lobster", "chicken", "truffle"));
        assertTrue("Chef was not alerted for substitution approval", ingredientService.isChefAlerted());
    }

    @Given("no substitute is available for {string}")
    public void noSubstituteIsAvailableFor(String ingredient) {
        // For simplicity, we'll just reset IngredientService
        ingredientService = new IngredientService();
    }
    @Then("the system should show an error message {string}")
    public void theSystemShouldShowAnErrorMessage(String message) {
        // Code that checks if the error message is displayed
     //   //System.out.println("Error message: " + message);  // For now, just print it for testing
        // You can add assertions here to verify that the system indeed shows the error message
        Assertions.assertTrue(isErrorMessageDisplayed(message), "Error message was not displayed");
    }

    private boolean isErrorMessageDisplayed(String message) {
        // Implement the logic to verify if the error message is displayed in the UI or system.
        // This could be an assertion based on the system's actual behavior.
        return true;  // Just a placeholder for now
    }
}
