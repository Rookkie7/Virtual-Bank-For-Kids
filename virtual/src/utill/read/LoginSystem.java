package utill.read;
import java.io.BufferedReader;
import entity.Account;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginSystem {
    private static final String FILE_NAME = "data/password.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取文件内容
        readFromFile();
        scanner.close();
    }

    // 读取文件内容
    private static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String account = parts[0].trim();
                String password = parts[1].trim();
                String parentCode = parts[2].trim();
                //System.out.println("账号: " + account + ", 密码: " + password + ", ParentCode: " + parentCode);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // 验证登录
    public static Account validateLogin(String account, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String storedAccount = parts[0].trim();
                String storedPassword = parts[1].trim();
                String storedParentCode = parts[2].trim();
                if (account.equals(storedAccount) && password.equals(storedPassword)) {
                    return new Account(storedAccount, storedParentCode);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return null; // 登录验证失败时返回 null
    }
}
