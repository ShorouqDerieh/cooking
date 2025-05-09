package main_classes;
import java.io.*;
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


    private List<Customer> customers = new ArrayList<>();

    public void saveCustomer(Customer customer) {
        customers.add(customer);
    }




    // Register a customer
    //private Map<String, Customer> customerDatabase = new HashMap<>();
    private final String FILE_NAME = "customers.ser";



    public Customer getCustomer(String id) {
        return customerDatabase.get(id);
    }

    public Collection<Customer> getAllCustomers() {
        return customerDatabase.values();
    }

    public void saveCustomers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(customerDatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCustomers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            customerDatabase = (Map<String, Customer>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous customer data found. Starting fresh.");
        }
    }




    // You no longer need a separate list of customers, as the map serves the purpose
    // public List<Customer> getAllCustomers()
}
