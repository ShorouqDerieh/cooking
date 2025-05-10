package main_classes;

public class Chef {
    private String name;

    public Chef(String name) {
        this.name = name;
    }

    // view customer preferences to customize meals
    public void viewCustomerPreferences(Customer customer) {
        System.out.println("Dietary Preferences: " + customer.getDietaryPreferences());
        System.out.println("Allergies: " + customer.getAllergies());
    }

    // suggest meal plans
    public void suggestMealPlans(Customer customer) {
        System.out.println("Suggesting meal plans based on customer order history...");
        customer.viewPastOrders();
    }

    // validate  compatibility
    public void validateIngredientCombination(String ingredient1, String ingredient2) {
        // Simple validation (you can expand based on real ingredient data)
        if (ingredient1.equals("Salmon") && ingredient2.equals("Chocolate Sauce")) {
            System.out.println("These ingredients cannot be combined.");
        } else {
            System.out.println("Ingredients are compatible.");
        }
    }

    // notifiy
    public void notifyIngredientSubstitution(String original, String substitute) {
        System.out.println("Ingredient " + original + " is unavailable. Substituting with " + substitute + ".");
    }
}
