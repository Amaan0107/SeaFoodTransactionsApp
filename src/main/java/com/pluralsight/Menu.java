package com.pluralsight;
import  java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionManager manager = new TransactionManager();
    private final Ledger ledger = new Ledger(manager);

    //Shows main menu
    public void showMenu(){
        while(true){
            System.out.println("\n--------------- SeaFood Tracker ------------------");
            System.out.println("1) Add Sale");
            System.out.println("2) Add Purchase");
            System.out.println("3) View Ledger");
            System.out.println("4) Reports");
            System.out.println("5) Exit");
            System.out.println("-----------------------------------------------------");
            System.out.println("Enter here:");
            String choice = scanner.nextLine();

            switch (choice){
                case "1" -> addTransaction(true);
                case "2" -> addTransaction(false);
                case "3" -> ledgerMenu();
                case "4" -> reportMenu();
                case "5" -> {
                    System.out.println("GoodBye!!!");
                    return;
                }
                default -> System.out.println("Invalid. Try again.");
            }
        }
    }
    //Adding Transaction
    private void addTransaction(boolean isSale){
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount:");
        double amount = Double.parseDouble(scanner.nextLine());
        //Buying stored as negative
        if (!isSale) amount *= -1;

        Transaction t = new Transaction(
                LocalDate.now().toString(),
                LocalTime.now().toString().substring(0,8),
                description,
                vendor,
                amount
        );
        manager.addTransaction(t);
        if (isSale)
            System.out.println("Sale recorded successfully!");
        else
            System.out.println("Purchase recorded successfully!");
    }
    //Ledger Menu
    private void ledgerMenu(){
        while (true){
            System.out.println("\n---------------- Ledger menu ----------------");
            System.out.println("1) All Transactions");
            System.out.println("2) Sales Only");
            System.out.println("3) Purchases Only");
            System.out.println("4) Return to Home screen");
            System.out.println("------------------------------------------------");
            System.out.println("Enter here:");
            String choice = scanner.nextLine();

            switch (choice){
                case "1" -> ledger.showALl();
                case "2" -> ledger.showSales();
                case "3" -> ledger.showPurchases();
                case "4" -> {
                    return;
                }
                default -> System.out.println("Invalid. Try again.");
            }
        }
    }
    //Report menu
    private void reportMenu(){
        Report report = new Report(manager.getTransactions());
        while (true){
            System.out.println("\n-------------- Reports menu ----------------");
            System.out.println("1) Today");
            System.out.println("2) This month");
            System.out.println("3) This year");
            System.out.println("4) Search by Vendor");
            System.out.println("5) Back");
            System.out.println("----------------------------------------------");
            System.out.println("Enter here:");
            String choice = scanner.nextLine();

            switch (choice){
                case "1" -> report.todaySummary();
                case "2" -> report.thisMonth();
                case "3" -> report.thisYear();
                case "4" -> {
                    System.out.print("Enter Vendor name:");
                    String vendor = scanner.nextLine();
                    report.searchByVendor(vendor);
                }
                case "5" -> { return;}
                default -> System.out.println("Invalid. Try again.");
            }
        }
    }
}
