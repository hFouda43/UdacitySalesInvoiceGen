package com.udacity.salesinvoicegenerator.controller;

import com.udacity.salesinvoicegenerator.model.FileOperations;
import com.udacity.salesinvoicegenerator.model.InvoiceHeader;
import java.util.ArrayList;
import java.util.Scanner;


public class TestInvoice {

 public static void main(String[] args) throws Exception {

  ArrayList<InvoiceHeader> invoicesAsReadFromUser;
  ArrayList<InvoiceHeader> invoicesAsReadFromCsv = null;
  ReadDataFromUser readData = new ReadDataFromUser();
  FileOperations fileOperations = new FileOperations();
  PrintInvoicesDetails print = new PrintInvoicesDetails();


  //Ask if user wants to view the invoices or enter data
  System.out.println("Do you want to:");
  System.out.println("1- Print the invoice only? Please hit \"p\" key");
  System.out.println("2- Add new invoices only? Please hit \"I\" key");
  System.out.println("3- Print the invoice, then add new invoices then print them? Please hit \"A\" key");
  Scanner sc=new Scanner(System.in);
  String userInput=sc.next().toLowerCase();
  switch (userInput){
   case "p":
    //Read the saved data from the CSV files
    try {
     invoicesAsReadFromCsv = fileOperations.readFile();
    } catch (Exception e) {
     e.getMessage();
     e.printStackTrace();
    }
    //Print the Invoices from the CSV files
    print.print(invoicesAsReadFromCsv);
    break;
   case "i":
    //Ask user to enter data
    invoicesAsReadFromUser = readData.readData();
    //Write the data entered by the user in the CSV files
    try {
     fileOperations.writeFile(invoicesAsReadFromUser);
    } catch (Exception e) {
     e.getMessage();
     e.printStackTrace();
    }
    break;
   case "a":
    //Read the saved data from the CSV files
    try {
     invoicesAsReadFromCsv = fileOperations.readFile();
    } catch (Exception e) {
     e.getMessage();
     e.printStackTrace();
    }
    //Print the Invoices from the CSV files
    print.print(invoicesAsReadFromCsv);
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
   default:
    System.out.println("Invalid Input, please enter a valid input from the above options!");
  }

 }
}

