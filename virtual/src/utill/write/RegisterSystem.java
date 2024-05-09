package utill.write;
import java.io.*;

public class RegisterSystem {
    private static final String FILENAME = "data/password.txt";
    private static final String BASE_DIRECTORY = "data/";

    public static boolean register(String username, String password, String parentCode) {
        // 检测用户名是否已存在
        if (isUsernameExists(username)) {
            return false; // 如果用户名已存在，返回注册失败
        }

        try {
            // 将注册信息写入文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true));
            writer.write(username + "," + password + "," + parentCode);
            writer.newLine();
            writer.close();

	    File userDirectory = new File(BASE_DIRECTORY + username);
            if (!userDirectory.exists()) {
                userDirectory.mkdir();
                
                // 在文件夹内创建三个文件
                File fileA = new File(userDirectory, "GoalList.txt");
                fileA.createNewFile();
                File fileB = new File(userDirectory, "TaskList.txt");
                fileB.createNewFile();
                File fileC = new File(userDirectory, "TransactionList.txt");
                fileC.createNewFile();
            }
            return true; // 注册成功
        } catch (IOException e) {
            e.printStackTrace();
            return false; // 发生异常，注册失败
        }
    }

    private static boolean isUsernameExists(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length > 0 && userInfo[0].equals(username)) {
                    reader.close();
                    return true; // 用户名已存在
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // 用户名不存在
    }
}
