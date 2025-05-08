package main_classes;
//1.1
import java.util.*;
public class Customer {
    private String id;
    private String name;
    private String dietaryPreferences;
    private String allergies;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
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
}
