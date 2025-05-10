package org.example.test;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.sf.jasperreports.engine.JRException;
import org.example.Data;
import org.example.invoice;
import org.example.invoiceGenerator;
import org.example.reportGenerator;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class billingSteps {
    private String name;
    private List<String> items = new ArrayList<>();
    private double totalPrice;
    private invoice invoice;
    invoiceGenerator generator;
    private List<Map<String, Object>> salesData = new ArrayList<>();
    reportGenerator report;
    File reportFile;

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

    @Given("i am a system adminstrator")
    public void iAmASystemAdminstrator() {
        report=new reportGenerator();
    }

    @And("i select the month of {string}")
    public void iSelectTheMonthOf(String arg0) {
    report.setSelectedMonth(arg0);
    }

    @And("there are recorded sales data for that month")
    public void thereAreRecordedSalesDataForThatMonth() {
       report.addSalesData("Pizza",20,300, LocalDate.of(2025, 5, 5));
       report.addSalesData("Lasagna",10,250,LocalDate.of(2025, 5, 10));
       report.addSalesData("mulukhiyah",15,90,LocalDate.of(2025, 5, 11));
    }

    @When("I generate the monthly financial report")
    public void iGenerateTheMonthlyFinancialReport() throws JRException {
        if (report.hasData()) {
            report.setReportDate(String.valueOf(new Date()));
            report.generateReport();
        }
    }

    @Then("a PDF report should be generated containing:")
    public void aPDFReportShouldBeGeneratedContaining(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String section = row.get("Section");
            String description = row.get("Description");
            System.out.println("Section: " + section + ", Description: " + description);}
        String reportPath = "Report_.pdf";
        reportFile = new File(reportPath);
        assertTrue("Report should be generated", reportFile.exists());
        assertTrue("Report should not be empty", reportFile.length()>0);
    }

    @Then("no PDF report should be generated")
    public void noPDFReportShouldBeGenerated() {
        System.out.println("no sales to report in this month!");
        assertFalse("Report should not be generated",report.hasData());
    }
}