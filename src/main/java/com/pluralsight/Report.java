package com.pluralsight;
import  java.time.LocalDate;
import java.util.List;
import  java.util.stream.Collectors;

public class Report {
    private final List<Transaction> transactions;

    public Report(List<Transaction> transactions){
        this.transactions = transactions;
    }
    public void todaySummary(){
        LocalDate today = LocalDate.now();
        showSummary(today, today, "Today's Summary");
    }
    public void thisMonth(){
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = LocalDate.now();
        showSummary(start, end, "This months Summary");
    }
    public void thisYear() {
        LocalDate start = LocalDate.now().withDayOfYear(1);
        LocalDate end = LocalDate.now();
        showSummary(start, end, "This Year’s Summary");
    }

    public void searchByVendor(String vendor) {
        System.out.println("\nTransactions for vendor: " + vendor);
        System.out.println("------------------------------------------------------");
        boolean found = false;

        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for that vendor.");
        }
    }

    private void showSummary(LocalDate start, LocalDate end, String title) {
        double sales = 0, purchases = 0;

        for (Transaction t : transactions) {
            LocalDate date = LocalDate.parse(t.getDate());
            if ((date.isEqual(start) || date.isAfter(start)) &&
                    (date.isEqual(end) || date.isBefore(end))) {

                if (t.getAmount() > 0) sales += t.getAmount();
                else purchases += t.getAmount();
            }
        }

        double net = sales + purchases;

        System.out.println("\n----------------- " + title + " --------------------");
        System.out.println("Period: " + start + " to " + end);
        System.out.println("Total Sales: $" + String.format("%.2f", sales));
        System.out.println("Total Purchases: $" + String.format("%.2f", purchases));
        System.out.println("Net Profit: $" + String.format("%.2f", net));
        System.out.println("------------------------------------------------------");
    }
}
