package com.pluralsight;
import  java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionManager manager = new TransactionManager();
    private final Ledger ledger = new Ledger(manager);
}
