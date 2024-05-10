package entity;

import java.util.Date;

public class Transaction {
    private int transactionType; // 1 for deposit, 0 for withdrawal
    private double amount;
    private double balanceAfterTransaction;
    private Date timestamp;
    private String idcode;

    public Transaction(int transactionType, double amount, double balance, Date timestamp) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfterTransaction = balance;
        this.timestamp = timestamp;
        // this.idcode = idcode;
    }

    @Override
    public String toString() {
        return transactionType + " " + amount + " " + balanceAfterTransaction + " " + timestamp;
    }
}
