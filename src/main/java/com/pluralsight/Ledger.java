package com.pluralsight;
import java.util.*;
import java.util.stream.Collectors;
public class Ledger {
    private final TransactionManager manager;

    public Ledger(TransactionManager manager){
        this.manager = manager;
    }
    public void showALl(){
        printTransactions(manager.getTransactions());
    }
    public void showSales(){
        printTransactions(manager.getTransactions().stream()
                .filter(t -> t.getAmount() > 0)
                .toList());
    }
    public void showPurchases(){
        printTransactions(manager.getTransactions()
                .stream()
                .filter(t -> t.getAmount() < 0)
                .toList());
    }
    private void printTransactions(List<Transaction>list){
        if (list.isEmpty()){
            System.out.println("No Transactions found.");
            return;
        }
        System.out.println("\nDate | Time | Description | Vendor | Amount");
        System.out.println("--------------------------------------------");


        list.stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .forEach(System.out::println);
    }
}
