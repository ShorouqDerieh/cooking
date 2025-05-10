package org.example;

import java.io.Serializable;
import java.util.*;
public class Customer  {
    private String id;
    private String name;
    private String dietaryPreferences;
    private String allergies;
    private List<String> pastOrders = new ArrayList<>();
    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }
    private List<String> orders;

    public Customer(String id, String name, List<String> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDietaryPreferences() { return dietaryPreferences; }
    public String getAllergies() { return allergies; }

    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    private List<Customer> customers = new ArrayList<>();

    public void saveCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public String getPreferences() {
        return dietaryPreferences;
    }
    public List<String> getOrders() { return orders; }
    public void inputDietaryPreferencesAndAllergies(String preferences, String allergies) {
        this.dietaryPreferences = preferences;
        this.allergies = allergies;
        System.out.println("Dietary preferences and allergies saved.");
    }

    // view past orders
    public void viewPastOrders() {
        if (pastOrders.isEmpty()) {
            System.out.println("You have no past orders.");
        } else {
            System.out.println("Your past orders:");
            for (String order : pastOrders) {
                System.out.println(order);
            }
        }
    }

    // reorder a meal
    public void reorderMeal(String meal) {
        System.out.println("Reordering: " + meal);
        pastOrders.add(meal);
    }



    // add past order
    public void addPastOrder(String order) {
        pastOrders.add(order);
    }
    public List<String> suggestMeals() {
        List<String> suggestions = new ArrayList<>();
        if (dietaryPreferences != null && dietaryPreferences.toLowerCase().contains("vegetarian")) {
            suggestions.add("Vegetarian Pasta");
            suggestions.add("Grilled Vegetable Salad");
        }
        if (allergies != null && allergies.toLowerCase().contains("nuts")) {
            suggestions.add("Nut-free Chicken Stir Fry");
            suggestions.add("Gluten-free Pizza");
        }
        if (suggestions.isEmpty()) {
            suggestions.add("Classic Beef Burger");
            suggestions.add("Chicken Caesar Salad");
        }
        return suggestions;
    }


    public void addOrder(String order) {
        orders.add(order);
    }
    public void placeOrder(String meal) {
        if (meal.toLowerCase().contains(allergies.toLowerCase()) ||
                meal.toLowerCase().contains("meat") && dietaryPreferences.equalsIgnoreCase("vegetarian")) {
            System.out.println("This meal does not fit your dietary restrictions or allergies!");
        } else {
            pastOrders.add(meal);
            System.out.println(" Meal ordered: " + meal);
        }
    }


}