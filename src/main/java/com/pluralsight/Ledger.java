package com.pluralsight;
import java.util.*;
import java.util.stream.Collectors;
public class Ledger {
    private final TransactionManager manager;

    public Ledger(TransactionManager manager){
        this.manager = manager;
    }
    public void showALl(){
        printList(manager.getTransactions());
    }
    public void showDeposits(){
        printList(manager.getTransactions());
    }
    public void showPayments(){
        printList(manager.getTransactions()
                .stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.toList()));
    }
    private void printList(List<Transaction>list){
        list.stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .forEach(System.out::println);
    }
}
