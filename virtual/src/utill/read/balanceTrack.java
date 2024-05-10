package utill.read;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class balanceTrack {
    public static double readThirdParameter(String idcode) {
        String filePath = "data/" + idcode + "/TransactionList.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            if ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 3) {
                    return Double.parseDouble(parts[2]);
                } else {
                    System.err.println("TransactionList.txt in folder " + idcode + " does not have enough parameters.");
                }
            } else {
                System.err.println("TransactionList.txt in folder " + idcode + " is empty.");
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return -1; // 如果出现任何错误或文件格式不正确，返回 -1
    }

    public static void main(String idcode) {
        // String idcode = "example_idcode"; // 用你的实际 idcode 替换此处的示例 idcode
        double thirdParameter = readThirdParameter(idcode);
        if (thirdParameter != -1) {
            System.out.println("Third parameter in TransactionList.txt for idcode " + idcode + ": " + thirdParameter);
        } else {
            System.out.println("Failed to read third parameter for idcode " + idcode);
        }
    }
}