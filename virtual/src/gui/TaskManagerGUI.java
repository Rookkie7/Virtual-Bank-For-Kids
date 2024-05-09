
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Task {
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

    @Override
    public String toString() {
        return "[" + id + "] " + name + " (Deadline: " + deadline + ", Reward: " + reward + ")";
    }

    public String getFullDescription() {
        return "<html><font color='red'><b>[" + id + "] " + name + "</b></font><br>Deadline: " + deadline + "<br>Reward: " + reward + "<br>Description: " + description + "<br>-------------- " + "</html>";
    }
    
}

public class TaskManagerGUI extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JList<Task> taskList;
    private DefaultListModel<Task> listModel;
    private JButton setTaskButton;
    private JButton deleteTaskButton;
    private ArrayList<Task> tasks;

    public TaskManagerGUI() {
        setTitle("Tasks");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Tasks");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setCellRenderer(new TaskListRenderer());

        JScrollPane listScrollPane = new JScrollPane(taskList);

        setTaskButton = new JButton("Set Task");
        setTaskButton.setFont(new Font("Arial", Font.PLAIN, 30)); // 设置按钮字体大小为20
        setTaskButton.addActionListener(this);

        deleteTaskButton = new JButton("Delete Task");
        deleteTaskButton.setFont(new Font("Arial", Font.PLAIN, 30)); // 设置按钮字体大小为20
        deleteTaskButton.addActionListener(this);

        tasks = new ArrayList<>();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(listScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(setTaskButton);
        buttonPanel.add(deleteTaskButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == setTaskButton) {
            TaskDialog dialog = new TaskDialog(this);
            dialog.setVisible(true);
            Task newTask = dialog.getTask();
            if (newTask != null) {
                tasks.add(newTask);
                listModel.addElement(newTask);
            }
        }else if (e.getSource() == deleteTaskButton) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex >= 0) {
                tasks.remove(selectedIndex);
                listModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to delete.");
            }
        }
    }

    public static void main(String[] args) {
        new TaskManagerGUI();
    }
}

class TaskListRenderer extends JLabel implements ListCellRenderer<Task> {
    public TaskListRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task task, int index, boolean isSelected, boolean cellHasFocus) {
        setText(task.getFullDescription());
        setFont(new Font("Arial", Font.PLAIN, 20)); // 设置任务字体大小为20
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
