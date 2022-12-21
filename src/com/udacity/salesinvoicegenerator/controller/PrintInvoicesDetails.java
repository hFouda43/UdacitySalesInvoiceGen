package com.udacity.salesinvoicegenerator.controller;
import com.udacity.salesinvoicegenerator.model.InvoiceHeader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PrintInvoicesDetails {

    // Print method prints the details of an input list of invoices
    public void print(ArrayList<InvoiceHeader> invoiceHeaders){
        //Looping the invoices to print out its data
        for(int i=0;i<invoiceHeaders.size();i++){
            System.out.println(invoiceHeaders.get(i).getInvoiceNumber());
            System.out.println("{");
            System.out.println("("+changeDateFormat(invoiceHeaders.get(i).getInvoiceDate())+"), "+invoiceHeaders.get(i).getCustomerName());
            //looping the invoice lines list for each invoice
            for(int x=0;x<invoiceHeaders.get(i).getInvoiceLines().size();x++){
                System.out.println(invoiceHeaders.get(i).getInvoiceLines().get(x).getItemName()+", "+invoiceHeaders.get(i).getInvoiceLines().get(x).getItemPrice()+", "+invoiceHeaders.get(i).getInvoiceLines().get(x).getCount());
            }
            System.out.println("}");
        }
    }

    //This method changes the date format provided in the Invoice Header sheet to the format required in the final output
       public static String changeDateFormat(String date)  {
           DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
           try {
               //parsing the string into date object
               Date parsedDate = format.parse(date);
               //changing the format of the parsed date
               String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(parsedDate);
               return formattedDate;
           } catch (ParseException e) {
               throw new RuntimeException(e);
           }
    }

}
