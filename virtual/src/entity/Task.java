package entity;

public class Task {
    static int count = 1; // 自动编号
    int id;
    String name;
    String description;
    String deadline;
    String reward;

    public Task(String name, String description, String deadline, String reward) {
        this.id = count++;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.reward = reward;
    }

    public static Task fromString(String str) {
        // Assuming str is formatted as "name|description|deadline|reward"
        String[] parts = str.split("\\|");
        if (parts.length != 4) {
            // Invalid format
            return null;
        }
        return new Task(parts[0], parts[1], parts[2], parts[3]);
    }

    @Override
    public String toString() {
        return name + "|" + description + "|" + reward + "|" + deadline;
    }

    public String getFullDescription() {
        return "<html><font color='red'><b>[" + id + "] " + name + "</b></font><br>Deadline: " + deadline + "<br>Reward: " + reward + "<br>Description: " + description + "<br>-------------- " + "</html>";
    }
}
