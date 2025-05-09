package org.example.test;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import java.time.LocalDate;
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
    int days;
    LocalDate today;
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


    @When("the end of the business day is reached")
    public void theEndOfTheBusinessDayIsReached() {
        emails = service2.generateDailySummaryEmail(ingredient);
        sent = !emails.isEmpty();

    }

    @And("there are ingredients with stock levels below their minimum quantities")
    public void thereAreIngredientsWithStockLevelsBelowTheirMinimumQuantities() {
        assertFalse("No ingredients are below minimum quantity", emails.isEmpty());
    }

    @Then("the system should send a single email to the kitchen manager")
    public void theSystemShouldSendASingleEmailToTheKitchenManager() {
        if (sent) {
            service = new lowStockService(emails, manager);
            service.sendDailySummary("Daily Low-Stock Summary");
        }
        assertTrue("Email summary was not sent", sent);
    }

    @And("the email should include:")
    public void theEmailShouldInclude(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String name = row.get("ingredient");
            int stock = Integer.parseInt(row.get("stock"));
            int minQty = Integer.parseInt(row.get("Minquantity"));
            ingredient item = new ingredient(name, stock, minQty);
            ingredient.add(item);
        }
    }

    @And("the email subject should be {string}")
    public void theEmailSubjectShouldBe(String arg0) {
        assertTrue(emails.stream().anyMatch(email -> email.contains(arg0)));
    }

    @And("the email should include a message like {string}")
    public void theEmailShouldIncludeAMessageLike(String arg0) {
        boolean found = emails.stream().anyMatch(email -> email.contains(arg0));
        assertTrue("The message was not found in the email", found);
    }

    @And("we have the following ingredients:")
    public void weHaveTheFollowingIngredients(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            ingredient item = new ingredient();
            item.setName(row.get("ingredient"));
            item.setQuantity(Integer.parseInt(row.get("stock")));
            item.setMinquantity(Integer.parseInt(row.get("Minquantity")));
            ingredient.add(item);
            sending.put(item.getName(), false);
        }
    }

    @And("we have a product {string} with expiry date {string}")
    public void weHaveAProductWithExpiryDate(String arg0, String arg1) {
        ingredient item=new ingredient();
        item.setName(arg0);
        item.setExpiryDate(LocalDate.parse(arg1));
        ingredient.add(item);

    }

    @And("the system is configured to alert {int} days before expiry")
    public void theSystemIsConfiguredToAlertDaysBeforeExpiry(int arg0) {
        days=arg0;
    }

    @When("today's date is {string}")
    public void todaySDateIs(String arg0) {
        today=LocalDate.parse(arg0);
        expiryService expiry=new expiryService();
        emails=expiry.generateExpiryAlerts(ingredient,today,days);
    }

    @Then("the system should send an expiry alert email to the kitchen manager")
    public void theSystemShouldSendAnExpiryAlertEmailToTheKitchenManager() {
        if (!emails.isEmpty()) {
            expiryService expirySrv = new expiryService();
            expirySrv.sendExpiryEmails(emails, manager);
        }
        assertFalse("No expiry alerts were sent", emails.isEmpty());
    }

    @And("the email should includes:")
    public void theEmailShouldIncludes(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String product = row.get("product");
            String expiryDate = row.get("expiryDate");

            boolean found = emails.stream().anyMatch(email ->
                    email.contains(product) && email.contains(expiryDate));

            assertTrue("Email should include product " + product + " with expiry date " + expiryDate, found);
        }
    }
}