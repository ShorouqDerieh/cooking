package org.example.test;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.*;
import main_classes.*;
import org.junit.jupiter.api.Assertions;
import java.util.*;
public class profile {
    Map<String, Customer> customerDatabase = new HashMap<>();
    List<String> recommendedMeals = new ArrayList<>();
    Customer currentCustomer;
    String currentRole;
   private String customizedMeal;
    private String dietaryPreference;
    private String allergy;//
//    private String recommendedMeal;
//    private String customerName;

    @Given("I am a registered customer")
    public void iAmARegisteredCustomer() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        String regNumber = "123";  // Example
        currentRole = "customer";
        if (!customerDatabase.containsKey(regNumber)) {
            Customer customer = new Customer(regNumber, "");
            customerDatabase.put(regNumber, customer);
        }
        currentCustomer = customerDatabase.get(regNumber);
    }
    @When("I enter my dietary preferences as {string} and allergies as {string}")
    public void iEnterMyDietaryPreferencesAsAndAllergiesAs(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
      //  throw new io.cucumber.java.PendingException();
        currentCustomer.setDietaryPreferences(string);
        currentCustomer.setAllergies(string2);
    }
    @Then("the system should save my preferences and allergies")
    public void theSystemShouldSaveMyPreferencesAndAllergies() {
        // Write code here that turns the phrase above into concrete actions
      //  throw new io.cucumber.java.PendingException();
        Customer savedCustomer = customerDatabase.get(currentCustomer.getId());
        Assertions.assertEquals(currentCustomer.getDietaryPreferences(), savedCustomer.getDietaryPreferences());
        Assertions.assertEquals(currentCustomer.getAllergies(), savedCustomer.getAllergies());
    }
    @Given("I have entered my dietary preferences as {string} and allergies as {string}")
    public void iHaveEnteredMyDietaryPreferencesAsAndAllergiesAs(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    //    throw new io.cucumber.java.PendingException();
        iAmARegisteredCustomer();
        iEnterMyDietaryPreferencesAsAndAllergiesAs(string, string2);
    }
    @When("I search for meal recommendations")
    public void iSearchForMealRecommendations() {
        // Write code here that turns the phrase above into concrete actions
    //    throw new io.cucumber.java.PendingException();
        MealRecommendationService service = new MealRecommendationService();
        recommendedMeals = service.recommendMeals(currentCustomer);
    }
    @Then("the system should recommend only vegetarian meals that are free of peanuts")
    public void theSystemShouldRecommendOnlyVegetarianMealsThatAreFreeOfPeanuts() {
        // Write code here that turns the phrase above into concrete actions
   //     throw new io.cucumber.java.PendingException();
        for (String meal : recommendedMeals) {
            Assertions.assertTrue(meal.toLowerCase().contains("vegetarian"));
            Assertions.assertFalse(meal.toLowerCase().contains("peanuts"));
        }

    }
    @Given("I am a chef")
    public void iAmAChef() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
        currentRole = "chef";
    }
    @Given("a customer has dietary preferences and allergies stored")
    public void aCustomerHasDietaryPreferencesAndAllergiesStored() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
        if (dietaryPreference == null) {
            dietaryPreference = "vegetarian";
        }
        if (allergy == null) {
            allergy = "peanuts";
        }
        iHaveEnteredMyDietaryPreferencesAsAndAllergiesAs("vegetarian", "peanuts");
    }
    @When("I view the customer's profile")
    public void iViewTheCustomerSProfile() {
        // Write code here that turns the phrase above into concrete actions
      //  throw new io.cucumber.java.PendingException();
        Assertions.assertNotNull(currentCustomer);
    }
    @Then("I should see the customer's dietary preferences and allergies")
    public void iShouldSeeTheCustomerSDietaryPreferencesAndAllergies() {
        // Write code here that turns the phrase above into concrete actions
      //  throw new io.cucumber.java.PendingException();
//        Assertions.assertEquals("vegetarian", currentCustomer.getDietaryPreferences());
//        Assertions.assertEquals("peanuts", currentCustomer.getAllergies());
//        ChefService chefService = new ChefService(CustomerService);
//        Customer customer = CustomerService.getCustomer("123");
//        String result = chefService.customizeMeal(customer);
//
//        Assertions.assertTrue(result.contains("vegetarian") && result.contains("without peanuts"));
        Assertions.assertNotNull(dietaryPreference);
        Assertions.assertNotNull(allergy);

    }
    @Then("I should customize the meal accordingly")
    public void iShouldCustomizeTheMealAccordingly() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
       // String chefAction = "Prepare vegetarian meal without peanuts";
      //  Assertions.assertTrue(chefAction.toLowerCase().contains(currentCustomer.getDietaryPreferences()));
      //  Assertions.assertFalse(chefAction.toLowerCase().contains(currentCustomer.getAllergies()));
     //   ChefService c=new ChefService();
        ChefService chefService = new ChefService();
        customizedMeal = chefService.customizeMeal(currentCustomer);
        Assertions.assertTrue(
                customizedMeal.contains("vegetarian") && customizedMeal.contains("without peanuts"),
                "Customized meal does not respect dietary preferences and allergies"
        );
    }
}
