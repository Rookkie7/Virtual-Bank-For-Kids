package entity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {

    public static void writeTransaction(Transaction transaction,String idcode) {
        // String idcode= transaction.idcode;
         File file = new File("data/" + idcode + "/TransactionList.txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            pw.println(transaction.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
