package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import gui.MainInterface;

import entity.BankAccount;
import entity.FileManager;
import entity.Transaction;

public class BankAccountGUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    private JPanel mainPanel, depositPanel, withdrawPanel, balancePanel;
    private JLabel balanceLabel, depositBalanceLabel, withdrawBalanceLabel;
    private JTextField depositAmountField, withdrawAmountField;
    

    public BankAccountGUI(String idcode) {
       BankAccount account = new BankAccount(readLastTransactionBalance(idcode),idcode);
        setTitle("ATM");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton balanceButton = new JButton("Balance");
        mainPanel.add(depositButton);
        mainPanel.add(withdrawButton);
        mainPanel.add(balanceButton);

        setupDepositPanel(account);
        setupWithdrawPanel(account);
        setupBalancePanel(account);

        cardPanel.add(mainPanel, "Main");
        cardPanel.add(depositPanel, "Deposit");
        cardPanel.add(withdrawPanel, "Withdraw");
        cardPanel.add(balancePanel, "Balance");

        add(cardPanel, BorderLayout.CENTER);

        depositButton.addActionListener(e -> {
            account.setBalance(readLastTransactionBalance(idcode));
            depositBalanceLabel.setText("current balance: " + account.getBalance());
            depositAmountField.setText("");
            cardLayout.show(cardPanel, "Deposit");
        });

        withdrawButton.addActionListener(e -> {
            account.setBalance(readLastTransactionBalance(idcode));
            withdrawBalanceLabel.setText("current balance: " + account.getBalance());
            withdrawAmountField.setText("");
            cardLayout.show(cardPanel, "Withdraw");
        });

        balanceButton.addActionListener(e -> {
            balanceLabel.setText("current balacne: " + account.getBalance());
            cardLayout.show(cardPanel, "Balance");
        });
    }
    
    private void setupWithdrawPanel(BankAccount account) {
        withdrawPanel = new JPanel(new GridLayout(4, 1));
        withdrawBalanceLabel = new JLabel("current balance: " + account.getBalance());
        withdrawAmountField = new JTextField(10);
        JButton withdrawSubmitButton = new JButton("Withdraw");
        JButton withdrawBackButton = new JButton("back");

        withdrawPanel.add(withdrawBalanceLabel);
        withdrawPanel.add(withdrawAmountField);
        withdrawPanel.add(withdrawSubmitButton);
        withdrawPanel.add(withdrawBackButton);

        withdrawSubmitButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(withdrawAmountField.getText());
                account.withdraw(amount);
                withdrawBalanceLabel.setText("current balance: " + account.getBalance());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount");
            }
        });

        withdrawBackButton.addActionListener(e -> {
            withdrawAmountField.setText("");
            cardLayout.show(cardPanel, "Main");
        });
    }
    
    private void setupDepositPanel(BankAccount account) {
        depositPanel = new JPanel(new GridLayout(4, 1));
        depositBalanceLabel = new JLabel("current balance: " + account.getBalance());
        depositAmountField = new JTextField(10);
        JButton depositSubmitButton = new JButton("Deposit");
        JButton depositBackButton = new JButton("back");

        depositPanel.add(depositBalanceLabel);
        depositPanel.add(depositAmountField);
        depositPanel.add(depositSubmitButton);
        depositPanel.add(depositBackButton);

        depositSubmitButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(depositAmountField.getText());
                account.deposit(amount);
                depositBalanceLabel.setText("current balance: " + account.getBalance());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount");
            }
        });

        depositBackButton.addActionListener(e -> {
            depositAmountField.setText("");
            cardLayout.show(cardPanel, "Main");
        });
    }
    
    private void setupBalancePanel(BankAccount account) {
        balancePanel = new JPanel(new GridLayout(2, 1));
        balanceLabel = new JLabel("current balance: " + account.getBalance());
        JButton balanceBackButton = new JButton("back");

        balancePanel.add(balanceLabel);
        balancePanel.add(balanceBackButton);

        balanceBackButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));
    }
    
    private double readLastTransactionBalance(String idcode) {
        Path path = Paths.get("data/" + idcode + "/TransactionList.txt");
        // Path path = Paths.get("./data/TransactionList.txt");
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                if (!lines.isEmpty()) {
                    String lastLine = lines.get(lines.size() - 1);
                    String[] parts = lastLine.split(" ");
                    return Double.parseDouble(parts[2]);
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error reading from file: " + e.getMessage());
            }
        }
        return 1000; // Default balance if no transactions are found or in case of error
    }


    // public static void main(String idcode) {
    //     SwingUtilities.invokeLater(() -> new BankAccountGUI(idcode).setVisible(true));
    // }
}

