package main_classes;
import java.util.*;
//1.1
public class CustomerService {
    private Map<String, Customer> customerDatabase = new HashMap<>();

    public Customer registerCustomer(String id, String name) {
        if (!customerDatabase.containsKey(id)) {
            Customer customer = new Customer(id, name);
            customerDatabase.put(id, customer);
        }
        return customerDatabase.get(id);
    }

    public void savePreferences(String id, String dietaryPref, String allergies) {
        Customer customer = customerDatabase.get(id);
        if (customer != null) {
            customer.setDietaryPreferences(dietaryPref);
            customer.setAllergies(allergies);
        }
    }

    public Customer getCustomer(String id) {
        return customerDatabase.get(id);
    }
    private List<Customer> customers = new ArrayList<>();

    public void saveCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

}
