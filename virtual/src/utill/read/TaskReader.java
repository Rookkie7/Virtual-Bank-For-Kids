package utill.read;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import entity.Task;

public class TaskReader {
    public static ArrayList<Task> readTasksFromFile(String idcode) {
        ArrayList<Task> tasks = new ArrayList<>();
        String filePath = "data/" + idcode + "/TaskList.txt";
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming Task's fromString() method returns a Task object
                Task task = Task.fromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}