package com.udacity.salesinvoicegenerator.model;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class FileOperations {
   private String invoiceHeaderFilePath = "src/com/udacity/salesinvoicegenerator/InvoiceHeader.csv";
   private String invoiceLineFilePath = "src/com/udacity/salesinvoicegenerator/InvoiceLine.csv";
   private File invoiceHeadersFilePathCheck = new File(invoiceHeaderFilePath);
   private File invoiceLinesFilePathCheck = new File(invoiceLineFilePath);
    final static String date_Format = "dd-MM-yyyy";

    //This method reads the invoice header and invoice lines files and return a list of invoice headers object
    public  ArrayList<InvoiceHeader> readFile() throws Exception {

String invoiceHeaderLine="";
String invoiceLineRow="";
ArrayList<InvoiceHeader> invoiceHeaders=new ArrayList<>();
FileReader invoiceHeadersFile = null;
BufferedReader invoiceHeadersReader = null;
FileReader invoiceLinesFile = null;
BufferedReader invoiceLinesReader = null;

//Checking if the provided files are having the correct csv format
        if ((!invoiceHeadersFilePathCheck.getName().toUpperCase().endsWith("CSV")) && (!invoiceLinesFilePathCheck.getName().toUpperCase().endsWith("CSV"))) {
            throw new Exception("Incorrect File format!, File should be in csv format.");
        } else {
            try {
                //Reading the invoice headers file
                invoiceHeadersFile = new FileReader(invoiceHeaderFilePath);
                invoiceHeadersReader = new BufferedReader(invoiceHeadersFile);
                //looping the records of the invoice headers file
                while ((invoiceHeaderLine = invoiceHeadersReader.readLine()) != null) {
                    //splitting each cell and storing it in an array of strings
                    String[] values = invoiceHeaderLine.split(",");
                    InvoiceHeader invoiceHeader = new InvoiceHeader();
                    //filling in the invoice header object
                    invoiceHeader.setInvoiceNumber(Integer.parseInt(values[0]));
                    //Checking if the date in the cell is a valid date
                    if(isDateValid(values[1])){
                    invoiceHeader.setInvoiceDate(values[1]);}
                    else {
                        invoiceHeader.setInvoiceDate("Invalid Date!");
                    }
                    invoiceHeader.setCustomerName(values[2]);
                    //Starting to read the invoice lines file
                    invoiceLinesFile = new FileReader(invoiceLineFilePath);
                    invoiceLinesReader = new BufferedReader(invoiceLinesFile);
                    ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
                    //Looping the invoice lines records
                    while ((invoiceLineRow = invoiceLinesReader.readLine()) != null) {
                        String[] values2 = invoiceLineRow.split(",");
                        //Getting all invoice lines for the related invoice header using the unique invoice number identifier
                        if (invoiceHeader.getInvoiceNumber() == Integer.parseInt(values2[0])) {
                            InvoiceLine invoiceLine = new InvoiceLine();
                            invoiceLine.setItemName(values2[1]);
                            invoiceLine.setItemPrice(Double.parseDouble(values2[2]));
                            invoiceLine.setCount(Integer.parseInt(values2[3]));
                            //filling in the invoice lines list
                            invoiceLines.add(invoiceLine);
                        }
                        //adding the list of invoice lines to the invoice header
                        invoiceHeader.setInvoiceLines(invoiceLines);
                    }
                    //adding the final invoice header to the list of invoice headers
                    invoiceHeaders.add(invoiceHeader);
                    //closing the reader and file of the invoice lines file
                    invoiceLinesReader.close();
                    invoiceLinesFile.close();
                }
                //closing the reader and file of the invoice headers file
                invoiceHeadersReader.close();
                invoiceHeadersFile.close();
            } catch (FileNotFoundException e) {
                System.out.println("File is not Found!");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // return the list of invoices headers
        return invoiceHeaders;
    }

    //This method is used in writing into the invoice headers and invoice lines files
    public void writeFile(ArrayList<InvoiceHeader> invoiceHeaders) throws Exception {
        // Define Variables for file paths

        FileWriter invoiceHeadersFile;
        BufferedWriter invoiceHeadersWriter;
        FileWriter invoiceLinesFile;
        BufferedWriter invoiceLinesWriter;
//Checking if the folder or file path found
        if (!invoiceHeadersFilePathCheck.exists()) {
            throw new Exception("The Folder/File path is not found");
            //checking that the file is matching to the required csv format
        } else if (!invoiceHeadersFilePathCheck.getName().toUpperCase().endsWith("CSV")) {
            throw new Exception("Incorrect File format!, File should be in csv format.");
        } else {
            try {
                //Starting to write in the invoice headers file
                invoiceHeadersFile = new FileWriter(invoiceHeaderFilePath, true);
                invoiceHeadersWriter = new BufferedWriter(invoiceHeadersFile);

                List<String> invoicesHeaders = new ArrayList<>();
                //format the data so that the three fields are represented as a string and comma separated for each invoice header
                for (int i = 0; i < invoiceHeaders.size(); i++) {
                    invoicesHeaders.add(invoiceHeaders.get(i).getInvoiceNumber() + "," +
                            invoiceHeaders.get(i).getInvoiceDate() + "," +
                            invoiceHeaders.get(i).getCustomerName());
                }
                // write the data and put each value in one cell using the comma delimiter
                for (String rowData : invoicesHeaders) {
                    invoiceHeadersWriter.write("\n");
                    invoiceHeadersWriter.write(String.join(",", rowData));
                }
                //close the writer and the file for invoice headers
                invoiceHeadersWriter.close();
                invoiceHeadersFile.close();
            } catch (FileNotFoundException e) {
                System.out.println("File is not Found!");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Checking if the folder or path of the invoice lines file exist
        if (!invoiceLinesFilePathCheck.exists()) {
            throw new Exception("The Folder/File path is not found");
            //checking if the file is having the proper csv format
        } else if (!invoiceLinesFilePathCheck.getName().toUpperCase().endsWith("CSV")) {
            throw new Exception("Incorrect File format!, File should be in csv format.");
        } else {
            try {
                //Start reading the invoice lines file
                invoiceLinesFile = new FileWriter(invoiceLineFilePath, true);
                invoiceLinesWriter = new BufferedWriter(invoiceLinesFile);

                List<List<InvoiceLine>> invoicesLinesData = new ArrayList<>();
                List<InvoiceLine> IL = new ArrayList<>();
                List<String> invoicesLines = new ArrayList<>();
                //Start in filling list of invoice lines
                for (int i = 0; i < invoiceHeaders.size(); i++) {
                    invoicesLinesData.add(invoiceHeaders.get(i).getInvoiceLines());
                    //start in filling each invoice line in a list of strings with values comma separated
                    for (int x = 0; x < invoicesLinesData.get(i).size(); x++) {
                        IL.add(invoicesLinesData.get(i).get(x));
                        invoicesLines.add(invoiceHeaders.get(i).getInvoiceNumber() + "," +
                                IL.get(x).getItemName() + "," +
                                IL.get(x).getItemPrice() + "," +
                                IL.get(x).getCount());
                    }
                }

                // Start in writing the invoice lines data cell by cell using the comma delimiter
                for (String rowData : invoicesLines) {
                    invoiceLinesWriter.write(String.join(",", rowData));
                    invoiceLinesWriter.write("\n");
                }
                //close the writer and file of the invoice lines
                invoiceLinesWriter.close();
                invoiceLinesFile.close();
            } catch (FileNotFoundException e) {
                System.out.println("File is not Found!");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //This method is validating the dates provided in the invoice headers sheet
    public static boolean isDateValid(String date)
    {
        try {
            DateFormat dateFormat = new SimpleDateFormat(date_Format);
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}
