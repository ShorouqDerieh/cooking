package main_classes;
import java.util.*;
//1.1
public class ChefService {

    private CustomerService customerService;

  public ChefService(CustomerService customerService) {
       this.customerService = customerService;
    }
    public ChefService() {

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

}







