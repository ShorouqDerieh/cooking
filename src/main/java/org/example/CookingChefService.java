package org.example;

import java.util.*;
//1.1
public class CookingChefService {

    private CustomerService customerService;

    public CookingChefService(CustomerService customerService) {
        this.customerService = customerService;
    }
    public CookingChefService() {

    }
    public Customer viewCustomerProfile(String customerId) {
        return customerService.getCustomer(customerId);
    }

    public String customizeMeal(Customer customer) {
        if (customer.getAllergies() != null && customer.getAllergies().equalsIgnoreCase("peanuts")) {
            return "vegetarian salad without peanuts";
        }
        return "vegetarian salad";
    }
    private List<Customer> customers = new ArrayList<>();

    public void saveCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public void viewCustomerPreferences(Customer customer) {
        System.out.println("Dietary preferences: " + customer.getDietaryPreferences());
        System.out.println("Allergies: " + customer.getAllergies());
    }

    public void suggestMealPlans(Customer customer) {
        System.out.println("Chef suggests a personalized meal plan for " + customer.getName());
    }

    public void validateIngredientCombination(String ingredient1, String ingredient2) {
        // Logic for validating the combination of ingredients
        System.out.println("Checking if " + ingredient1 + " and " + ingredient2 + " can be combined...");
    }
}
