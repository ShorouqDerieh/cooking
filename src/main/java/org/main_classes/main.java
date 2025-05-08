package main_classes;
import java.util.List;
public class main {
    public static void main(String[] args) {

        CustomerService customerService = new CustomerService();
        ChefService chefService = new ChefService(customerService);
        MealRecommendationService mealRecommendationService = new MealRecommendationService();


        Customer customer = customerService.registerCustomer("1", "Hiba");

        //
        customerService.savePreferences("1", "vegetarian", "لاbread");

        //
        Customer fetchedCustomer = chefService.viewCustomerProfile("1");
        System.out.println("Customer Profile:");
        System.out.println("ID: " + fetchedCustomer.getId());
        System.out.println("Name: " + fetchedCustomer.getName());
        System.out.println("Dietary Preferences: " + fetchedCustomer.getDietaryPreferences());
        System.out.println("Allergies: " + fetchedCustomer.getAllergies());

        //
        String customizedMeal = chefService.customizeMeal(fetchedCustomer);
        System.out.println("\nCustomized Meal: " + customizedMeal);

        // make suere
        if (customizedMeal.contains("vegetarian") && customizedMeal.contains("without peanuts")) {
            System.out.println("✅ Meal respects dietary preferences and allergies.");
        } else {
            System.out.println("❌ Meal does NOT respect dietary preferences and allergies.");
        }

        // sugest meals
        List<String> recommendations = mealRecommendationService.recommendMeals(fetchedCustomer);
        System.out.println("\nRecommended Meals:");
        for (String meal : recommendations) {
            System.out.println("- " + meal);
        }

        //make sure suggest not contain alller
        boolean containsAllergy = recommendations.stream().anyMatch(meal -> meal.toLowerCase().contains("peanuts"));
        if (!containsAllergy) {
            System.out.println("✅ Recommendations respect allergies.");
        } else {
            System.out.println("❌ Recommendations do NOT respect allergies.");
        }
    }
}
