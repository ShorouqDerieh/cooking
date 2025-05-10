package main_classes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackPastOrdersSystem {
    private Map<String, Customer> customerOrders = new HashMap<>();

    public TrackPastOrdersSystem() {
        List<String> ordersHiba = Arrays.asList("Pizza", "Pasta", "Salad");
        List<String> ordersAli = Arrays.asList("Burger", "Sushi");

        customerOrders.put("1", new Customer("1", "hiba", ordersHiba));
        customerOrders.put("2", new Customer("2", "ali", ordersAli));
    }

    public Customer getCustomerById(String id) {
        return customerOrders.get(id);
    }

    public void displayPastOrders(Customer customer) {
        if (customer != null && !customer.getOrders().isEmpty()) {
            System.out.println("Past orders for customer " + customer.getName() + ":");
            for (String order : customer.getOrders()) {
        ///        System.out.println("- " + order);
            }
        } else {
      //      System.out.println("Error: No past orders found for customer " + (customer != null ? customer.getName() : "unknown"));
        }
    }

    public void reorderFirstMeal(Customer customer) {
        if (customer != null && !customer.getOrders().isEmpty()) {
            String firstOrder = customer.getOrders().get(0);
        //    System.out.println(customer.getName() + " reordered: " + firstOrder);
        } else {
         //   System.out.println("Error: No orders to reorder.");
        }
    }

    public void displayAllOrderHistories() {
        for (Customer customer : customerOrders.values()) {
         //   System.out.println("Customer: " + customer.getName() + ", ID: " + customer.getId());
            for (String order : customer.getOrders()) {
           //     System.out.println("- " + order);
            }
        }
    }

    public void analyzeData() {
     //   System.out.println("Analyzing data to improve service...");
    }

    public void suggestMealPlans(Customer customer) {
        if (customer != null) {
         //   System.out.println("Chef suggests a personalized meal plan for customer " + customer.getName() + ".");
        } else {
         //   System.out.println("Error: Customer not found.");
        }
    }
//    public List<String> getPastOrdersForCustomer(String id) {
//        Customer customer = customerOrders.get(id);
//        if (customer != null) {
//            return customer.getOrders();
//        }
//        return Arrays.asList();  // Return an empty list if no orders found
//    }
}