package com.pluralsight;
import java.io.*;
import java.util.*;

//Responsible for CSV and new transactions

public class TransactionManager {
    private static final String File_Name = "transactions.csv"; //File where data is stored
    private List<Transaction> transactions = new ArrayList<>(); //List of transactions
    //Constructor loads transactions
    public TransactionManager() {
        loadTransactions();
    }
    //Getter for all transactions
    public List<Transaction> getTransactions() {
        return transactions;
    }
    //Adds new Transaction and saves it to CVS file
    public void addTransaction(Transaction t) {
        transactions.add(t);
        saveTransaction(t);
    }
    //File loading
    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(File_Name))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    transactions.add(new Transaction(
                            parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])
                    ));
                }


            }
        } catch (IOException e) {
            System.out.println("No transactions found yet.");
        }
    }
    //File saving
    private void saveTransaction(Transaction t) { // ✅ method correctly placed outside of catch
        try (FileWriter writer = new FileWriter(File_Name, true)) {
            writer.write(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" +
                    t.getVendor() + "|" + t.getAmount() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}



