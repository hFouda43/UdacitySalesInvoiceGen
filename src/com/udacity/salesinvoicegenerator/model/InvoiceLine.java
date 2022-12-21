package com.udacity.salesinvoicegenerator.model;

public class InvoiceLine extends InvoiceHeader{

    //Invoice line class is inheriting the invoice Header class to maintain the relationship of the invoice number key
    private String itemName;
    private double itemPrice;
    private int count;

    public InvoiceLine() {
    }

    public InvoiceLine(String itemName, double itemPrice, int count) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
