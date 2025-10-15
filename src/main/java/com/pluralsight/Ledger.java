package com.pluralsight;
import java.util.*;

public class Ledger {
    private final TransactionManager manager; //Access all transactions

    public Ledger(TransactionManager manager){
        this.manager = manager;
    }
    //Methods
    public void showALl(){
        printTransactions(manager.getTransactions());
    }
    public void showSales(){
        printTransactions(manager.getTransactions().stream()
                .filter(t -> t.getAmount() > 0) //Filter positive amount
                .toList());
    }
    public void showPurchases(){
        printTransactions(manager.getTransactions()
                .stream()
                .filter(t -> t.getAmount() < 0) //Filter negative amount
                .toList());
    }
    //Helper Method
    private void printTransactions(List<Transaction>list){
        if (list.isEmpty()){
            System.out.println("No Transactions found.");
            return;
        }
        System.out.println("\nDate | Time | Description | Vendor | Amount");
        System.out.println("--------------------------------------------");

        //Sort from newest-oldest by date
        list.stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .forEach(System.out::println);
    }
}
