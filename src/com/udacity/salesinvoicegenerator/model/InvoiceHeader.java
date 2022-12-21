package com.udacity.salesinvoicegenerator.model;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    //Invoice header class is a parent of invoice lines class and is having an aggregate relation to it
    private int invoiceNumber;
    private String invoiceDate;
    private String customerName;
    private ArrayList<InvoiceLine> invoiceLines;

    public InvoiceHeader() {

    }

    public InvoiceHeader(String invoiceDate, String customerName, ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.invoiceLines = invoiceLines;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }
}
