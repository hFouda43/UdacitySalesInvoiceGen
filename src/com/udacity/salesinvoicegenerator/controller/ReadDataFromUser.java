package com.udacity.salesinvoicegenerator.controller;

import com.udacity.salesinvoicegenerator.model.InvoiceHeader;
import com.udacity.salesinvoicegenerator.model.InvoiceLine;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadDataFromUser {
private int numberOfInvoices;
private int numberOfInvoiceLines;

//This method reads the invoice details as an input from the user via console
    public ArrayList<InvoiceHeader> readData(){
        Scanner sc=new Scanner(System.in);
        //Checking the total number of invoices that the user intends to add
        System.out.println("Please Enter the number of Invoices you want to insert:");
        numberOfInvoices=sc.nextInt();
        //Creating a list of invoice headers to store all of the added invoices to it
        ArrayList<InvoiceHeader> invoiceHeaders=new ArrayList<>();
        //looping the invoices as per the total number of invoices added by the user
        for (int i=0;i<numberOfInvoices;i++){
            InvoiceHeader invoiceHeader=new InvoiceHeader();
            System.out.println("Please enter the invoice number for invoice "+(i+1)+" :");
            invoiceHeader.setInvoiceNumber(sc.nextInt());
            System.out.println("Please enter the date for invoice "+(i+1)+" in (dd-MM-yyyy) format: ");
            invoiceHeader.setInvoiceDate(sc.next());
            System.out.println("Please enter the customer name for invoice "+(i+1)+" :");
            invoiceHeader.setCustomerName(sc.next());
            System.out.println("Please enter the number of invoice "+(i+1)+" items: ");
            numberOfInvoiceLines=sc.nextInt();
            //Creating a list of invoice lines to store all invoice items per single invoice
            ArrayList<InvoiceLine> invoiceLines=new ArrayList<>();
            for (int x=0;x<numberOfInvoiceLines;x++){
                InvoiceLine invoiceLine=new InvoiceLine();
                System.out.println("Please enter item "+(x+1)+" name: ");
                invoiceLine.setItemName(sc.next());
                System.out.println("Please enter item "+(x+1)+" price: ");
                invoiceLine.setItemPrice(sc.nextDouble());
                System.out.println("Please enter number of items purchased for item "+(x+1)+" : ");
                invoiceLine.setCount(sc.nextInt());
                invoiceLines.add(invoiceLine);
            }
            //adding all invoice lines to the related invoice header
            invoiceHeader.setInvoiceLines(invoiceLines);
            //adding the final invoice header to the list of invoice headers
            invoiceHeaders.add(invoiceHeader);
        }
        sc.close();
      return invoiceHeaders;
    }
}
