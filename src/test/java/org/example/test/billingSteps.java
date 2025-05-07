package org.example.test;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.invoice;
import org.example.invoiceGenerator;

import java.util.ArrayList;
import java.util.List;

public class billingSteps {
    private String name;
    private List<String> items = new ArrayList<>();
    private double totalPrice;
    private invoice invoice;
    invoiceGenerator generator;
    @Given("I am a customer and my name is {string}")
    public void iAmACustomerAndMyNameIs(String arg0) {
        name=arg0;
    }

    @And("i purchase these items")
    public void iPurchaseTheseItems(DataTable data) {
        items=data.asList();
    }

    @And("the total price is {double}")
    public void theTotalPriceIs(Double arg0) {
        totalPrice=arg0;
    }

    @When("i request invoice")
    public void iRequestInvoice() {
        invoice= new invoice(name,items,totalPrice);
       generator = new invoiceGenerator();
    }

    @Then("the system should generate an invoice and display it")
    public void theSystemShouldGenerateAnInvoice() {
        generator.generateInvoice(invoice.getName(),invoice.getItems(),invoice.getPrice());
    }
}