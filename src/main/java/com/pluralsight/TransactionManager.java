package com.pluralsight;
import java.io.*;
import java.util.*;

public class TransactionManager {
    private static final String File_Name = "transactions.csv";
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionManager() {
        loadTransactions();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
        saveTransaction(t);
    }

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
    private void saveTransaction(Transaction t) { // ✅ method correctly placed outside of catch
        try (FileWriter writer = new FileWriter(File_Name, true)) {
            writer.write(String.join("|",
                    t.getDate(),
                    t.getTime(),
                    t.getDescription(),
                    t.getVendor(),
                    String.valueOf(t.getAmount())) + "\n");
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}



