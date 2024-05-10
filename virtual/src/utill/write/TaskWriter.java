package utill.write;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import entity.Task;

public class TaskWriter {
    public static void writeTasksToFile(ArrayList<Task> tasks, String idcode) {
        String filePath = "data/" + idcode + "/TaskList.txt";
        try {
            BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(filePath),
                StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING); // Truncate existing file
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
