package com.pluralsight;
import  java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import  java.util.stream.Collectors;

public class Report {
    private final List<Transaction> transactions;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Report(List<Transaction> transactions){
        this.transactions = transactions;
    }
    public void monthToDate(){
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);
        printBetween(start, today);
    }
    public void previousMonth(){
        LocalDate today = LocalDate.now();
        LocalDate start = today.minusMonths(1).withDayOfMonth(1);
        LocalDate end = today.withDayOfMonth(1).minusDays(1);
        printBetween(start, end);
    }
    private void yearToDate(){
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfYear(1);
        printBetween(start, today);
    }
    private void printBetween(LocalDate start, LocalDate end){
        transactions.stream()
                .filter(t -> {
                    LocalDate d = LocalDate.parse(t.getDate(), formatter);
                    return !d.isBefore(start) && !d.isAfter(end);
                })
                .forEach(System.out::println);
    }
    public void searchByVendor(String vendor){
        transactions.stream()
                .filter(t -> t.getVendor().equalsIgnoreCase(vendor))
                .forEach(System.out::println);
    }
}
