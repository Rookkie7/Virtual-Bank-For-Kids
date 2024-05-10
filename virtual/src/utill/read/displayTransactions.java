package utill.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class displayTransactions {
       public static StringBuilder displayTransactionsFromFile(String filePath) {
        StringBuilder transactions = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String type = parts[0].equals("1") ? "Deposit" : "Withdraw";
                    transactions.append(type).append(" ").append(parts[1]);
                    for (int i = 2; i < parts.length; i++) {
                        transactions.append(" ").append(parts[i]);
                    }
                    transactions.append("\n");
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return transactions;
    }
}
