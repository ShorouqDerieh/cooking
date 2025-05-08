package org.example.test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.EmailService;
import org.example.ingredient;
import org.example.kitchenManager;
import org.example.lowStockService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class lowStockSteps {
    List<ingredient> ingredient = new ArrayList<>();
    Map<String, Boolean> sending = new HashMap<>();
    List<String> emails = new ArrayList<>();
    String managerEmail;
    kitchenManager manager;
    lowStockService service;
    boolean sent;
    lowStockService service2 = new lowStockService();
    //ingredient item;
 /*   @Given("i am a kitchen manager my name is {string} and my email is {string}")
    public void iAmAKitchenManager() {
        System.out.println("I am a kitchen manager");
        manager=new kitchenManager();
        manager.setEmail();

    }*/

    @Given("i am a kitchen manager my name is {string} and my email is {string}")
    public void iAmAKitchenManagerMyNameIsAndMyEmailIs(String arg0, String arg1) {
        System.out.println("I am a kitchen manager");
        manager = new kitchenManager(arg0, arg1);
    }

    @And("we have an ingredient {string} with stock {string}")
    public void weHaveAnIngredientWithStock(String arg0, String arg1) {
        ingredient item = new ingredient();
        item.setName(arg0);
        item.setQuantity(Integer.parseInt(arg1));
        ingredient.add(item);
        sending.put(item.getName(), false);

    }

    @And("each ingredient has minimum quantity {string}")
    public void eachIngredientHasMinimumQuantity(String arg0) {
        for (ingredient item : ingredient) {
            item.setMinquantity(Integer.parseInt(arg0));
        }
    }

    @When("the stock of {string} is updated to {string}")
    public void theStockOfIsUpdatedTo(String arg0, String arg1) {
        for (ingredient item : ingredient) {
            if (item.getName().equals(arg0)) {
                item.setQuantity(Integer.parseInt(arg1));
                emails = service2.generateEmails(ingredient, sending);
               /*if(item.getQuantity()<=item.getMinquantity()&&!sending.get(item.getName())){
                    String email="Low Stock of "+item.getName()+
                            " Current Stock: "+item.getQuantity()+
                            " Minimum Stock: "+item.getMinquantity();
                    emails.add(email);
               }*/
            }

        }
    }

    @Then("the system should send email to kitchen manager")
    public void theSystemShouldSendEmailToKitchenManager() {
        // if(emails.isEmpty()){
        if (!emails.isEmpty()) {
            service = new lowStockService(emails, manager);
            service.check();
        }
        assertFalse("There are no emails to send", emails.isEmpty());


        // service=new lowStockService(emails,manager);
        // for(ingredient item:ingredient){
        //   sending.replace(item.getName(),false,service.check());
        //}
      /*  for(String email:emails) {
            service.sendEmail(manager.getEmail(), "Low stock Alert", email);
        }*/

    }

    @And("the email should include ingredient name {string}")
    public void theEmailShouldIncludeIngredientName(String arg0) {
        boolean found = emails.stream().anyMatch(email -> email.contains(arg0));
        if (!found) {
            assertFalse("Email doesn't include ingredient quantity: " + arg0, found);
        }
    }

    @And("the current quantity {string}")
    public void theCurrentQuantity(String arg0) {
        boolean found = emails.stream().anyMatch(email -> email.contains("Current Stock: " + arg0));
        if (!found) {
            assertFalse("Email doesn't include ingredient quantity: " + arg0, found);
        }
    }

    @And("the minimum quantity {string}")
    public void theMinimumQuantity(String arg0) {
        boolean found = emails.stream().anyMatch(email -> email.contains("Minimum Stock: " + arg0));
        if (!found) {
            assertFalse("Email doesn't include ingredient quantity: " + arg0, found);
        }
    }


}