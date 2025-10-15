package com.pluralsight;

public class Transaction {
    private String date; //Date of Transaction
    private String time; //Time of transaction
    private String description; //Description of transaction
    private String vendor; //Vendor name
    private Double amount;  //Amount for sales and purchases


    // Constructor
    public Transaction(String date, String time, String description, String vendor, Double amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    //Getters
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public Double getAmount() {
        return amount;
    }
    //Returns a readable version of transaction
    @Override
    public String toString() {
        return date + " | " + time + " | " + description + " | " + vendor + " | " + amount;
    }
}
