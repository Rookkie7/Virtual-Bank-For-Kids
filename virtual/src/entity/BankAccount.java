package entity;

import java.util.Date;

public class BankAccount {
    private double balance;
    public String idcode;

    public BankAccount(double initialBalance, String idcode) {
        this.balance = initialBalance;
        this.idcode = idcode;
    }

    public void deposit(double amount) {
        balance += amount;
        FileManager.writeTransaction(new Transaction(1, amount, balance, new Date()), idcode);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            FileManager.writeTransaction(new Transaction(0, amount, balance, new Date()), idcode);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
