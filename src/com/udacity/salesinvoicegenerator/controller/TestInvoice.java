package com.udacity.salesinvoicegenerator.controller;

import com.udacity.salesinvoicegenerator.model.FileOperations;
import com.udacity.salesinvoicegenerator.model.InvoiceHeader;
import java.util.ArrayList;


public class TestInvoice {

 public static void main(String[] args) throws Exception {

  ArrayList<InvoiceHeader> invoicesAsReadFromUser;
  ArrayList<InvoiceHeader> invoicesAsReadFromCsv = null;
  ReadDataFromUser readData = new ReadDataFromUser();
  FileOperations fileOperations = new FileOperations();
  PrintInvoicesDetails print = new PrintInvoicesDetails();

  //Ask user to enter data
  invoicesAsReadFromUser = readData.readData();
  //Write the data entered by the user in the CSV files
  try {
   fileOperations.writeFile(invoicesAsReadFromUser);
  } catch (Exception e) {
   e.getMessage();
   e.printStackTrace();
  }
  //Read the saved data from the CSV files
  try {
   invoicesAsReadFromCsv = fileOperations.readFile();
  } catch (Exception e) {
   e.getMessage();
   e.printStackTrace();
  }
  //Print the Invoices from the CSV files
  print.print(invoicesAsReadFromCsv);

 }
}

